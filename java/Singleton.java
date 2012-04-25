import java.util.Date;

/*
 * Observe the timestamp difference: 5 seconds
 *
 */

public class Singleton {

        static {
            System.out.println( "ts1=" + new Date().toString() );
        }

        // Private constructor prevents instantiation from other classes
        private Singleton() { 
        }
 
        private static class SingletonHolder {  

            static {
                  System.out.println( "ts2=" + new Date().toString() );
            }
            public static final Singleton instance = new Singleton();
        }
 
        public static Singleton getInstance() {
                return SingletonHolder.instance;
        }

        public static void main(String[] args) 
        throws InterruptedException {
            Thread.sleep(5000);
            System.out.println("Singleton = " + getInstance() ) ;
        }
}
