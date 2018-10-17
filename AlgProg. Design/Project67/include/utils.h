#ifndef UTILS_H
#define UTILS_H

#include <string.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>
#include <dirent.h>
#include <stdio.h>
#include "common.h"

// returns the number of tokens in a dtring str delimited by delim
// str: numm-terminated string delimited by delim
// delim: character delimiter
int get_num_tokens(const char str[], char delim);

// populate array with the tokens from string str delimited by delim
//str: null-terminated string delimited by delim
// array: array of floats of length size to be populated by the tokens
// size: length of array
// delim: delimiter of string str
void get_tokens_array(const char str[], float array[], int size, char delim);


// populates the Stats structure stats with the statistics for array
// stats: structure containing the statistics for array
// array: array of floats of length size
// size: length of array
void get_stats(Stats *stats, const float array[], int size);


// returns a dynamically allocated double pointer to float of size rows x cols
float **allocate_2d_float(int rows, int cols);

// returns a dynamically allocated double pointer to int of size rows x cols
int **allocate_2d_int(int rows, int cols);

// returns a dynamically allocated  double pointer to char of size rows x cols (array of strings)
char **allocate_2d_char(int rows, int cols);

// frees a 2d dynamically alocated array
// array: 2d dynamically allocated array of floats of size rows x cols
// rows: number of rows of array
int free_2d_float(float **array, int rows);


// frees a 2d dynamically alocated array
// array: 2d dynamically allocated array of chars of size rows x cols
// rows: number of rows of array
int free_2d_char(char **array, int rows);

// frees a 2d dynamically alocated array
// array: 2d dynamically allocated array of ints of size rows x cols
// rows: number of rows of array
int  free_2d_int(int **array, int rows);


// return 1 if path is a regular file (not a link), 0 otherwise
int is_reg_file(const char *path);

// return 1 if path is a directoy, 0 otherwis
int is_dir(const char *path);


// populates and array inplace with list of regular files in a directory
// path: directory containing the files
// files_list: 2d array of strings holing the names (not absolute or relative)
//             of the files in path
// size: the number of regular files in path
int get_reg_files_list2(const char *path, char ***files_list, int *size);


// returns an array of regular file names (stings) in a directory
// path: directory containing the files
// size: the number of regular files in path
char **get_reg_files_list(const char *path,  int *size);


// return the number of regular files in a directory
// path: directory containing the files
int get_num_files(const char *path);


// popuates data with the daily stock values and their corresponding dates
// fname: the name of the file containing the data. the format of the file is:
//        Date,Open,High,Low,Close,Volume
//        arranged in column format
// data: array that hold the Open, High, Low, Close, and Volume data of size
//       rows x cols. The cols of the file are the rows of the array
// date: the dates if the daily stock values. That's the first column of the
//       file of length size. The dates are of type string.
// rows : the number columns in the file.he number of cols of data. This is assigned inplace
// cols : the number of non empty lines in the file. This is assigned in inplace
int get_stock_values(const char *fname, float ***data, char ***date, char delim,
    int *rows, int *cols);

// converts inplace all the characters of str from lower to upper case
void to_upper(char *str);

// converts inplace all the characters of str from upper to lower case
void to_lower(char *str);

// populates the Stats stucture stats with statistics of the 2d array data
// stats: array of structures of size cols that hold the statistics for
//        each row of data
// data: 2d array of size rowsxcols where each row is an array of the data of
//       the same context
// rows: the number of rows of the 2d array data
// cols: the number of columns of the 2d array data
void get_all_stats(Stats *stats, const float **data, int rows, int cols);


#endif
