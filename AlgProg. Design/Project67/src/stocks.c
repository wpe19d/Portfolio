/*
 * wesley easton
 * 09 December 2017
 * 
 * This program allows the user to enter multiple locations of stock for various calculations or read in a .csv file type for stocks.  The information is then output file that the user can create.
 * The calculations are : min stock, max stock, average stock value, stock standard deviation, and stock median.
 */
 #include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

#include "io.h"
#include "stats.h"
#include "utils.h"
#include "common.h"

void printDirectoryInfo(FILE *fp, char **cmdLineArray, char **fileArray, char **tickerArray, int size, int boolTik, int dirLoc, int count, int printCount);
void printSingleEntry(FILE *fp, int printCount, char *cmdLineArray);

int main(int argc, char* argv[])
{

	FILE *fin;
	FILE *compFile;
    char **files_list;
    char **totalFilesList;
	char **totalDirectoryList;
    char **totalTickers;
    char **singleFileList;
	int *cmdLineLocation = (int*) malloc(argc * sizeof(int));
	int *singleCmdLocation = (int*) malloc(argc * sizeof(int));
    char **tickers = malloc(argc * sizeof(char*));
    char str_delim = ',';
    char comp_delim = '"\t"';
    int idx = 0;
    int arraySize = 0;
    int printCount = 0;
    int newSize = 0;
    int singleCount = 0;
    char line[1024];
	char *tok;
    char fileName[256];
    char companyName[256];
    int size = argc - 1;
    int count = 0;
    int directorySize = 0;
    int directoryLocation = 0;
    int directoryCount =0;
    char ffname[256];
    srand(time(NULL));
    
    Corporation *corp = (Corporation *)malloc(sizeof(Corporation));

    //Variables to check if argv[#] has been read or not
    int boolTickerDirectory = 0;
    int boolTicker = 0;
    int boolContinue = 0;
    int boolFile = 0;
    int boolCompany = 0;
    int boolDirectory = 0;
    
    printf("***All directory searches should end with / unless specifying a file type format***\n\n");
    
    //Looks for file name or  for directories with -d
    for(int i = 0; i < size; i++)
    {
		if((strcmp(argv[i], "-o") == 0) && boolFile == 0)
		{			
			strcpy(fileName, argv[i + 1]);
			fin = fopen(fileName, "w");
			if(fin == NULL)
			{
				printf("%s file name not found\n", fileName);
			}
			boolFile = 1;
		}
		if((strcmp(argv[i], "-d") == 0) && boolDirectory == 0)
		{
			if(is_dir(argv[i + 1]) == 1)
			{
				get_reg_files_list2(argv[i + 1], &files_list, &directorySize);
				directoryLocation = i + 1;
				
			}
			else
			{
				printf("%s is not a valid directory\n\n", argv[i + 1]); 
				
			}
			boolTickerDirectory = 1;
		}
	}
	
	//checks for tickers or unspecified directories
    for(int j = 0; j <= size; j++)
    {
		
		//if -t is specified
		if((strcmp(argv[j], "-t") == 0) && boolTicker == 0)
		{
			int c = j + 1;
			tickers = allocate_2d_char(argc, 25);
			
			while(boolContinue == 0)
			{
				
				if(argc == (c))
				{
					boolContinue = 1;
					break;
				}
				else if(strstr(argv[c], "..") != NULL)
				{
					boolContinue = 1;
					break;
				}
				else if(strstr(argv[c], "-") != NULL)
				{
					boolContinue = 1;
					break;
				}
				
				strcpy(tickers[count], argv[c]);
				c++;
				count++;
				newSize = count;;
			}
			boolTicker = 1;
		}
		
		//if there is no -d specified.  This reads in manually entered file location or directory
		if((is_dir(argv[j]) == 1 || is_reg_file(argv[j]) == 1) && boolTickerDirectory == 0)
		{
			if(strstr(argv[j], ".csv") && strstr(argv[j], companyName) == NULL)
			{
				printSingleEntry(fin, printCount, argv[j]);
				singleCmdLocation[singleCount] = j;
				singleCount++;
				printCount++;
			}
			else if(is_dir(argv[j]) == 1)
			{
				cmdLineLocation[directoryCount] = j;
				directoryLocation = j;
				get_reg_files_list2(argv[j], &files_list, &directorySize);
				tickers = allocate_2d_char(directorySize, 25);
				newSize += directorySize;
				
				for(int i = 0; i < directorySize; i++)
				{
					char temp[1024];
					strcpy(temp, files_list[i]);
					
					strcpy(tickers[i],strtok(temp, "."));
				}
				
				printDirectoryInfo(fin, argv, files_list, tickers, directorySize, boolTicker, directoryLocation, count, printCount);
				printCount++;
				boolDirectory = 1;
			}
		}
		//reads in company file location
		if((strcmp(argv[j], "-c") == 0) && boolCompany == 0)
		{	
			strcpy(companyName, argv[j + 1]);
			compFile = fopen(companyName, "r");
			
			if(compFile == NULL)
			{
				printf("%s file name not found\n", companyName);
			}
			
			boolCompany = 1;
			
		}
			
	}

	totalFilesList = allocate_2d_char(newSize + singleCount, 25);
	totalDirectoryList = allocate_2d_char(newSize + singleCount, 50);
	totalTickers = allocate_2d_char((newSize + singleCount), 50);
	
	//for -t tickers 
	if(boolTicker == 1)
	{
		arraySize = count;
		//creates array of all tickers to pick one at random
		for(int i = 0; i < count; i++)
		{
			strcpy(totalFilesList[i], tickers[i]);
		}
	}
	//for all directories with files specified
	if(singleCount != 0)
	{
		singleFileList = allocate_2d_char(singleCount, 50);
		
		char *last;
		
		for(int i = 0; i < singleCount; i++)
		{
			char temp[1024];

			strcpy(singleFileList[i], argv[singleCmdLocation[i]]);
			strcpy(totalDirectoryList[i], argv[singleCmdLocation[i]]);
			strcpy(temp, singleFileList[i]);
			last = strrchr(temp, '/');
			strcpy(temp, strtok(last + 1, "."));
			strcpy(totalTickers[i], temp);
		}
	}
	//creates array of all .csv files to pick one at random
	if(boolDirectory == 1 || boolTicker == 1)
	{
		for(int i = 0; i < printCount; i++)
		{
			if(cmdLineLocation[i] == 0)
			{
				break;
			}
			get_reg_files_list2(argv[cmdLineLocation[i]], &files_list, &directorySize);
			for(int k = 0; k < directorySize; k++)
			{
				char temp[1024];
				strcpy(temp, argv[cmdLineLocation[i]]);
				strcpy(totalFilesList[k + arraySize], files_list[k]);
				strcpy(totalDirectoryList[k + arraySize + singleCount], strcat(temp, files_list[k]));
				strcpy(totalTickers[k + arraySize + singleCount], strtok(files_list[k], "."));
				
			}
			arraySize += directorySize;
		}
		arraySize += singleCount;
		idx = rand() % arraySize;
	}
	//if -t is specified
	if(boolTicker == 1)
	{
		idx = rand() % count;
		printDirectoryInfo(fin, argv, files_list, tickers, directorySize, boolTicker, directoryLocation, count, printCount);
			
		for(int i = 0; i < directorySize; i++)
		{
			for(int j = 0; j < count; j++)
			{
				if(strstr(files_list[i], tickers[j]) != NULL)
				{
					if(count == 1)
					{
						idx = 1;
						strcpy(ffname, strcat(argv[directoryLocation], files_list[i]));
					}
					else if(j == idx)
					{
						strcpy(ffname, strcat(argv[directoryLocation], files_list[i]));

					}
				}
			}
		}

		get_stock_values(ffname, &(corp->data), &(corp->date), str_delim, &corp->data_rows, &corp->data_cols);
		corp->date_length = corp->data_cols;
		corp->stats = (Stats *) malloc(corp->data_rows*sizeof(Stats));
		
		//search Comp2 File
		fgets(line, 1023, compFile);
		fgets(line, 1023, compFile);
		while(!feof(compFile))
		{
			tok = strtok(line, &comp_delim);
			if(count == 1)
			{
				idx = 0;
			}
			to_upper(tickers[idx]);
			
			if(strcmp(tok, tickers[idx]) == 0)
			{
				
				//corp name abreviation.
				strcpy(corp->ticker, tok);
				
				//corp name
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				strcpy(corp->name, tok);

				//corp sector
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				strcpy(corp->sector, tok);

				//corp industry
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				strcpy(corp->industry, tok);
				
				break;
			}
			
			fgets(line, 1023, compFile);
		
		}
		get_all_stats(corp->stats, corp->data, corp->data_rows, corp->data_cols);
		print_stats_table(fin, corp);
		free_2d_char(tickers, argc);
		
	}
	//if only specified files were entered
	else if(singleCount != 0 && boolDirectory == 0)
	{
		idx = rand() % singleCount;
		for(int i = 0; i < singleCount; i++)
		{
			if(i == idx)
			{
				strcpy(ffname, singleFileList[i]);
			
			}
		}
		get_stock_values(ffname, &(corp->data), &(corp->date), str_delim, &corp->data_rows, &corp->data_cols);
		corp->date_length = corp->data_cols;
		corp->stats = (Stats *) malloc(corp->data_rows*sizeof(Stats));

		//search Comp2 File

		while(!feof(compFile))
		{
			tok = strtok(line, &comp_delim);
			to_upper(totalTickers[idx]);
			if(strcmp(tok, totalTickers[idx]) == 0)
			{
				
				//corp name abreviation.
				strcpy(corp->ticker, tok);
				
				//corp name
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				strcpy(corp->name, tok);

				//corp sector
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				strcpy(corp->sector, tok);

				//corp industry
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				strcpy(corp->industry, tok);
				
				break;
			}
			
			fgets(line, 1023, compFile);
		
		}
		
		get_all_stats(corp->stats, corp->data, corp->data_rows, corp->data_cols);
		print_stats_table(fin, corp);
	}
	//if only a directory was entered
	else if(boolDirectory == 1 && singleCount == 0 && boolTicker == 0)
	{ 
		for(int i = 0; i < arraySize; i++)
		{
			if(i == idx)
			{
				strcpy(ffname, totalDirectoryList[i]);
			}
		}
		get_stock_values(ffname, &(corp->data), &(corp->date), str_delim, &corp->data_rows, &corp->data_cols);
		corp->date_length = corp->data_cols;
		corp->stats = (Stats *) malloc(corp->data_rows*sizeof(Stats));
		
		
		//search Comp2 File
		fgets(line, 1023, compFile);
		fgets(line, 1023, compFile);
		while(!feof(compFile))
		{
			tok = strtok(line, &comp_delim);
			to_upper(tickers[idx]);

			if(strcmp(tok, tickers[idx]) == 0)
			{
				
				//corp name abreviation.
				strcpy(corp->ticker, tok);
				
				//corp name
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				strcpy(corp->name, tok);

				//corp sector
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				strcpy(corp->sector, tok);

				//corp industry
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				strcpy(corp->industry, tok);
				
				break;
			}
			
			fgets(line, 1023, compFile);
		
		}
		
		get_all_stats(corp->stats, corp->data, corp->data_rows, corp->data_cols);
		print_stats_table(fin, corp);
		
		free_2d_char(totalDirectoryList, arraySize);
		free_2d_char(totalTickers, arraySize);
		free_2d_char(tickers, directorySize);
	}
	//if a combination was entered.
	else if(boolDirectory == 1 && singleCount !=0)
	{
		for(int i = 0; i < arraySize; i++)
			{
				if(i == idx)
				{
					strcpy(ffname, totalDirectoryList[i]);
				}
			}
			
			//search Comp2 File
		fgets(line, 1023, compFile);
		fgets(line, 1023, compFile);
		while(!feof(compFile))
		{
			tok = strtok(line, &comp_delim);
			to_upper(totalTickers[idx]);

			if(strcmp(tok, totalTickers[idx]) == 0)
			{
				
				//corp name abreviation.
				strcpy(corp->ticker, tok);
				
				//corp name
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				strcpy(corp->name, tok);

				//corp sector
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				strcpy(corp->sector, tok);

				//corp industry
				tok = strtok(NULL, &comp_delim);
				tok = strtok(NULL, &comp_delim);
				strcpy(corp->industry, tok);
				
				break;
			}
			
			fgets(line, 1023, compFile);
		
		}
			get_stock_values(ffname, &(corp->data), &(corp->date), str_delim, &corp->data_rows, &corp->data_cols);
			corp->date_length = corp->data_cols;
			corp->stats = (Stats *) malloc(corp->data_rows*sizeof(Stats));
			get_all_stats(corp->stats, corp->data, corp->data_rows, corp->data_cols);
			print_stats_table(fin, corp);
			
			free_2d_char(totalDirectoryList, arraySize);
			free_2d_char(totalTickers, arraySize);
			free_2d_char(tickers, directorySize);
			free_2d_char(singleFileList, singleCount);

	}

	free(corp);
	free(cmdLineLocation);
	free(singleCmdLocation);
	fclose(fin);
	fclose(compFile);
    return EXIT_SUCCESS;
    
}


