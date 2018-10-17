#ifndef UTILS_H
#define UTILS_H

//Returns the number of entries separated by a comma token.
int get_num_tokens(const char str[], char delim[]);

//Returns the array with the data input from the command line.
void get_tokens_array(const char str[], float array[], int size, char delim[]);

#endif
