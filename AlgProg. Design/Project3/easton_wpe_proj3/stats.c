/*
 * wesley easton
 * 15 October 2017
 * 
 * Function definitions for the calculations. 
 * 
*/


//Function that calculates and returns the average for the stocks.
float get_average(const float data[], const int size)
{
	int i = 0;
	float average = 0;
	
	for(i = 0; i < size; i++)
	{
		average += data[i];
	}
	
	average /= size;
	
	return average;
}

//Function that calculates and returns the variance for the stocks.
float get_variance(const float data[], const int size, const float mean)
{
	int i = 0;
	float variance = 0;
	
	for(i = 0; i < size; i++)
	{
		variance += (data[i] * data[i]);
	}
	
	variance = (variance / i) - (mean * mean);
	
	return variance;
}

////Function that calculates and returns the max stock.
float get_max(const float data[], const int size)
{
	int max = 0;
	int i = 0;
	
	if(max ==0)
	{
		max = data[0];
	}
	
	for(i = 0; i < (size); i++)
	{
		if(data[i] > max)
		{
			max = data[i];
		}	
	}
	
	return max;
}

//Function that calculates and returns the min stock.
float get_min(const float data[], const int size)
{
	int min = 0;
	int i = 0;
	
	if(min ==0)
	{
		min = data[0];
	}
	
	for(i = 0; i < (size); i++)
	{
		if(data[i] < min)
		{
			min = data[i];
		}	
	}
	
	return min;
}


//Function that sorts the stocks according to user input.
void sort(const float input[], float output[], const int size, const char order)
{
	int i = 0;
	int j = 0;
	float temp = 0;

	//sorts array in ascending order.
	if(order == 'a' || order == 'A')
	{
		//transfer input over to output
		for(i = 0; i < size; i++)
		{
			output[i] = input[i];
		}
		
		//For loop to sort array in ascending order.
		for(i = 0; i < (size - 1); i++)
		{
			for(j = i + 1; j < (size); j++)
			{
				if(output[i] > output[j])
				{
					temp = output[i];
					output[i] = output[j];
					output[j] = temp;
				}
			}
		}
	}//sorts array in descending order
	else if(order =='d' || order =='D')
	{
		//transfer input over to output
		for(i = 0; i < size; i++)
		{
			output[i] = input[i];
		}
		
		//For loop to sort array in ascending order.
		for(i = 0; i < (size - 1); i++)
		{
			for(j = i + 1; j < (size); j++)
			{
				if(output[i] < output[j])
				{
					temp = output[i];
					output[i] = output[j];
					output[j] = temp;
				}
			}
		}
	}
	else//sorts the array regardless of user decision for median calculation.
	{
		for(i = 0; i < size; i++)
		{
			output[i] = input[i];
		}
		
		//For loop to sort array in ascending order.
		for(i = 0; i < (size - 1); i++)
		{
			for(j = i + 1; j < (size); j++)
			{
				if(output[i] > output[j])
				{
					temp = output[i];
					output[i] = output[j];
					output[j] = temp;
				}
			}
		}
	}
}

////Function that calculates and returns the median for the stocks.
float get_median(const float input[], const int size)
{
	float median = 0;
	int middleNum1 = 0;
	int middleNum2 = 0;
	
	if(size % 2 != 0)
	{
		middleNum1 = (size / 2); 
		
		median = input[middleNum1];
	}
	else
	{
		middleNum1 = size / 2;
		middleNum2 = (size / 2) - 1;
		
		median = (input[middleNum1] + input[middleNum2]) / 2;
	}
	
	return median;
}
	
	
	
	
	
