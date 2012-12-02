#include <iostream>

class A {

   int num;

public:

   A() : num(0) {}
 
   void operator++(int) {

      num++;
   }
};


int main()
{
   A* a = new A();

   std::cout << "before: " << a << std::endl;
   *a++;
   std::cout << "after:  " << a << std::endl;

   std::cout << "before2: " << a << std::endl;
   (*a)++;
   std::cout << "after2:  " << a << std::endl;
 
   return 0;
}
