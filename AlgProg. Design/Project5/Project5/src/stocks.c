/*
 * wesley easton
 * 08 November 2017
 * 
 * This program allows the user to enter multiple stocks for various calculations or read in a .csv file type for stocks.  The user may enter the stock manually or on the command line.
 * The calculations are : min stock, max stock, average stock value, stock standard deviation, and stock median.
 */


#include <stdio.h>
#include <stdlib.h>
#include "io.h"
#include "stats.h"
#include "utils.h"
#include "common.h"

// this function counts the number of columns and valid lines in 
// fname and assigns them to cols and rows, respectively 
void get_num_lines(const char *fname, const char delim, int *rows, int *cols);


// this function assigns the stock values and volume to their respective arrays
// you  need to make sure you're not parsing blank lines. use rows and cols as
// an extra safety measure to prevent you from accessing unallocated memory   
void get_1d_array_values(const char *fname, float *open, float *close, float *high, float *low, float *volume, char delim, int rows, int cols);


// the function assigns the stock values and dates to 2d-arrays. stocks_array should have a size
// columns x rows. volume should be included in stocks_array and treated as a float since all the 
// statistics done on the volume array will be of type float  
void get_2d_array_values(const char *fname, float **stocks_array, char **date, char delim, int rows, int cols);


// this function takes in a stats_array of structures containing statistics for each of the data 
// arrays in our file: open, high, low close, volume and prints a pretty-formatted tabled with the results
// like the one shown in the project writeup
void print_stats_table(const Stats *stats_array, int size);


int main(int argc, char* argv[])
{
    int size;
    int option = 0;
    int rows = 0;
    int cols = 0;
    
    char delim = ',';
    
    // structure that holds the stats for an array
    Stats stats;
    Stats *stats_array;
    
    // individual arrays that hold the stock values
    float *stocks,  // stock values from options 1-4
          *open,    // open stock price from option 5
          *close,   // close stock price from option 5
          *high,    // high stock price from option 5
          *low,     // low stock price from option 5
          *volume;  // trade volume of stock from option 5

    char **date;            // array of strings to hold the date from option 5
    float **stocks_arrayTwo;   // 2d array to hold the open, close, high, low , & volume stock prices from option 5 

	//File Name
    FILE *fname;

    if (argc == 1)
    {
		// greet and  get the stock values
		print_greeting();
		printf("How many stocks prices would you like to analyze?  ");
		scanf("%d", &size);

		stocks = (float *) malloc(size * sizeof(float));
    	if(stocks == NULL)
        {
            printf("Error! Cannot allocate memory for stocks - Exiting...\n");
            exit(0);
        }

        read_array(stocks, size);

        option = 1;
    }
    //.x file and additional statement
    else if (argc == 2)
    {

		char *ptr = NULL;
		ptr = strchr(argv[1], delim);
		
		//Checks to see if .csv file exists
		if(strstr(argv[1], ".csv") != NULL)
		{
			fname = fopen(argv[1], "r");
			
			if(fname == NULL)
			{
				printf("ERROR: %s not found\n", argv[1]);
				return(-1);
			}
			
			fclose(fname);
			
			//Gets the number of rows and columns in file
			get_num_lines(argv[1], delim, &rows, &cols);
			
			
			if(cols != 6)
			{
				printf("ERROR: Incosistent number of columns\n");
				
				return(0);
			}
			
			//allocating memory for 1D arrays
			open = (float *) malloc(rows * sizeof(float));
			close = (float *) malloc(rows * sizeof(float));
			high = (float *) malloc(rows * sizeof(float));
			low = (float *) malloc(rows * sizeof(float));
			volume = (float *) malloc(rows * sizeof(float));
			stats_array = (Stats *) malloc(5 * sizeof(Stats));
			
			//allocating memory for 2D array
			stocks_arrayTwo = (float **)malloc(5 * sizeof(float *));
			
		    for (int i = 0; i < 5; i++)
		    {
			  stocks_arrayTwo[i] = (float *)malloc(rows * sizeof(float));
			  
			  if (stocks_arrayTwo[i] == NULL)
			  {
				 printf("Error! Cannot allocate memory for stocks_arrayTwo\n");
				 return(0);
			  }
		  }
			
			get_1d_array_values(argv[1], open, close, high, low, volume, delim, rows, cols);
			get_2d_array_values(argv[1], stocks_arrayTwo, date, delim, rows, cols);
		
			option = 2;
		}

		// does the string contain the delimiter
		else if (ptr == NULL) // this is case $ ./stocks.x 4
        {
			
				size = atoi(argv[1]);

				stocks = (float *) malloc(size * sizeof(float));
				
				if(stocks == NULL)
				{
					printf("Error! Cannot allocate memory for stocks - Exiting...\n");
					exit(0);
				}

				read_array(stocks, size);
			
           option = 1;
     	}
     	
     	// this is case $ ./stocks 1,2,3,4
		else 
		{

	    	size = get_num_tokens(argv[1], delim);

        	stocks = (float *) malloc(size * sizeof(float));
	    	if(stocks == NULL)
	    	{
				printf("Error! Cannot allocate memory for stocks - Exiting...\n");
				exit(0);
	    	}

        	get_tokens_array(argv[1], stocks, size, delim);

        	option = 1;
    	}
    }
    
    //.x and multiple statements
    else if (argc > 2) // this is case $ ./stocks.x 1 2 3 4
    {
        // ignore the executable
		size = argc - 1;

        stocks = (float *) malloc(size * sizeof(float));
		if(stocks == NULL)
        {
            printf("Error! Cannot allocate memory for stocks - Exiting...\n");
            exit(0);
        }

		// assign all the values but the executable to the array
        for (int i = 0; i < size; i++)
            stocks[i] = atof(argv[i+1]);


        option = 1;
    }


    if (option == 1)
    {
    	// get stats and print results
    	get_stats(&stats, stocks, size);
    	print_results(&stats, stocks, size);
         // free memory
        free(stocks);

    }
    
    //Option 2 if file is read in
    else if(option == 2)
    {
		printf("\n\n\nBefore get\n");
		get_stats(&stats_array[0], open, rows);
		get_stats(&stats_array[1], close, rows);
		get_stats(&stats_array[2], high, rows);
		get_stats(&stats_array[3], low, rows);
		get_stats(&stats_array[4], volume, rows);
		
		//1D Array Table
		printf("\n1D Array Table");
		print_stats_table(stats_array, 5);
		
		for(int i = 0; i < 5; i++)
		{
			get_stats(&stats_array[i], stocks_arrayTwo[i], rows);
		}
		
		//2D Array Table
		printf("\n2D Array Table");
		print_stats_table(stats_array, 5);
		
		free(open);
        free(close);
        free(high);
        free(low);
        free(volume);
		free(stats_array);
		
		for (int i = 0; i < 5; i++)
		{
			free(stocks_arrayTwo[i]);
		}
		
		free(stocks_arrayTwo);
    }

    return 0;
}

