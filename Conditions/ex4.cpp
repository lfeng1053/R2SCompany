#include <stdio.h>

int main(){
	int mark;
	scanf("%d", &mark);
	char grade;
	if (mark >= 75){
		grade = 'A';
	} else if (mark >= 60 && mark < 75){
		grade = 'B';
	} else if (mark >= 45 && mark < 60){
		grade = 'C';
	} else if (mark >= 35 && mark < 45){
		grade = 'D';
	} else if (mark < 35){
		grade = 'E';
	}
	
	printf("Grade of student is: %c", grade);
}
