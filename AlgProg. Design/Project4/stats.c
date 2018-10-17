#include <limits.h>
#include "stats.h"


// sorts the values of an array according to order
void  sort (float output[], const int  size, char order)
{
    int  i, j;
    float temp;

    if (order == 'a' || order == 'A')
    {
        for ( i = 0;  i < size - 1;  ++i )
            for ( j = i + 1;  j < size;  ++j )
                if ( output[i] > output[j] )
                {
                    temp = output[i];
                    output[i] = output[j];
                    output[j] = temp;
                }
    }
    else if (order == 'd' || order == 'D')
    {
        for ( i = 0;  i < size - 1;  ++i )
            for ( j = i + 1;  j < size;  ++j )
                if ( output[i] < output[j] )
                {
                    temp = output[i];
                    output[i] = output[j];
                    output[j] = temp;
                }
    }
    else
		return;

}

// calculates the mean of the elements of an array
float get_average(const float array[], int size)
{
    int i;
    float sum = 0.0;

    for (i = 0; i < size; i++)
        sum += array[i];

    sum /= size;

    return sum;
}

// calculates the variance of the emelemts of an array
// this function calls the get_average to get the mean
float get_variance(const float array[], int size)
{
    int i;
    float sum = 0.0;

    float mean = get_average(array, size);


    for (i = 0; i < size; i++)
        sum += array[i] * array[i];

    sum = sum/size - mean*mean;

    return sum;

}

// gets the median of an array after it sorts it
float get_median(const float array[], int size)
{
    int i;
    float temp_array[size]; // temp array tp be manipulated
    float median;

    // copy oroginal array to the temp array
    for (i = 0; i < size; i++)
        temp_array[i] = array[i];

    sort(temp_array, size, 'a');

    if (size % 2 == 0)
        median = (temp_array[size/2] + temp_array[size/2-1])/2.0;
    else
        median = temp_array[size/2];

   return median;
}

// finds the maximum value of the elements of an array
float get_max(const float array[], int size)
{
    int i;
    float max = array[0];

    for (i = 0; i < size; i++)
        if (array[i] >= max)
            max = array[i];

    return max;
}


// finds the minimum value of the elements of an array
float get_min(const float array[], int size)
{
    int i;
    float min = array[0];

    for (i = 0; i < size; i++)
        if (array[i] <= min)
            min = array[i];

    return min;

}
