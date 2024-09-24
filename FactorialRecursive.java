public class FactorialRecursive {
	static long Factorial(long n) {
		if (n <= 1)
			return 1;
		else
			return n * Factorial(n-1);
	}
  
	public static void main(String[] args) {
		System.out.println("The " + 5 + "th Factorial number is " + Factorial(5));
		System.out.println("The " + 10 + "th Factorial number is " + Factorial(10));
		System.out.println("The " + 15 + "th Factorial number is " + Factorial(15));
	}
