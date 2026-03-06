#include <stdio.h>

int main(){
	double a, b;
	printf("Enter 2 number: ");
	scanf("%lf%lf", &a, &b);
	
	if(a * b >= 1000){
		printf("The product of the two numbers is equal to or greater than 1000");
	} else {
		printf("The product of the two numbers is not equal to nor greater than 1000");
	}
}
