#include <stdio.h>
#include <string.h>
#include <ctype.h>

int main(void)
{
    char isbn[20];

    printf("ISBN Validator\n");

    for (;;)
    {
        printf("ISBN (0 to quit): ");
        if (scanf("%19s", isbn) != 1)
            break;

        if (strcmp(isbn, "0") == 0)
        {
            printf("Have a Nice Day!\n");
            break;
        }

        if (strlen(isbn) != 10)
        {
            printf("This is not a valid ISBN.\n");
            continue;
        }

        int all_digits = 1;
        for (int i = 0; i < 10; i++)
        {
            if (!isdigit((unsigned char)isbn[i]))
            {
                all_digits = 0;
                break;
            }
        }
        if (!all_digits)
        {
            printf("This is not a valid ISBN.\n");
            continue;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++)
            sum += (isbn[i] - '0') * (10 - i);
        sum += (isbn[9] - '0');

        if (sum % 11 == 0)
            printf("This is a valid ISBN.\n");
        else
            printf("This is not a valid ISBN.\n");
    }

    return 0;
}
