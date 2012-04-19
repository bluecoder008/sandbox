#include <cstdlib>
#include <iostream>
#include <vector>
#include <iterator>

template <typename OutIt>
    OutIt split(const std::string &text, char sep, OutIt out)
{
    size_t start = 0, end = 0;

    while((end = text.find(sep, start)) != std::string::npos)
    { 
        *out++ = atoi(text.substr(start, end - start).c_str());
        start = end + 1;
    }

    *out++ = atoi(text.substr(start).c_str());
    return out;
}

int main(int argc, char* argv[])
{
    const char* s1 = argv[1];
    const char* s2 = argv[2];
    std::vector<int> vec1, vec2;

    split( s1, '.', std::back_inserter(vec1) );
    split( s2, '.', std::back_inserter(vec2) );

    if ( vec1 < vec2 ) {
        std::cout << s1 << " < " << s2 << std::endl;
    } else if ( vec2 < vec1  ) {
        std::cout << s1 << " > " << s2 << std::endl;
    } else {
        std::cout << s1 << "==" << s2 << std::endl;
    }
    return 0;
}
