/**
 *
 *
 */


#include <stdio.h>
#include <limits.h>




int main(int argc, char* argv[])
{


    int num_stocks = 1, 
        real_num_stocks = 0; // handles the negative stock values


    float min_value = (float) LONG_MAX, 
          max_value = (float) LONG_MIN,
          mean = 0.0f,
          variance = 0.0f,
          stock_price=1.0f;

    char know_num_stocks = 'n';
    
    printf("Do you know how many stocks prices you have?  ");
    scanf("%c", &know_num_stocks);

    if (know_num_stocks == 'y' || know_num_stocks == 'Y')
    {
        printf("How many?: ");
        scanf("%d", &num_stocks);


        if (num_stocks == 1)
        {
            // not much to do. the variance is zero so no need to reassign it
            scanf("%f", &stock_price);
            mean = stock_price;
            min_value = stock_price;
            max_value = stock_price;
        }
        else 
        {
            for (int i = 1; i <= num_stocks; i++)
            {
                printf("Please enter stock price #%d:  ", i);
                scanf("%f", &stock_price);

                if (stock_price < 0)
                    continue;

                // update good stock price count and accumulate sums per formulas
                real_num_stocks++;
                mean += stock_price;
                variance += stock_price * stock_price;

                // update the min price
                if (stock_price <= min_value)
                    min_value = stock_price;

                // update the max price
                if (stock_price >= max_value)
                    max_value = stock_price;
            }
        }
        
        // computation can be done here (uncomment next two lines)
        //mean /= real_num_stocks;
        //variance = variance/real_num_stocks - mean * mean;

    }
    else if (know_num_stocks == 'n' || know_num_stocks == 'N')
    {
        printf("Ok. Enter the stock prices one at a time (negative price terminates the input)\n");
        num_stocks = 0;
        
        for (;;)
        {
            printf("Please enter stock price #%d:  ", num_stocks + 1);
            scanf("%f", &stock_price);

            if (stock_price >= 0)
            {
                real_num_stocks++;
                mean += stock_price;
                variance += stock_price * stock_price;

                // update the min price
                if (stock_price <= min_value)
                    min_value = stock_price;

                // update the max price
                if (stock_price >= max_value)
                    max_value = stock_price;
                
            }
            else
                break;
        }

        // computation can be done here (uncomment next two lines)
        //mean /= real_num_stocks;
        //variance = variance/real_num_stocks - mean * mean;
    }
    else
        printf("Get out while you're ahead\n");
    
    
    // finish the computation here (uncomment above if you comment this)
    if (real_num_stocks == 0)
        printf("You did not enter anything meaningful. Run the program again. Bye!\n\n");
    else
    {
        mean /= real_num_stocks;
        variance = variance/real_num_stocks - mean * mean;


        printf("Mean = $%.2f\n", mean);
        printf("Variance = $%.2f\n", variance);
        printf("Max = $%.2f\n", max_value);
        printf("Min = $%.2f\n", min_value);
    }    
    
    return 0;

}
