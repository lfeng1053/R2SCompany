#include <stdio.h>
#define PI 3.14159265358979323846
int main (){
	double radius, area, perimeter;
	printf("Enter radius: ");
	scanf("%lf", &radius);
	area = radius *radius * PI;
	perimeter = radius * 2 * PI;
	
	printf("Area = %.2lf \n", area);
	printf("Perimeter = %.2lf \n", perimeter);
}
