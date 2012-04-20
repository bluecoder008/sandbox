/** NOTE: Interview question, what is value for E78 in Radix 7 
*/


#include <cstdlib>
#include <iostream>
#include <list>

void convert2R7(int hex, std::list<char>& list)
{
    do {   
        int rem = hex % 7;
        list.push_front( '0' + rem );
        hex = hex / 7;
    } while( hex > 0);
}

int main(int argc, char** argv)
{
   int hex = strtol(argv[1], NULL, 16);

   std::cout << "hex is: 0x" << std::hex << hex << std::endl;
 
   std::list<char> r7digits;

   convert2R7(hex,r7digits);

   std::cout << "radix 7 output: ";
   for( std::list<char>::iterator be = r7digits.begin(),
                            end = r7digits.end();
        be != end;
        ++be ) {
       std::cout << *be;
   }
   std::cout << std::endl; 
}
