#include <stdio.h>

int main() {
    int n, i, allOdd = 1;
    printf("Enter array size: ");
    scanf("%d", &n);
    
    int arr[n];
    for (i = 0; i < n; i++) scanf("%d", &arr[i]);
    for (i = 0; i < n; i++)
        if (arr[i] % 2 == 0) { allOdd = 0; break; }
    printf(allOdd ? "All elements are odd\n" : "Not all elements are odd\n");
}
