#include <stdio.h>

#define MAX 100

int arr[MAX];
int n = 0;

void inputArray() {
    printf("Enter number of elements (1-100): ");
    scanf("%d", &n);
    if (n < 1 || n > MAX) {
        printf("Invalid size! Must be between 1 and 100.\n");
        n = 0;
        return;
    }
    for (int i = 0; i < n; i++) {
        printf("Enter element [%d]: ", i + 1);
        scanf("%d", &arr[i]);
    }
    printf("Array inputted successfully.\n");
}

void outputArray() {
    if (n == 0) { printf("Array is empty.\n"); return; }
    printf("Array: ");
    for (int i = 0; i < n; i++)
        printf("%d ", arr[i]);
    printf("\n");
}

void printDescending() {
    if (n == 0) { printf("Array is empty.\n"); return; }
    int temp[MAX];
    for (int i = 0; i < n; i++) temp[i] = arr[i];

    for (int i = 0; i < n - 1; i++)
        for (int j = 0; j < n - i - 1; j++)
            if (temp[j] < temp[j + 1]) {
                int t = temp[j];
                temp[j] = temp[j + 1];
                temp[j + 1] = t;
            }

    printf("Array in descending order: ");
    for (int i = 0; i < n; i++)
        printf("%d ", temp[i]);
    printf("\n");
}

void checkAllOdd() {
    if (n == 0) { printf("Array is empty.\n"); return; }
    for (int i = 0; i < n; i++) {
        if (arr[i] % 2 == 0) {
            printf("Not all elements are odd.\n",);
            return;
        }
    }
    printf("All elements are odd.\n");
}

void searchValue() {
    if (n == 0) { printf("Array is empty.\n"); return; }
    int x, count = 0;
    printf("Enter value to search: ");
    scanf("%d", &x);
    for (int i = 0; i < n; i++)
        if (arr[i] == x) count++;
    printf("Value %d appears %d time(s) in the array.\n", x, count);
}

int isPrime(int num) {
    if (num < 2) return 0;
    for (int i = 2; i * i <= num; i++)
        if (num % i == 0) return 0;
    return 1;
}

void displayPrimes() {
    if (n == 0) { printf("Array is empty.\n"); return; }
    printf("Prime numbers in array: ");
    int found = 0;
    for (int i = 0; i < n; i++) {
        if (isPrime(arr[i])) {
            printf("%d ", arr[i]);
            found = 1;
        }
    }
    if (!found) printf("None");
    printf("\n");
}

void quit() {
    int confirm;
    printf("Enter 1 to exit: ");
    scanf("%d", &confirm);
    if (confirm == 1) {
        printf("Quit...\n");
        return;
    }
}

int main() {
    int choice;
    do {
        printf("1 - Input the array\n");
        printf("2 - Output the array\n");
        printf("3 - Print array in descending order\n");
        printf("4 - Check if all elements are odd\n");
        printf("5 - Search a value\n");
        printf("6 - Display prime numbers in array\n");
        printf("7 - Quit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1: inputArray();      break;
            case 2: outputArray();     break;
            case 3: printDescending(); break;
            case 4: checkAllOdd();     break;
            case 5: searchValue();     break;
            case 6: displayPrimes();   break;
            case 7: quit();            break;
            default: printf("Invalid choice. Please enter 1-7.\n");
        }
    } while (choice != 7);

    return 0;
}
