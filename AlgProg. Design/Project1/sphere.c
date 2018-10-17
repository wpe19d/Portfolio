/*
 *Wesley Easton
 * 11 September 2017 
 * 
 * This program calculates the area, volume, and surface area of a sphere.
 * 
*/


#include <stdio.h>
#define PI 3.141592654

int main()
{

	double radius = 0;
	double area = 0;
	double volume = 0;
	double surfaceArea = 0;

	printf("Please enter the radius of the sphere: ");
	scanf("%lf", &radius);

	area = PI * (radius * radius);

	volume = (4.0 / 3) * PI * (radius * radius * radius);

	surfaceArea = 4 * area;

	printf("The area of the sphere is %.3e meters^2\n", area);
	printf("The volume of the sphere is %.3e meters^3\n", volume);

	printf("The surface area of the sphere is %.3e meters^2\n", surfaceArea);
	


	return 0;



}
