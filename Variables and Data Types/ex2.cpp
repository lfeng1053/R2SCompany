#include <stdio.h>

int main(){
	int age;
	double salary;
	
	printf("Enter your salary: \n");
	scanf("%lf", &salary);
	printf("Enter your age: \n");
	scanf("%d", &age);
	
	printf("Your salary: %lf \nYour age: %d", salary, age);
}
