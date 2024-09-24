public class BinarySearchRecursive {
	static int BinarySearch(int[] a, int target, int low, int high) {
		if(high == a.length) high--;
		int middle = low + (high - low) / 2;
		if(high >= low) {
			if (target == a[middle])
				return middle;
			else if (target < a[middle])
				return BinarySearch(a, target, low, middle - 1);
			else if (target > a[middle])
				return BinarySearch(a, target, middle + 1, high);
		}
		return -1;
	}
	static String arrayToString(int[] a) {
		String arrayString = "";
		for(int i = 0; i < a.length - 1; i++) {
			arrayString += a[i] + ", ";
		}
		arrayString += a[a.length -1];
		return arrayString;
	}
	static void printBinSec(int[] a, int target, int low, int high) {
		int result = BinarySearch(a, target, low, high);
		if(result < 0)
			System.out.println(target + " not found in list {" + arrayToString(a) + "}");
		else
			System.out.println(target + " found at index " + result + " in list {" + arrayToString(a) + "}");
	}
	public static void main(String[] args) {
		int[] list = {5, 13, 15, 16, 48, 51, 65, 71, 78, 84, 87, 99};
		int hi = list.length-1;
		printBinSec(list, 71, 0, hi);
		printBinSec(list, 17, 0, hi);
		printBinSec(list, 99, 0, hi);
	}
}
