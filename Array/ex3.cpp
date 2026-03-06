#include <stdio.h>

int main() {
    int n, i, x, count = 0;
    printf("Enter array size: ");
    scanf("%d", &n);
    
    int arr[n];
    for (i = 0; i < n; i++) scanf("%d", &arr[i]);
    
    printf("Enter x: ");
    scanf("%d", &x);
    
    for (i = 0; i < n; i++)
        if (arr[i] == x) count++;
    printf("%d appears %d time(s)\n", x, count);
}
