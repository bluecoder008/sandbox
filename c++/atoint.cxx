#include <cstdio>
#include <cstdlib>
#include <iostream>
#include <sstream>

int atoint(char* s)
{
   std::istringstream i(std::string(s), std::istringstream::in);
   int ret;
   i >> ret;
   return ret;
}

int atoint2(char* s)
{
   int ret;
   if ( sscanf(s, "%d",&ret) == 1)
       return ret;
}

main(int argc, char** argv)
{
   if ( argc < 2 ) {
       std::cout << "atoint <number_string>" << std::endl;
       return -1;
   }

   int n1 = atoi(argv[1]);
   std::cout << "n1=" << n1 << std::endl;

   int n2 = atoint(argv[1]);
   std::cout << "n2=" << n2 << std::endl;

   int n4 = atoint2(argv[1]);
   std::cout << "n4=" << n4 << std::endl;

   if ( n1 == n2 && n2 == n4) {
       std::cout << "Great !!!!" << std::endl;
   }

}
