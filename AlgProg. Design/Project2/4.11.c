// Program to add the digits of a number

#include <stdio.h>

int main (void)
{
   unsigned long int number;
   int right_digit, sum = 0;

   printf ("Enter your number: \n");
   scanf ("%li", &number);

   while ( number != 0 ) 
   {
      right_digit = number % 10;
      sum += right_digit;
      number /= 10;
   }

   printf("%i\n", sum);

   return 0;
}

