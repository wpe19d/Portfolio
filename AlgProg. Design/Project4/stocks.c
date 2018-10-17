/*
 * wesley easton
 * 25 October 2017
 * 
 * This program allows the user to enter multiple stocks for various calculations.  The user may enter the stock manually or on the command line.
 * The calculations are : min stock, max stock, average stock value, stock variance, and stock median.
 */

#include <limits.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "io.h"
#include "stats.h"
#include "utils.h"

int main(int argc, char* argv[])
{
    int size = 0;

    char cdmLineEntry [1024];
    char delimiter[] = ",";
    char *numberOfEntries;
    
    //prints the greeting for the program
    print_greeting();
   
	//runs if the user only enters the executable command
	if(argc == 1)
	{
		printf("How many stocks prices would you like to analyze?  ");
		scanf("%d", &size);

		float array[size];
		
		// read the data
		read_array(array, size);


		// get the stats
		float mean = get_average(array, size);
		float variance = get_variance(array, size);
		float min = get_min(array, size);
		float max = get_max(array, size);
		float median = get_median(array, size);

		// show the results
		print_results(array, size, median, min, max, mean, variance);
	}
	//Runs if the user enters the executable and one additional string
	else if(argc == 2)
	{
		strcpy(cdmLineEntry, argv[1]);
		numberOfEntries = strchr(cdmLineEntry, ',');
		
		//Runs if the user only entered one number
		if(numberOfEntries == NULL && argc == 2)
		{
			size = atoi(cdmLineEntry);
			float array[size];
			
			read_array(array, size);

			// get the stats
			float mean = get_average(array, size);
			float variance = get_variance(array, size);
			float min = get_min(array, size);
			float max = get_max(array, size);
			float median = get_median(array, size);

			// show the results
			print_results(array, size, median, min, max, mean, variance);

		}
		
		//Runs if the user entered multiple numbers separated by commas.
		else if(numberOfEntries != NULL)
		{
			size = get_num_tokens(cdmLineEntry, delimiter);
			float array[size];
			
			get_tokens_array(cdmLineEntry, array, size, delimiter);
						
			// get the stats
			float mean = get_average(array, size);
			float variance = get_variance(array, size);
			float min = get_min(array, size);
			float max = get_max(array, size);
			float median = get_median(array, size);
			
			// show the results
			print_results(array, size, median, min, max, mean, variance);
			
		}
	}
	
	//Runs if the user enters multiple numbers separated by spaces.
	else
	{
		
			size = (argc - 1);
			float array[size];
			
			//Reads in numbers from command line and assigns them to the array.
			for(int i = 1; i < argc; i++)
			{
				array[i - 1] = atof(argv[i]);
			}
			
			// get the stats
			float mean = get_average(array, size);
			float variance = get_variance(array, size);
			float min = get_min(array, size);
			float max = get_max(array, size);
			float median = get_median(array, size);
			
			// show the results
			print_results(array, size, median, min, max, mean, variance);

	}

	return 0;
}

