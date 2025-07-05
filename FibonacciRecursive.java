public class FibonacciRecursive {
    static long Fibonacci(long n) {
        if (n <= 1)
            return n;
        else
            return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    public static void main(String[] args) {
        System.out.println("The " + 5 + "th Fibonacci number is " + Fibonacci(10));
        System.out.println("The " + 10 + "th Fibonacci number is " + Fibonacci(10));
        System.out.println("The " + 15 + "th Fibonacci number is " + Fibonacci(15));
    }
}
