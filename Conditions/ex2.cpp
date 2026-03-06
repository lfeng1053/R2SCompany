#include <stdio.h>
#include <math.h>
int main(){
	double a, b;
	printf("Enter 2 number: ");
	
	scanf("%lf%lf", &a, &b);
	double diff = abs(a-b);
	
	if(diff == a){
		printf("Difference is equal to value: %lf", a);
	} else if (diff == b){
		printf("Difference is equal to value: %lf", b);
	} else {
		printf("Difference is no euqal to any of the values enter");
	}
}
