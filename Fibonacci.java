public class Fibonacci {
    public static void main(String[] args) {
        int nth = 5;
        int first = 0;
        int second = 1;
        int temp = 0;
        int i;
        for (i = 0; i < nth; i++) {
            temp = first;
            first = second;
            second += temp;
        }
        System.out.println(temp);
    }
}
