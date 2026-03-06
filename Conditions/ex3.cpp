#include <stdio.h>

int main(){
	double salary;
	printf("Enter salary: ");
	scanf("%lf", &salary);
	
	char grade;
	printf("Enter grade: ");
	scanf(" %c", &grade);
	switch(grade){
		case 'A':
		case 'a':
			salary += 300;
			break;
		case 'B':
		case 'b':
			salary += 200;
			break;
		default:
			salary += 100;
			break;
	}
	
	printf("Salary at the end of month: %lf", salary);
}
