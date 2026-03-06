#include <stdio.h>

int main() {
    int n, i, sum = 0;
    
    printf("Enter array size: ");
    scanf("%d", &n);
    
    int arr[n];
    for (i = 0; i < n; i++) scanf("%d", &arr[i]);
    for (i = 0; i < n; i++)
        if (arr[i] % 2 != 0) sum += arr[i];
    printf("Sum of odd elements = %d\n", sum);
}
