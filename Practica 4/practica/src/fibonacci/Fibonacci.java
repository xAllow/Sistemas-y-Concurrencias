package fibonacci;

public class Fibonacci extends Thread{
    private int n;
    private int fibn, fibn1 ;
    public Fibonacci(int n){
        this.n = n;
    }

    public int fibn(){
        return fibn;
    }

    public int fibn1(){
        return fibn1;
    }

    public void run(){
        //suponemos que n>= 1
        if(n == 1){
            fibn = 1;
            fibn1 = 0; //fibn -1
        } else {
            Fibonacci fibA = new Fibonacci(n-1);
            fibA.start();
            try {
                fibA.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            fibn1 = fibA.fibn();
            fibn = fibA.fibn() + fibA.fibn1();
        }
        System.out.print(fibn + " ");

    }

    public static void main(String[] args) {
        int n = 5;
        Fibonacci fib = new Fibonacci(n);
        fib.start();
        try {
            fib.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("\n" + fib.fibn());
    }
}
