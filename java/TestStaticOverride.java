
/**
 * This example demonstrates that a class's static method can be
 * overridden by its subclass.
 * 
 * <pre>
 * [Test code]
 * $ javac TestStaticOverride.java
 *
 * $ java TestStaticOverride
 * TestStaticOverride.methodA()
 * SubTestOverride.methodA()
 * </pre>
 *
 */
public class TestStaticOverride
{
   static void methodA() { 
       System.out.println("TestStaticOverride.methodA()");
   }

   static public void main(String[] args) {

       TestStaticOverride.methodA();
       SubTestOverride.methodA();
   }
}

class SubTestOverride extends TestStaticOverride 
{
    static void methodA() {
       System.out.println("SubTestOverride.methodA()");
    }
 
}


