#include <stdio.h>

int main() {
    int n;
    printf("How many numbers do you wnat? ");
    scanf("%d", &n);

    int a = 1, b = 1;
    for (int i = 1; i <= n; i++) {
        printf("%d ", a);
        int temp = a + b;
        a = b;
        b = temp;
    }
    return 0;
}
