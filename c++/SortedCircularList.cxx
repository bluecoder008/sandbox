#include <iostream>

class SortedCircularList 
{
public:
    SortedCircularList(int N,
                       int* array) : N_(N)
    {
        array_ = new int[N];
        for(int n=0; n<N; n++) {
            array_[n] = *array++;
        }
    }

    virtual ~SortedCircularList() {
        delete [] array_;
    }

    void print() {
        std::cout << "List content: " << std::endl;
        for(int n=0; n<N_; n++) {
            std::cout << array_[n] << " ";
        }
        std::cout << std::endl;
    }

    int findMin();

    int find(int val);

private:
    int* array_;
    int N_;

    inline bool isSortedRange(int lo, int hi) {
        return ( array_[lo] <= array_[hi] );
    }

    inline bool isWithinRange(int lo, int hi, int val) {
        return ( array_[lo] <= val && val <= array_[hi] );
    }
};


int main(int argc, char** argv)
{
    if ( argc == 1 ) {

        int arr[] = {11, 12, 18, 20, 2, 4, 5, 8, 9, 10};

        SortedCircularList scl(sizeof(arr)/sizeof(int), arr);
      
        scl.print();
        std::cout << "findMin()=" << scl.findMin() << std::endl;

        std::cout << "find(21)=" << scl.find(21) << std::endl;

        std::cout << "find(8)=" << scl.find(8) << std::endl;
    }

    return 0;
}

int SortedCircularList::findMin()
{
    int lo = 0;
    int hi = N_-1;

    while ( lo < hi ) {

        //std::cout << "lo=" << lo << " hi=" << hi << std::endl;

        if( isSortedRange( lo, hi ) ) {
           return lo;
        }

        // (lo,hi) not sorted here - min should be unsorted half
        int mid = (lo+hi)/2;

        if ( isSortedRange(lo, mid) ) {
            lo = mid + 1;
        } else {
            hi = mid;
        }
    }
    // lo == hi here
    return lo;
}

int SortedCircularList::find(int val)
{
    int lo = 0;
    int hi = N_ - 1;

    while ( lo < hi ) {

        int mid = (lo + hi)/2; 
 
        if ( isSortedRange( lo, mid ) && isWithinRange( lo, mid, val) ||
            !isSortedRange( lo, mid ) && !isWithinRange( mid, hi, val) ) { 
            hi = mid;
        } else {
            lo = mid + 1;
        }
    }
    if ( array_[lo] == val ) {
        return lo;
    }
    return -1;
}
