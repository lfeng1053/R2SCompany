#include <stdio.h>
int main(){
	int num;
	printf("Enter a three-digit number: ");
	scanf("%d", &num);
	
	printf("Sum of digits of %d is: %d" , num, (num/100) + (num/10)%10 + (num%10));
}
