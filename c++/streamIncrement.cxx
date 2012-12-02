/* Google interview question
(taken: http://www.careercup.com/page?pid=google-interview-questions)

U have a number, don't know how long it is, do not know how many digits, don't know when number ends, do not know which is the last number. There is a function to increment the number by 1, but function can take only stream of digits and not the complete number e.g if you have 878999 as a number, you could input this number into the function only as single digit e.g 8,7,8,9,9,9. The output should be the whole number incremented by 1 i.e 879000, remember only single digits you can send to function as input. You can use any data structure, but need to tell why you are using that particular data structure. No need to worry about Time complexity. 
Kindly, suggest how to approach this problem ?

*/

#include <iostream>

// Node for the double linked list
struct Node 
{
    Node *prev, *next;
    int digit;

    //ctor
    Node(int d) : prev(NULL), next(NULL), digit(d) {}
};

//Special SimpleDoubleLinkedList
struct SDLL
{
    Node *head, *tail;
 
    // ctor
    SDLL() : head(NULL), tail(NULL) {}

    // dtor
    virtual ~SDLL() {

       while(tail) {

           Node* p = tail;
           tail = tail->prev;
           delete p;
       }

    }

    // Add an integer to the tail of the list
    void addLast(int n) {

       Node* newNode = new Node(n);

       // special case -> empty list
       if (head == NULL) {
          head = tail = newNode;
          return;
       }
   
       // here -> non-empty list, tail is not NULL
       tail->next = newNode;
       newNode->prev = tail;
       tail = newNode;
    }

    // Add an integer to the start of the list
    void addFirst(int n) {

       Node* newNode = new Node(n);

       // special case -> empty list
       if (head == NULL) {
          head = tail = newNode;
          return;
       }
   
       // here -> non-empty list, tail is not NULL
       head->prev = newNode;
       newNode->next = head;
       head = newNode;
    }

    void increment() {

        Node* current = tail;

        bool incrementByOne = true;

        while ( current ) {
 
             if (incrementByOne) {
                 if ( current->digit < 9 ) {
                     current->digit++;
                     incrementByOne = false;
                 } else { // current->digit == 9
                     current->digit = 0;
                     incrementByOne = true;
                 }
             } //incrementByOne
     
             current = current->prev;
        }
        if ( incrementByOne ) {
            addFirst(1);
        }
    }

    void print() {

        Node *current = head;
        while( current ) {
            if (current != head) {
                std::cout << " ";
            }
            std::cout << current->digit;
            current = current->next;
        }
    }
};

int main()
{
    SDLL list;
    int digit;

    std::cout << "Please enter a digit [0-9] (-1 to end):" << std::endl;
    do {
        std::cin >> digit;
        if (digit != -1) 
            list.addLast(digit);
    } while(digit != -1 );

    list.increment();
    list.print();
}

