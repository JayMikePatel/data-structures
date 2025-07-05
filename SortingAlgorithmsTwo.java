import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SortingAlgorithmsTwo {
    static int comparisons = 0, exchanges = 0;

    static void BubbleSort(String file, int N) throws IOException {
        int[] A = fileToArray(file);
        System.out.println("Array before: " + printArray(A));
        comparisons = 0;
        exchanges = 0;
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
        comparisons = 0;
        exchanges = 0;
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
        comparisons = 0;
        exchanges = 0;
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

    static void segmentedInsertionSort(int[] A, int N, int H) {
        for (int i = H; i < N; i++) {
            int current = A[i];
            int j = i;
            while (j > H - 1) {
                comparisons++;
                if (A[j - H] > current) {
                    A[j] = A[j - H];
                    j -= H;
                    exchanges++;
                } else
                    break;
            }
            A[j] = current;
        }
    }

    static void shellSort(String file, int N) throws IOException {
        int[] A = fileToArray(file);
        int[] B = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        System.out.println("ShellSort H = H * 3 + 1 Sequence");
        comparisons = 0;
        exchanges = 0;
        int H = 1;
        while (H * 3 + 1 < N) {
            H = H * 3 + 1;
        }
        System.out.println("Array before: " + printArray(A));
        while (H > 0) {
            segmentedInsertionSort(A, N, H);
            H = (H - 1) / 3;
        }
        System.out.println("Array after: " + printArray(A));
        System.out.println("Comparisons: " + comparisons + " Exchanges: " + exchanges);

        System.out.println("\nShellSort H = H * 2 + 1 Sequence");
        comparisons = 0;
        exchanges = 0;
        H = 1;
        while (H * 2 + 1 < N) {
            H = H * 2 + 1;
        }
        System.out.println("Array before: " + printArray(B));
        while (H > 0) {
            segmentedInsertionSort(B, N, H);
            H = (H - 1) / 2;
        }
        System.out.println("Array after: " + printArray(B));
        System.out.println("Comparisons: " + comparisons + " Exchanges: " + exchanges + "\n");
    }

    static void quickSort(int[] A, int lo, int hi) {
        int pivotPoint = partition(A, lo, hi);
        if (lo < pivotPoint) {
            quickSort(A, lo, pivotPoint - 1);
        }
        if (pivotPoint < hi) {
            quickSort(A, pivotPoint + 1, hi);
        }
    }

    static int partition(int[] A, int lo, int hi) {
        int pivot = A[lo];
        while (lo < hi) {
            while (lo < hi) {
                comparisons++;
                if (pivot < A[hi]) {
                    hi--;
                } else
                    break;
            }
            if (hi != lo) {
                A[lo] = A[hi];
                lo++;
                exchanges++;
            }
            while (lo < hi) {
                comparisons++;
                if (A[lo] < pivot) {
                    lo++;
                } else
                    break;
            }
            if (hi != lo) {
                A[hi] = A[lo];
                hi--;
                exchanges++;
            }
        }
        A[hi] = pivot;
        return hi;
    }

    static void quickSortHelper(String file, int N) throws IOException {
        int[] A = fileToArray(file);
        comparisons = 0;
        exchanges = 0;
        System.out.println("Array before: " + printArray(A));
        quickSort(A, 0, N - 1);
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
        /*
         * System.out.println("BubbleSort Prototype");
         * BubbleSort("prototypeOrdered.txt", 10);
         * BubbleSort("prototypeReverse.txt", 10);
         * BubbleSort("prototypeRandom.txt", 10);
         * 
         * System.out.println("\nSelectionSort Prototype");
         * SelectionSort("prototypeOrdered.txt", 10);
         * SelectionSort("prototypeReverse.txt", 10);
         * SelectionSort("prototypeRandom.txt", 10);
         * 
         * System.out.println("\nInsertionSort Prototype");
         * InsertionSort("prototypeOrdered.txt", 10);
         * InsertionSort("prototypeReverse.txt", 10);
         * InsertionSort("prototypeRandom.txt", 10);
         * 
         * System.out.println("\nBubbleSort Large Dataset");
         * BubbleSort("largeOrdered.txt", 2000);
         * BubbleSort("largeReverse.txt", 2000);
         * BubbleSort("largeRandom.txt", 2000);
         * 
         * System.out.println("\nSelectionSort Large Dataset");
         * SelectionSort("largeOrdered.txt", 2000);
         * SelectionSort("largeReverse.txt", 2000);
         * SelectionSort("largeRandom.txt", 2000);
         * 
         * System.out.println("\nInsertionSort Large Dataset");
         * InsertionSort("largeOrdered.txt", 2000);
         * InsertionSort("largeReverse.txt", 2000);
         * InsertionSort("largeRandom.txt", 2000);
         */
        System.out.println("\nShellSort Prototype");
        shellSort("prototypeOrdered.txt", 10);
        shellSort("prototypeReverse.txt", 10);
        shellSort("prototypeRandom.txt", 10);

        System.out.println("ShellSort Large Dataset");
        shellSort("largeOrdered.txt", 2000);
        shellSort("largeReverse.txt", 2000);
        shellSort("largeRandom.txt", 2000);

        System.out.println("\nQuickSort Prototype");
        quickSortHelper("prototypeOrdered.txt", 10);
        quickSortHelper("prototypeReverse.txt", 10);
        quickSortHelper("prototypeRandom.txt", 10);

        System.out.println("\nQuickSort Large Dataset");
        quickSortHelper("largeOrdered.txt", 2000);
        quickSortHelper("largeReverse.txt", 2000);
        quickSortHelper("largeRandom.txt", 2000);

    }
}
