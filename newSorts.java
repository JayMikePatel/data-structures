public class newSorts {
    static int comparisons, exchanges;
    static void quickSort(int[] A, int lo, int hi) {
        int pivotPoint = partition(A, lo, hi);
        if(lo < pivotPoint) {
            quickSort(A, lo, pivotPoint - 1);
        }
        if(pivotPoint < hi) {
            quickSort(A, pivotPoint+1, hi);
        }
    }
    static int partition(int[] A, int lo, int hi) {
        int pivot = A[lo];
        while(lo < hi) {
            while(lo < hi) {
                comparisons++;
                if(pivot < A[hi]) {
                    hi--;
                } else
                    break;
            }
            if(hi != lo) {
                A[lo] = A[hi];
                lo++;
                exchanges++;
            }
            while(lo < hi) {
                comparisons++;
                if(A[lo] < pivot) {
                    lo++;
                } else
                    break;
            }
            if(hi != lo) {
                A[hi] = A[lo];
                hi--;
                exchanges++;
            }
        }
        A[hi] = pivot;
        return hi;
    }
    static void Sort(int[] A, int N) {
        comparisons = 0;
        exchanges = 0;
        System.out.println("Array before: " + printArray(A));
        quickSort(A, 0, N-1);
        System.out.println("Array after: " + printArray(A));
        System.out.println("Comparisons: " + comparisons + " Exchanges: " +  exchanges);
    }

    static String printArray(int[] A) {
        String arrayString = "";
        for(int i = 0; i < A.length - 1; i++) {
            arrayString += A[i] + ", ";
        }
        arrayString += A[A.length -1];
        return arrayString;
    }

    static void segmentedInsertionSort(int[] A, int N, int H) {
        for(int i = H; i < N; i++) {
            int current = A[i];
            int j = i;
            while(j > H - 1) {
                comparisons++;
                if(A[j-H] > current) {
                    A[j] = A[j-H];
                    j -= H;
                    exchanges++;
                } else
                    break;
            }
            A[j] = current;
        }
    }

    static void shellSort(int[] A, int N) {
        int[] B = new int[A.length];
        int[] C = new int[A.length];
        for(int i = 0; i < A.length; i++) {
            B[i] = A[i];
            C[i] = A[i];
        }
        comparisons = 0;
        exchanges = 0;
        System.out.println("Array before: " + printArray(A));
        int H = 1;
        /*
        while(H*3 + 1 < N) {
            H = H*3 + 1;
        }*/
        H = 1;
        System.out.println("H: " + H);
        while(H > 0) {
            segmentedInsertionSort(A, N, H);
            H = (H-1)/3;
        }
        System.out.println("Array after: " + printArray(A));
        System.out.println("Comparisons: " + comparisons + " Exchanges: " +  exchanges);
    }
    public static void main(String[] args) {
        int[] A = {15, 8, 7, 3, 2, 14, 11, 1, 5, 9, 4, 12, 13, 6, 10};
        int[] po = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] pre = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] pra = {7, 2, 6, 1, 5, 4, 9, 8, 3, 10};
        shellSort(po, po.length);
        shellSort(pre, pre.length);
        shellSort(pra, pra.length);

        shellSort(lo, lo.length);
        shellSort(lre, lre.length);
        shellSort(lra, lra.length);
    }
}
