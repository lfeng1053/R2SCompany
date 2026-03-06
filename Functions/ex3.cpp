#include <stdio.h>
#include <stdbool.h>

bool isValid(double grade) {
    if (grade > 10 || grade < 0) {
        return false;
    }
    return true;
}

void getInput(double marks[]) {
  const  char *course[] = {"database", "c", "oop", "java"};

    for (int i = 0; i < 4; i++) {
        do {
            printf(" %s: ", course[i]);
            scanf("%lf", &marks[i]);
            if (!isValid(marks[i])) {
                printf("Enter grade again (0 <= grade <= 10): ");
            }
        } while (!isValid(marks[i]));
    }
}

double calculateGPA(double marks[]) {
    double sum = 0;
    for (int i = 0; i < 4; i++) {
        sum += marks[i];
    }
    return (sum / 4.0 / 10.0) * 4.0; 
}

const char* getRank(double gpa) {
    if (gpa >= 3.60) return "Excellent";
    if (gpa >= 3.20) return "Good";
    if (gpa >= 2.50) return "Fair";
    if (gpa >= 2.00) return "Average";
    return "Weak";
}

void displayMenu() {
    
    printf("1. Input mark\n");
    printf("2. Calculate GPA\n");
    printf("3. Show Rank\n");
    printf("4. Quit\n");

}

int main() {
    double marks[4] = {0};
    double gpa = 0;
    int choice;
    bool hasMarks = false;

    for (;;) {
        displayMenu();
        if (scanf("%d", &choice) != 1) {
            while (getchar() != '\n') {}
            printf("Enter choice");
            continue;
        }

        switch (choice) {
            case 1:
                getInput(marks);
                gpa = calculateGPA(marks);
                hasMarks = true;
                break;
            case 2:
                if (!hasMarks) printf("Enter grade first!\n");
                else printf("\nGPA: %.2f\n", gpa);
                break;
            case 3:
                if (!hasMarks) printf("Enter grade first\n");
                else printf("\nRank: %s\n", getRank(gpa));
                break;
            case 4:
                printf("Quit.\n");
                return 0;
            default:
                printf("Enter choice. \n");
                break;
        }
    }
}
