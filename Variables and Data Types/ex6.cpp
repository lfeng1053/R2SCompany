#include <stdio.h>
#include <math.h>
float Semi_Perimeter(float a,float b, float c){
	float s = (a +b + c) / 2;
	return s;
}

float Area(float a, float b, float c){
	float s = Semi_Perimeter(a, b, c);
	return sqrt(s * (s - a) * (s - b) * (s - c));
}
int main (){
	float a, b, c;
	printf("Enter triangle lengths:"); scanf("%f%f%f", &a, &b, &c);
	printf("Semi Perimeter = %f\n", Semi_Perimeter(a, b, c));
	printf("Area = %f", Area(a, b, c));
}
