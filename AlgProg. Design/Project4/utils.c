#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "utils.h"


//Function that counts the number of tokens in a string and returns that number as an int.
int get_num_tokens(const char str[], char delim[])
{
	int numTokens = 0;
	char cmdLine[1024];
	strcpy(cmdLine, str);
	
	char *userInputCount = cmdLine;
	char *tokenCount = strtok(userInputCount, delim);	
	 
	while(tokenCount) 
    {
         tokenCount = strtok(NULL, delim);
         
         numTokens++;
    }
    
    return numTokens;
}

//Function that reads in a string, separates the string into tokens separated by a specified delimeter and reads them into an array.
void get_tokens_array(const char str[], float array[], int size, char delim[])
{
	int i = 0;
	char cmdLine[1024];
	strcpy(cmdLine, str);
	
	char *userInput = cmdLine;
	char *token = strtok(userInput, delim);
	
	while(token) 
    {
         array[i] = atof(token);
        
         token = strtok(NULL, delim);
                  
         i++;
         
    }
}
