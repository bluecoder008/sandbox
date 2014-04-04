#include <iostream>
#include <string>
#include <cstdlib>

std::string convert(std::string& str)
{
    int count=1;
    int index = 0;
    size_t slen = str.length();
    std::string ret;   

    while (index < slen ) {
        char ch = str[index];
	index++;
        if ( index < slen && str[index] == ch ) {
            count++;
        } else {
            ret += '0' + count;
            ret += ch;
            count = 1;
       }
    }
    return ret;
}

int main(int argc, char** argv)
{
    if ( argc != 3 ) {
        std::cerr << argv[0] << " <init-num> <iter-count>" << std::endl;
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
