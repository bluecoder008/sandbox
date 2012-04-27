#include <cassert>
#include <cstdlib>
#include <iostream>

//f(0)=0, f1=1, f2=1, f(n)=f(n-1) + f(n-2)


int f(int n)
{
    assert( n>=0 );
    if ( n==0 ) return 0;
    if ( n==1 ) return 1;
 
    int f0 = 0;
    int f1 = 1;
    int f2;
    
    for(int n1=2; n1<=n; n1++) {
        f2 = f0 + f1;
        f0 = f1;
        f1 = f2;
    }
    return f2;
}

main(int argc, char** argv)
{
    if ( argc != 2 ) {
        std::cout << argv[0] << " N" << std::endl;
        return -1;
    }

    int N = atoi(argv[1]);

    std::cout << "f(" << N << ") = " << f(N) << std::endl;
}
