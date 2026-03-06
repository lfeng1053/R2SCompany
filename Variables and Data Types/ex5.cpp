#include <stdio.h>
#include <math.h>
struct Point{
	float x, y;
};
int main(){
	struct Point p1, p2;
	printf("Enter the first point:\n");
	printf("x = "); scanf("%f", &p1.x);
	printf("y = "); scanf("%f", &p1.y);
	
	
	printf("Enter the second point:\n");
	printf("x = "); scanf("%f", &p2.x);
	printf("y = "); scanf("%f", &p2.y);
	
	float dis = sqrt(((p2.x - p1.x) * (p2.x - p1.x)) + ((p2.y - p1.y) * (p2.y - p1.y)));
	
	printf("Distance: %f", dis);
}
