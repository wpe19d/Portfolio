/*
 * wesley easton
 * 15 October 2017
 * 
 * User I/O function definitions.
 * 
*/

#include <stdio.h>

//Function that asks the user for the number of stocks they would like to enter.
void read_data(float array[], int size)
{
	int i = 0;
	// read data
	printf("Please enter stocks prices\n");
	printf("--------------------------\n\n");
	for (i = 0; i < size; i++)
	{
		printf("Enter entry #%d: ", i+1);
		scanf("%f", &array[i]);
	}
}

//Funtion that prints all of the user's statistical results.
void print_results(const float array[], int size, float mean, float median, float max, float min, float variance)
{
	printf("Your stocks\n");
	printf("-----------\n");
	
	
	//Will need to modify these
	for(int i = 0; i < size; i++)
	{
		printf("$%.2f", array[i]);
		printf("\n");
	}
	
	printf("\n");
	
	printf("The stock mean is: $%.2f\n", mean);
	
	printf("The stock median is: $%.2f\n", median);
	
	
	printf("The stock min is: $%.2f\n", min);
	
	printf("The stock max is: $%.2f\n", max);
	
	printf("The stock variance is: $%.2f\n", variance);
}
	
//Function that asks for user's sort order deciation.
char read_sort(char choice)
{
	printf("If you would like to sort your stocks, please enter 'a' for ascending or 'd' for descending.\n");
	printf("Enter 'n' if you do not want your stocks to be sorted\n");
	printf("Sort stocks? a/d/n:");
	scanf(" %c", &choice);
	printf("\n");
	
	return choice;
}

//Displays the gretting message.
void print_greeting()
{
	printf("\n-------------------------Welcome to the Stock Calculator------------------------\n\n\n\n");
}

