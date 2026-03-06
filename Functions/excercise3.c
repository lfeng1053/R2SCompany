#include <stdio.h>

#define NUM_COURSES 4
#define MIN_GRADE 0.0
#define MAX_GRADE 10.0

const char *course_names[] = {"database", "c", "oop", "java"};

double grades[NUM_COURSES];
int grades_entered = 0; 

int input_one_grade(const char *name, double *out)
{
    double g;
    printf("Enter mark for %s (0-10): ", name);
    if (scanf("%lf", &g) != 1) {
        while (getchar() != '\n')
            ;
        return 0;
    }
    if (g < MIN_GRADE || g > MAX_GRADE) {
        printf("Invalid. Grade must be between %.1f and %.1f.\n", MIN_GRADE, MAX_GRADE);
        return 0;
    }
    *out = g;
    return 1;
}


void input_marks(void)
{
    int i;
    printf("\n--- Input marks ---\n");
    for (i = 0; i < NUM_COURSES; i++) {
        while (!input_one_grade(course_names[i], &grades[i]))
            ;
    }
    grades_entered = 1;
    printf("Marks saved.\n\n");
}


double calculate_gpa(void)
{
    double sum = 0;
    int i;
    for (i = 0; i < NUM_COURSES; i++)
        sum += grades[i];
    return (sum / NUM_COURSES / 10.0) * 4.0;
}


void display_gpa(void)
{
    if (!grades_entered) {
        printf("\nNo marks entered yet. Choose option 1 first.\n\n");
        return;
    }
    printf("\n--- GPA ---\n");
    printf("GPA (4-point scale): %.2f\n\n", calculate_gpa());
}


const char *get_rank(double gpa)
{
    if (gpa >= 3.60 && gpa <= 4.00) return "Excellent";
    if (gpa >= 3.20 && gpa < 3.60)  return "Good";
    if (gpa >= 2.50 && gpa < 3.20)  return "Fair";
    if (gpa >= 2.00 && gpa < 2.50)  return "Average";
    return "Weak";
}


void display_rank(void)
{
    double gpa;
    if (!grades_entered) {
        printf("\nNo marks entered yet. Choose option 1 first.\n\n");
        return;
    }
    gpa = calculate_gpa();
    printf("\n--- Rank ---\n");
    printf("GPA: %.2f -> Rank: %s\n\n", gpa, get_rank(gpa));
}

int main(void)
{
    int choice;

    for (;;) {
        printf("1. Input mark\n");
        printf("2. Display GPA\n");
        printf("3. Display Rank\n");
        printf("4. Quit\n");
        printf("Your choice: ");

        if (scanf("%d", &choice) != 1) {
            while (getchar() != '\n')
                ;
            printf("Invalid input.\n\n");
            continue;
        }

        switch (choice) {
            case 1:  input_marks();    break;
            case 2:  display_gpa();   break;
            case 3:  display_rank();  break;
            case 4:  printf("Bye.\n"); return 0;
            default: printf("Choose 1, 2, 3 or 4.\n\n"); break;
        }
    }
}
