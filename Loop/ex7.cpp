#include <stdio.h>

int main(){
	int password;
	
	int n = 3;
	while(n--){
		printf("Enter password: ");
		scanf("%d", &password);
		if(password == 12345){
			printf("Correct password");
			break;
		} else {
			printf("Invalid password\n");
		} 
		if (n == 0) printf("End of try");
		
	}
}
