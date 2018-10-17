#include <stdio.h>
#include "io.h"


// prompt the user for input and read the values into an array
void read_array(float array[], int size)
{
    int i = 0;
    for (i = 0; i < size; i++)
    {
        printf ("Please enter stock entry #%d: ", i+1);
        scanf("%f", &array[i]);
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
void print_results(const float array[], int size, float median,  float min, float max, float mean, float variance)
{

    printf("\nStock Results\n");
    printf("-------------\n");
    print_array(array, size);
    printf("median: $%.2f\n", median);
    printf("min: $%.2f\n", min);
    printf("max: $%.2f\n", max);
    printf("variance: $%.2f\n", variance);
    printf("mean: $%.2f\n", mean);

}
