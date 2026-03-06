#include <stdio.h>

int main(){
	int n;
	long long factorial = 1;
	printf("Enter number: ");
	scanf("%d", &n);
	if(n < 0){
		printf("Error");
	}
	for(int i = 1; i <= n; i++){
		factorial*=i;
	}
	printf("%d! = %lld", n, factorial);
}
