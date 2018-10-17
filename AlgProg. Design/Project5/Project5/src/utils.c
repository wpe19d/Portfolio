#include <math.h>
#include "utils.h"
#include "stats.h"
int get_num_tokens(const char str[], char delim)
{
    int counter = 0;
    char temp[1024];
    strcpy(temp, str);

    char *token = strtok(temp, &delim);

    while(token)
    {
        counter++;
        token = strtok(NULL, &delim);
    }

    return counter;
}

void get_tokens_array(const char str[], float array[], int size, char delim)
{
    char temp[1024];
    strcpy(temp, str);
    char *token = strtok(temp, &delim);
    int idx = 0;
    array[idx] = atof(token);

    while(token && idx < size - 1)
    {
        token = strtok(NULL, &delim);
        array[++idx] = atof(token);
    }

}

void get_stats(Stats *stats, const float array[], int size)
{
    // get the stats
    stats->mean = get_average(array, size);
    stats->stddev= sqrt(get_variance(array, size));
    stats->min = get_min(array, size);
    stats->max = get_max(array, size);
    stats->median = get_median(array, size);
}