//function to print directory tickers and files
void printDirectoryInfo(FILE *fp, char **cmdLineArray, char **fileArray, char **tickerArray, int size, int boolTik, int dirLoc, int count, int printCount)
{
	if(printCount < 1)
	{
		fprintf(fp, "\tData File\t\t\tTicker\n");
		fprintf(fp, "--------------------------------------------------\n");
	}
	
	if(boolTik == 1)
	{
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < count; j++)
			{
				if(strstr(fileArray[i], tickerArray[j]) != NULL)
				{
					fprintf(fp, "%s%s\t\t%s\n", cmdLineArray[dirLoc], fileArray[i], tickerArray[j]);
					
				}
			}
		}
	}
	else
	{
	
		for(int i = 0; i < size; i++)
		{
			fprintf(fp, "%s%s\t\t%s\n", cmdLineArray[dirLoc], fileArray[i], tickerArray[i]);
		}
	}
}

//function to print manually entered file locations and tickers
void printSingleEntry(FILE *fp, int printCount, char *cmdLineArray)
{
	char temp[1024];
	char *last;
	strcpy(temp, cmdLineArray);
	
	last = strrchr(temp, '/');
	strcpy(temp, strtok(last + 1, "."));
	
	if(printCount < 1)
	{
		fprintf(fp, "\tData File\t\t\tTicker\n");
		fprintf(fp, "--------------------------------------------------\n");
	}
	
	fprintf(fp, "%s\t\t%s\n", cmdLineArray, temp);
}




