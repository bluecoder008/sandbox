import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

class ArraysIntersect<T> {

     T[] intersect(T[] array1, T[] array2, T[] array12) {

         Set<T> checks = new HashSet<T>();
         List<T> ret = new ArrayList<T>();

         for( T t : array1 ) {
             checks.add(t);
         }

         for( T t : array2 ) {

             if (checks.contains(t) ) {
                 ret.add(t);
             }
         }
         return ret.toArray(array12);
     }

     static public void main(String[] args) {

         String[] a1 = { "Apple", "Orange", "PineApple", "Banana" };

         String[] a2 = { "Google", "Apple", "Amazon", "Orange" };

         String[] a12 = new String[4];

         String[] product = new ArraysIntersect<String>().intersect(a1, a2, a12);

         System.out.println("intersect: ");

         for( String str : product ) {
	     if ( str != null )
                 System.out.println("=>" + str );
         }
     }

}
