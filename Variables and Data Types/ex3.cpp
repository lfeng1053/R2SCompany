#include <stdio.h>
#define BASIC_SALARY 12000
#define DA (0.12 * BASIC_SALARY)
#define HRA 150
#define TA 120
#define OTHERS 450
#define PF (0.14 * BASIC_SALARY)
#define IT  (0.15 * BASIC_SALARY)
int main(){
	 
	printf("Tax cuts \nPF: %lf \nIT: %lf\n", PF, IT);
	printf("Net salary: %lf", BASIC_SALARY + DA + HRA + TA + OTHERS - (PF + IT));
}
