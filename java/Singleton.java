import java.util.Date;

/*
 * Observe the timestamp difference: 5 seconds
 *
 */

public class Singleton {

        static {
            System.out.println( "ts1=" + new Date().toString() );
        }

        // Private constructor prevents instantiation from other classes  <-- keypoint 0
        private Singleton() { 
        }
 
        // Use helper static class to hold instance <-- keypoint 1 (lazy loading)
        private static class SingletonHolder {
            static {
                  System.out.println( "ts2=" + new Date().toString() );
            }
            public static final Singleton instance = new Singleton();
        }
 
        // static getInstance method returns instance wrapped <-- keypoint 2
        public static Singleton getInstance() {
                return SingletonHolder.instance;
        }

        public static void main(String[] args) 
        throws InterruptedException {
            Thread.sleep(5000);
            System.out.println("Singleton = " + getInstance() ) ;
        }
}
