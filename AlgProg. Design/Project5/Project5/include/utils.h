#ifndef UTILS_H
#define UTILS_H

#include <string.h>
#include <stdlib.h>
#include "common.h"

int get_num_tokens(const char str[], char delim);
void get_tokens_array(const char str[], float array[], int size, char delim);
void get_stats(Stats *stats, const float array[], int size);

#endif