void get_num_lines(const char *fname, char delim, int *rows, int *cols)
{
	char line[256];
	int lines = 0;
	
	FILE *fileName = fopen(fname, "r");
			
	// returns no zero when EOF  is reached; zero otherwise
	while(!feof(fileName))
	{
		
		fgets(line, 255, fileName);
		lines++;
		
		*cols = get_num_tokens(line, delim);
		
		if(*cols != 6)
		{
			break;
		}

	}
	
	*rows = lines - 2;
	
	fclose(fileName);
}


void get_1d_array_values(const char *fname, float *open, float *close, float *high, float *low, float *volume, char delim, int rows, int cols)
{
	char line[256];
	char *tok;
	
	int i = 0;
	
	FILE *fileName = fopen(fname, "r");
	
	//Skips column header row
	fgets(line, 255, fileName);
	fgets(line, 255, fileName);
	
	
	while(!feof(fileName))
	{
		//Assigns each array the data in the file
		tok = strtok(line, &delim);
		
		tok = strtok(NULL, &delim);
		open[i] = atof(tok);
		
		tok = strtok(NULL, &delim);

		close[i] = atof(tok);
		
		tok = strtok(NULL, &delim);
		high[i] = atof(tok);
		
		tok = strtok(NULL, &delim);
		low[i] = atof(tok);
		
		tok = strtok(NULL, &delim);
		volume[i] = atof(tok);
		
		fgets(line, 255, fileName);
		
		i++;
		
	}
	
	fclose(fileName);
}




void get_2d_array_values(const char *fname, float **stocks_array, char **date, char delim, int rows, int cols)
{
	char line[256];
	char *tok;
	
	int j = 0;
	
	FILE *fileName = fopen(fname, "r");
	
	//Skips the column header row
	fgets(line, 255, fileName);
	fgets(line, 255, fileName);
	
	while(!feof(fileName))
	{
		tok = strtok(line, &delim);
		
		//Assigns each array the data from the file
		for(int i = 0; i < 5; i++)
		{
			tok = strtok(NULL, &delim);
			stocks_array[i][j] = atof(tok);
			
		}
		fgets(line, 255, fileName);
		
		j++;
	
	}
	
	fclose(fileName);
}


void print_stats_table(const Stats *stats_array, int size)
{

	//Prints and formats the table
	printf("\t %-10s %-10s %-10s %-10s %-10s\n", "Open", "High", "Low", "Close", "Volume");
	printf("------------------------------------------------------------------------\n");
	
	for(int i = 0; i < size; i++)
	{
		if(i == 0)
		{
			printf("min:\t\t");
			
			for(int j = 0; j < size; j++)
			{
				printf("$%-10.2f", stats_array[j].min);
			}
			printf("\n");
		}
		else if(i == 1)
		{
			printf("max:\t\t");
			
			for(int j = 0; j < size; j++)
			{
				printf("$%-10.2f", stats_array[j].max);
			}
			printf("\n");
		}
		else if(i == 2)
		{
			printf("mean:\t\t");
			
			for(int j = 0; j < size; j++)
			{
				printf("$%-10.2f", stats_array[j].mean);
			}
			printf("\n");
		}
		else if(i == 3)
		{
			printf("stddev:\t\t");
			
			for(int j = 0; j < size; j++)
			{
				printf("$%-10.2f", stats_array[j].stddev);
			}
			printf("\n");
		}
		else if(i ==4)
		{
			printf("median:\t\t");
			
			for(int j = 0; j < size; j++)
			{
				printf("$%-10.2f", stats_array[j].median);
			}
			printf("\n");
		}
	}
	printf("------------------------------------------------------------------------\n");

}
