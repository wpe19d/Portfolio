#ifndef STATS_H
#define STATS_H

// sorts an array in place of floats in the specified order
// array: array of length size to be sorted
// size: length of stats_array
// order: 'a' for ascending; 'd' for descending
void  sort (float array[], int  size, char order);

// returns the average of array
// array: array of length size
// size: length of stats_array
float get_average(const float array[], int size);

// returns the sample/population variance of array
// array: array of length size
// size: length of stats_array
// ps: 's' for sample variance; 'p' for population variance
float get_variance(const float array[], int size, char ps);


// returns the maximum value of array
// array: array of length size
// size: length of stats_array
float get_max(const float array[], int size);


// returns the minimum value of array
// array: array of length size
// size: length of stats_array
float get_min(const float array[], int size);

// returns the median of array. array is sorted by calling the sort funtion 1st
// array: array of length size
// size: length of stats_array
float get_median(const float array[], int size);
#endif
