#include <stdio.h>

int main() {
    char letter;
    printf("Enter a letter: ");
    scanf(" %c", &letter);

    switch (letter) {
        case 'B':
        case 'b':
            printf("Basic\n");
            break;
        case 'C':
        case 'c':
            printf("Cobol\n");
            break;
        case 'F':
        case 'f':
            printf("Fortran\n");
            break;
        case 'P':
        case 'p':
            printf("Pascal\n");
            break;
        case 'V':
        case 'v':
            printf("Visual C++\n");
            break;
        default:
            printf("No language found for '%c'\n", letter);
            break;
    }
}
