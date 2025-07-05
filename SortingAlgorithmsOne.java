import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SortingAlgorithmsOne {
    static void BubbleSort(String file, int N) throws IOException {
        int[] A = fileToArray(file);
        System.out.println("Array before: " + printArray(A));
        int comparisons = 0, exchanges = 0;
        for (int pass = 1; pass < N; pass++) {
            boolean noneExchanged = true;
            for (int index = 0; index < N - pass; index++) {
                comparisons++;
                if (A[index] > A[index + 1]) {
                    exchanges++;
                    int holder = A[index];
                    A[index] = A[index + 1];
                    A[index + 1] = holder;
                    noneExchanged = false;
                }
            }
            if (noneExchanged) {
                break;
            }
        }
        System.out.println("Array after: " + printArray(A));
        System.out.println("Comparisons: " + comparisons + " Exchanges: " + exchanges);
    }

    static void SelectionSort(String file, int N) throws IOException {
        int[] A = fileToArray(file);
        System.out.println("Array before: " + printArray(A));
        int comparisons = 0, exchanges = 0;
        for (int pass = 0; pass < N - 1; pass++) {
            int minIndex = pass;
            for (int index = pass + 1; index < N; index++) {
                comparisons++;
                if (A[index] < A[minIndex]) {
                    minIndex = index;
                }
            }
            int holder = A[minIndex];
            A[minIndex] = A[pass];
            A[pass] = holder;
            exchanges++;
        }
        System.out.println("Array after: " + printArray(A));
        System.out.println("Comparisons: " + comparisons + " Exchanges: " + exchanges);
    }

    static void InsertionSort(String file, int N) throws IOException {
        int[] A = fileToArray(file);
        System.out.println("Array before: " + printArray(A));
        int comparisons = 0, exchanges = 0;
        for (int pass = 1; pass < N; pass++) {
            int current = A[pass];
            int index = pass;
            while (index > 0) {
                comparisons++;
                if (A[index - 1] > current) {
                    A[index] = A[index - 1];
                    exchanges++;
                    index--;
                } else
                    break;
            }
            A[index] = current;
        }
        System.out.println("Array after: " + printArray(A));
        System.out.println("Comparisons: " + comparisons + " Exchanges: " + exchanges);
    }

    static String printArray(int[] A) {
        String arrayString = "";
        for (int i = 0; i < A.length - 1; i++) {
            arrayString += A[i] + ", ";
        }
        arrayString += A[A.length - 1];
        return arrayString;
    }

    static int[] fileToArray(String file) throws IOException {
        List<Integer> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while (line != null) {
            list.add(Integer.parseInt(line));
            line = reader.readLine();
        }
        reader.close();
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("BubbleSort Prototype");
        BubbleSort("prototypeOrdered.txt", 10);
        BubbleSort("prototypeReverse.txt", 10);
        BubbleSort("prototypeRandom.txt", 10);

        System.out.println("\nSelectionSort Prototype");
        SelectionSort("prototypeOrdered.txt", 10);
        SelectionSort("prototypeReverse.txt", 10);
        SelectionSort("prototypeRandom.txt", 10);

        System.out.println("\nInsertionSort Prototype");
        InsertionSort("prototypeOrdered.txt", 10);
        InsertionSort("prototypeReverse.txt", 10);
        InsertionSort("prototypeRandom.txt", 10);

        System.out.println("\nBubbleSort Large Dataset");
        BubbleSort("largeOrdered.txt", 2000);
        BubbleSort("largeReverse.txt", 2000);
        BubbleSort("largeRandom.txt", 2000);

        System.out.println("\nSelectionSort Large Dataset");
        SelectionSort("largeOrdered.txt", 2000);
        SelectionSort("largeReverse.txt", 2000);
        SelectionSort("largeRandom.txt", 2000);

        System.out.println("\nInsertionSort Large Dataset");
        InsertionSort("largeOrdered.txt", 2000);
        InsertionSort("largeReverse.txt", 2000);
        InsertionSort("largeRandom.txt", 2000);
    }
}
