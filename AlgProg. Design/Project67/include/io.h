#ifndef IO_H
#define IO_H
#include <string.h>
#include "common.h"

// prompt the user to input data on the command line to be read into the program
// array: array of floats of length size
// size: length of array
void read_cmdln_array(float *array, int size);

// prints the string greeting to the stream fp
// fp: valid file or stdout stream
// greeting: a null-terminated string with the message
void print_info(FILE *fp, const char *greeting);

// prints the elements of an array of floats to the stream fp separated by tabs
// array: array of floats of length size
// size: the length of array
void print_float_array(FILE *fp, const float array[], int size);

// prints the elements of an array of ints to the stream fp separated by tabs
// fp: valid file or stdout stream
// array: array of ints of length size
// size: the length of array
void print_int_array(FILE *fp, const int array[], int size);


// prints the stats of to the stream fp
// fp: valid file or stdout stream
// stats: stats structure containing the stats for an array of numbers
void print_stats(FILE *fp, const Stats *stats);


// prints the elements of an array of strings, each on a new line
// fp: valid file or stdout stream
// strings: array of strings of length
// size: the length of strings
void print_strings(FILE *fp, char **strings, int size);


// prints the elements of a 2d array of floats to the stream fp in matrix format
// fp: valid file or stdout stream
// array: 2d array of floats of dimension rowsxcols
// size: the number of rows of array
// cols: the number of columns of array
void print_2d_float_array(FILE *fp, const float **array, int rows, int cols);


// prints the statisitcs of stock data for corporation corp
// fp: valid file or stdout stream
// corp: corporation structure populated with data and information
// sample table format:
/*
-------------------------------------------------------------
                        My Corp (abc)
                          computers
                         electronics
                      3-Oct-17 to 13-Oct-17
-------------------------------------------------------------
          Open($)   High($)   Low($)    Close($)  Volume
-------------------------------------------------------------
Mean      1.73      1.85      1.65      1.75      882442.31
Median    1.72      1.85      1.63      1.72      415087.00
Min       1.43      1.72      1.43      1.64      192615.00
Max       1.86      2.05      1.75      1.90      3427365.00
StdDevP   0.13      0.10      0.09      0.09      1005288.94
StdDevS   0.14      0.11      0.10      0.09      1066269.88
=============================================================
*/
void print_stats_table(FILE *fp, Corporation *corp);
#endif
