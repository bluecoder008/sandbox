#include <iostream>
#include <algorithm>
#include <vector>

bool debug = false;

int minCoins( int a[], int N, int S ) {
/* N: Length of the array */
/* a[]: sorted array in increasing order */

    if (debug) {
        std::cout << "[DEBUG] input a[ ";
        for(int n=0; n<N; n++) {
            std::cout << a[n] << " ";
        }
        std::cout << "] S = " << S << std::endl;
    }

    if ( S <= 0 || N == 0) {
        if (debug) { std::cout << "[DEBUG] return -> -1" <<std::endl; }
        return -1;
    }

    if ( N == 1 ) {

        if( S % a[0] == 0 ) {
           int ret = S / a[0];
           if (debug) { std::cout << "[DEBUG] return -> " << ret <<std::endl; }
           return ret;
        }
        if (debug) { std::cout << "[DEBUG] return -> -1" <<std::endl; }
        return -1;
    }

    // N1 -> the last index + 1, last index is the last
    // element in a[] that is smaller than S
    int N1;
    for( N1=0; N1 < N; N1++) {
        if ( a[N1] > S )
            break;
    }

    if ( debug ) {
        std::cout << "[DEBUG] N1=" << N1 << std::endl;
    }

    if ( N1 < N )
        return minCoins( a, N1, S);

    int m1 = minCoins( a, N1, S-a[N1-1] ); 
    int m2 = minCoins( a, N1-1, S);

    if ( m1 < 0 ) { // This case, a[N1-1] is not part of solution
       return m2;
    } else { // m1 > 0 - Here a[N1-1] "might" be part of solution
       if ( m2 < 0 ) 
           return (m1 + 1);
       else {
           return ( m2 < (m1 + 1) ? m2 : m1+1 ); 
       }
    }
}

int main(int argc, char** argv) {

    std::vector<int> coins;

    std::cout << "coins:";

    int n;
    int count = 0;
 
    do {

        std::cin >> n;

        if ( n < 0 ) {
            break;
        }
        coins.push_back(n);

    } while(true);

    std::vector<int>::iterator it = coins.begin();


    std::cout << "S:";
    int S;
    std::cin >> S;

    std::sort(coins.begin(), coins.end());

    int array[coins.size()];

    for(int n=0; it != coins.end(); it++, n++) {
        array[n] = *it;
    }


    std::cout << "min coins: " << minCoins(array, coins.size(), S) << std::endl;
    return 0;
}
