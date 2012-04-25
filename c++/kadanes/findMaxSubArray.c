#include <iostream>

struct result {

   int start;
   int end;
   int sum;
};

result findMaxSubArray(int N, int* array) 
{
    result theResult;
    theResult.start = theResult.end = theResult.sum = 0;

    int sum = 0;
    int start = 0;

    for(int n=0; n<N; n++ ) {

        sum += array[n];
        if ( sum > theResult.sum ) {
            theResult.sum = sum;
            theResult.start = start;
            theResult.end = n;
            continue;
        }

        if ( sum < 0 ) {
            sum = 0;
            start = n+1;
        }
    }

    return theResult;
}


int main(void) 
{
   int N;
   std::cout << "Please enter N: " << std::endl; 
   std::cin  >> N;

   int array[N];

   for(int n = 0; n < N; n++) {
      std::cin >> array[n];
   }

   result r = findMaxSubArray( N, array );

   // std::cout << "sub-array: " << r.start << "-" << r.end << std::endl;

   std::cout << "sub-array:" << std::endl;
   if ( r.sum == 0 ) {
       std::cout << "\tempty\n";
   } else {
       for(int n=r.start; n<=r.end; n++) {
           std::cout << array[n] << " " ;
       }
   }
   std::cout << std::endl;
   std::cout << "max sum: " << r.sum << std::endl;
}

