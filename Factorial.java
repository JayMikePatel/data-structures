public class Factorial {
    public static void main(String[] args) {
        int n = 10;
        int total = 1;
        int i;
        for (i = 1; i <= n; i++) {
            total *= i;
        }
        System.out.println(total);
    }
}
