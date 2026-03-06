#include <stdio.h>

int main() {
    int n, i;
    printf("Enter array size: ");
    scanf("%d", &n);
    
    int arr[n];
    for (i = 0; i < n; i++) {
        printf("arr[%d] = ", i);
        scanf("%d", &arr[i]);
    }
    
    int min = arr[0], max = arr[0];
    for (i = 1; i < n; i++) {
        if (arr[i] < min) min = arr[i];
        if (arr[i] > max) max = arr[i];
    }
    printf("Min = %d\nMax = %d\n", min, max);
}
