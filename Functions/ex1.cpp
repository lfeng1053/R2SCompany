#include <stdio.h>
#define PI 3.1314
double Area(double radius){
	return PI * radius * radius;
}

double Perimeter(double radius){
	return PI * 2 * radius;
}
int main(){
	double radius;
	printf("Enter radius: ");
	scanf("%lf", &radius);
	printf("Perimeter: %lf ", Perimeter(radius));
	printf("Area: %lf", Area(radius));
}



