#include <iostream>
#include <string>
#include <cstdlib>

std::string convert(std::string& str)
{
    const char* pStr = str.c_str();
    int count=1;
    std::string ret;   

    while (*pStr) {
       char ch = *pStr;
       ++pStr;
       if ( *pStr == ch ) {
           count++;
       } else {
           ret += '0' + count;
           count = 1;
           ret += ch;
       }
    }
    return ret;
}

int main(int argc, char** argv)
{
    if ( argc != 3 ) {
        std::cerr << argv[0] << "<init-num> <iter-count>" << std::endl;
        return -1;
    }

    int count = (int)strtol(argv[2], NULL, 0);
    std::string current(argv[1]);

    std::cout << current << std::endl;
    for(int n=0; n < count-1; n++) {
       current = convert(current);
       std::cout << current << std::endl;
    }
}
