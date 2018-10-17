#ifndef COMMON_H
#define COMMON_H

typedef struct {
    float min,
          max,
          mean,
          stddev, // sqrt of the variance
          median;
} Stats; 

#endif
