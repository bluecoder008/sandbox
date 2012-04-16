#include <iostream>

int main()
{
    int a =1;
    std::cout << (++a + ++a ) << " ";
    std::cout << a << std::endl;

    a=1;
    std::cout << (++a + ++a + ++a) << " ";
    std::cout << a << std::endl;

    a=1;
    std::cout << (++a + ++a + ++a + ++a) << " ";
    std::cout << a << std::endl;
}
