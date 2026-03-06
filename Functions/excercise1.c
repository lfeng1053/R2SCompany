#include <stdio.h>

#define PI 3.14159265359

int main(void)
{
    double radius, area, perimeter;

    printf("Enter the radius of the circle: ");
    scanf("%lf", &radius);

    if (radius < 0) {
        printf("Error: Radius must be non-negative.\n");
        return 1;
    }

    perimeter = 2 * PI * radius;
    area = PI * radius * radius;

    printf("Area = %.2f\n", area);
    printf("Perimeter = %.2f\n", perimeter);

    return 0;
}
