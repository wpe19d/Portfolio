/*
 * wesley easton
 * 15 October 2017
 * 
 * This program allows the user to enter multiples stocks for various calculations.
 * The calculations are: min stock, max stock, average stock, stock variance, and stock median.
 * 
*/

#include <stdio.h>
#include "io.h"
#include "stats.h"

//Main program
int main()
{
	int size = 0;
	float mean = 0;
	float median = 0;
	float min = 0;
	float max = 0;
	float variance = 0;
	char userChoice = 'n';
	
	//Greeting message
	print_greeting();
	
	printf("Please enter the number of stocks you would like to enter: ");
	scanf("%d", &size);
	printf("\n");
	
	float stockArray[size];
	float sortedArray[size];
	
	//Read in user entered stocks.
	read_data(stockArray, size);
	
	//gets the user's sort type choice
	userChoice = read_sort(userChoice);
	
	//Sorts the stocks according to user choice.
	sort(stockArray, sortedArray, size, userChoice);
	
	//Finds the average for the stocks.
	mean = get_average(stockArray, size);
	
	//Finds the stock variance.
	variance = get_variance(stockArray, size, mean);
	
	//Finds the stock median according.
	median = get_median(sortedArray, size);
	
	//Finds the max stock value.
	max = get_max(stockArray, size);
	
	//Finds the minimum stock value.
	min = get_min(stockArray, size);
	
	//if user decides no sorting, it prints the sotcks in user entered order.  If user decided sorting, it prints the stocks in
	//user selected order.
	if(userChoice == 'n')
	{
		print_results(stockArray, size, mean, median, max, min, variance);
	}
	else
	{
		print_results(sortedArray, size, mean, median, max, min, variance);
	}
	
	return 0;
}
