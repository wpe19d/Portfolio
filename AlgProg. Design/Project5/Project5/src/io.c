#include <stdio.h>
#include "io.h"


// prompt the user for input and read the values into an array
void read_array(float *array, int size)
{
    int i = 0;
    for (i = 0; i < size; i++)
    {
        printf ("Please enter stock entry #%d: ", i+1);
        scanf("%f", array + i);
    }
}

// say hi to the user
void print_greeting(void)
{
    printf("\n-------------------------Welcome to the Stock Calculator-------------------------\n\n\n");
}

// display array values
void print_array(const float array[], int size)
{
    int i = 0;

    for (i = 0; i < size; i++)
        printf("%.2f ", array[i]);

    printf("\n\n");

}

// print the stat results including input data
void print_results(const Stats *stats, const float array[], int size)
{

    printf("\nInput data and the statistics for the stocks\n");
    print_array(array, size);
    print_stats(stats);

}

// print the stat results including input data
void print_stats(const Stats *stats)
{

    printf("\nStatistis for the Stocks\n");
    printf("%-10s $%.2f\n", "min:", stats->min);
    printf("%-10s $%.2f\n", "max:", stats->max);
    printf("%-10s $%.2f\n", "mean:", stats->mean);
    printf("%-10s $%.2f\n", "stddev:", stats->stddev);
    printf("%-10s $%.2f\n", "median:", stats->median);

}
