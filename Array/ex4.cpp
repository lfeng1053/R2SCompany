#include<stdio.h>

int main() {
    int n, i;
    printf("Enter array size: ");
    scanf("%d", &n);
    
    int arr[n];
    for (i = 0; i < n; i++) scanf("%d", &arr[i]);
    printf("Elements divisible by 3 or 5: ");
    for (i = 0; i < n; i++)
        if (arr[i] % 3 == 0 || arr[i] % 5 == 0)
            printf("%d ", arr[i]);
    printf("\n");
}
