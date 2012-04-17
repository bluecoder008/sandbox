#include "stdio.h"

main()
{
    printf("for c:\n");
    printf("7/3=%d\n", (7/3));
    printf("7/-3=%d\n", (7/-3));

    printf("for python:\n");
    system("python -c \"print '7/3=', 7/3\n\"");
    system("python -c \"print '7/-3=',7/-3\n\"");

}
