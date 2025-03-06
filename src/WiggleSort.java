import java.util.Arrays;

public class WiggleSort {
	private static void swap(int idx1, int idx2, int[] arr){
		int temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}

	//Time complexity: O(n), Space complexity: O(1)
	public static int[] wiggleSort(int n, int[] arr) {
		for(int i = 0; i < n - 1; i ++){
			if((i%2 == 0) && arr[i] > arr[i+1]){
				swap(i, i+1, arr);
			}
			else if((i%2 != 0) && arr[i] < arr[i+1]){
				swap(i, i+1, arr);
			}
		}

		return arr;
	}

	//Time complexity: O(nlogn), Space complexity: O(1)
	// public static int[] wiggleSort(int n, int[] arr) {
	// 	Arrays.sort(arr);
	// 	for(int i = 1; i < n - 1; i += 2){
	// 		swap(i, i+1, arr);
	// 	}
	// 	return arr;
	// }

    public static void main(String args[]){
        int[] arr = {3, 5, 2, 1, 6, 4};
        int n = arr.length;
        int[] result = wiggleSort(n, arr);
        System.out.println("Wiggle sorted array: ");
        for(int i = 0; i < n; i++){
            System.out.print(result[i] + " ");
        }
    }
}