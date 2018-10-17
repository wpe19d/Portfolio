#ifndef IO_H
#define IO_H
#include "common.h"
void read_array(float *array, int size);
void print_greeting(void);
void print_array(const float array[], int size);
void print_results(const Stats *stats, const float array[], int size);
void print_stats(const Stats *stats);
#endif
