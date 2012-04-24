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
            System.out.println( "Singleton() ctor =" + new Date().toString() );
            System.out.println( "ts2=" + new Date().toString() );
        }
 
        private static class SingletonHolder {  
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
