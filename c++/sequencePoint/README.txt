
http://www.parashift.com/c++-faq-lite/misc-technical-issues.html#faq-39.15

[39.15] Why do some people think x = ++y + y++ is bad?

Because it's undefined behavior, which means the runtime system is allowed to do weird or even bizarre things.

The C++ language says you cannot modify a variable more than once between sequence points. Quoth the standard (section 5, paragraph 4):

Between the previous and next sequence point a scalar object shall have its stored value modified at most once by the evaluation of an expression. Furthermore, the prior value shall be accessed only to determine the value to be stored.

http://www.parashift.com/c++-faq-lite/misc-technical-issues.html#faq-39.16

The C++ standard says (1.9p7),

At certain specified points in the execution sequence called sequence points, all side effects of previous evaluations shall be complete and no side effects of subsequent evaluations shall have taken place.
For example, if an expression contains the subexpression y++, then the variable y will be incremented by the next sequence point. Furthermore if the expression just after the sequence point contains the subexpression ++z, then z will not have yet been incremented at the moment the sequence point is reached.

The "certain specified points" that are called sequence points are (section and paragraph numbers are from the standard):

the semicolon (1.9p16)
the non-overloaded comma-operator (1.9p18)
the non-overloaded || operator (1.9p18)
the non-overloaded && operator (1.9p18)
the ternary ?: operator (1.9p18)
after evaluation of all a function's parameters but before the first expression within the function is executed (1.9p17)
after a function's returned object has been copied back to the caller, but before the code just after the call has yet been evaluated (1.9p17)
after the initialization of each base and member (12.6.2p3)

As a result, the expression such as (++a + a++) or (++a + ++a) should be illegal or undefined. However, even just for fun, one can still try to intepret the expressions' results. as:

(++a + a++ ) => 4

This is BAD, but understandable.

But, why these results?

(++a + ++a ) => 6
(++a + ++a + ++a ) => 10
(++a + ++a + ++a ) => 15

We can intepret perhaps as:

(++a + ++a ): 2 * ++a = 2 * 3 = 6
   ++a => 2
   ++a => a

(++a + ++a + ++a): (++a + ++a) + ++a => 6 + 4 = 10
   ++a => 2
   ++a => 3
   ++a => 4

(++a + ++a + ++a + ++a): ((++a + ++a) + ++a ) + ++a) = 10 + 5 = 15
   ++a => 2
   ++a => 3
   ++a => 4
   ++a => 5



