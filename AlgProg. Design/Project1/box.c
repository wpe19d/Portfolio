/*
 *Wesley Easton
 * 11 September 2017 
 * 
 * This program calculates the area, volume, and surface area of a box.
 * 
*/

#include <stdio.h>



int main()
{
	//Variable declarations.
	double length = 0;
	double width = 0;
	double height = 0;
	double area = 0;
	double volume = 0;
	double surfaceArea = 0;
	
	//Print/Scan statements to ask user for rectangle dimensions.
	printf("Please enter the length of the rectangle: ");
	scanf("%lf", &length);

	printf("Please enter the width of the rectangle: ");
	scanf("%lf", &width);

	printf("Please enter the height of the rectangle: ");
	scanf("%lf", &height);
	
	//Formulas to calculate area, volume, surface area.
	area = length * width;
	volume = area * height;
	surfaceArea = (2 * length * width) + (2 * width * height) + (2 * length * height);

	//Displays the answers.
	printf("The area of the rectangle is %.3e m^2\n", area);
	printf("The volume of the rectangle is %.3e m^3\n", volume);
	printf("The surface area of the rectangle is %.3e m^2\n", surfaceArea);


	return 0;

}
