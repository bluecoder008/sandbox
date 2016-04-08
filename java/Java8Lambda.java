import java.util.concurrent.Callable;

class Java8Lambda {

    public static void main(String[] args) {

        Runnable runnable = () -> { System.out.println("Runnable run"); };

	//runnable.run();
        run_runnable(runnable);

	Callable<Boolean> callable = () -> {  return normalFunc(); };

        run_callable(callable);
    }

    static Boolean normalFunc() {  return true; }

    static void run_runnable(Runnable func) {
        func.run();
    }

    static void run_callable(Callable<Boolean> callableBoolean) {
        try {
            System.out.println("Callable returns: " + callableBoolean.call());
        } catch (Exception exp) {;  }
    }
}
