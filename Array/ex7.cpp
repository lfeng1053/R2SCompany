#include <stdio.h>

int isPrime(int n) {
    if (n < 2) return 0;
    for (int i = 2; i * i <= n; i++)
        if (n % i == 0) return 0;
    return 1;
}

int main() {
    int n, i;
    printf("Enter array size: ");
    scanf("%d", &n);
    
    int arr[n];
    for (i = 0; i < n; i++) scanf("%d", &arr[i]);
    printf("Prime numbers: ");
    for (i = 0; i < n; i++)
        if (isPrime(arr[i])) printf("%d ", arr[i]);
    printf("\n");
}
