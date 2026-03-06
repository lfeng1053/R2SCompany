#include <stdio.h>

int main() {
    int n, i, count = 0;
    
    printf("Enter array size: ");
    scanf("%d", &n);
    
    int arr[n];
    for (i = 0; i < n; i++) scanf("%d", &arr[i]);
    for (i = 0; i < n; i++)
        if (arr[i] < 0) count++;
    printf("Number of negative elements = %d\n", count);
}
