
public class InitOrder {

    public static InitOrder m = new InitOrder();
    public InitOrder(){
        System.out.printf("MAIN CONSTRUCTOR\n");
    }

    {
        System.out.printf("NON-STATIC BLOCK\n");
    }

    static{
        System.out.printf("STATIC BLOCK\n");
    }

    //public static InitOrder m = new InitOrder();


    public static void main(String... args) {
        //InitOrder m = new InitOrder();
        System.out.printf("MAIN METHOD\n");

    }
}

/**
  * Execution order:
  *
  *   NON-STATIC BLOCK
  *   MAIN CONSTRUCTOR
  *   STATIC BLOCK
  *   MAIN METHOD 
  *
  */
