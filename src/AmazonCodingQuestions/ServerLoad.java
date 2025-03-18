package AmazonCodingQuestions;

import java.util.*;

public class ServerLoad {
    public static long getDiffSum(List<Integer> load, int k) {
        if (k < 0) return 0; // Invalid case

        // Count subarrays where difference <= k
        long countLessThanOrEqualK = countSubarrays(load, k);
        // Count subarrays where difference < k
        long countLessThanK = countSubarrays(load, k - 1);

        // Subtract to get subarrays where difference == k
        return countLessThanOrEqualK - countLessThanK;
    }

    private static long countSubarrays(List<Integer> load, int k) {
        int n = load.size();
        long count = 0;
        Deque<Integer> minDeque = new ArrayDeque<>();
        Deque<Integer> maxDeque = new ArrayDeque<>();
        int left = 0;

        for (int right = 0; right < n; right++) {
            // Maintain the minDeque
            while (!minDeque.isEmpty() && load.get(minDeque.peekLast()) >= load.get(right)) {
                minDeque.pollLast();
            }
            minDeque.addLast(right);

            // Maintain the maxDeque
            while (!maxDeque.isEmpty() && load.get(maxDeque.peekLast()) <= load.get(right)) {
                maxDeque.pollLast();
            }
            maxDeque.addLast(right);

            // Shrink the window from the left if the difference exceeds k
            while (!minDeque.isEmpty() && !maxDeque.isEmpty() &&
                   load.get(maxDeque.peekFirst()) - load.get(minDeque.peekFirst()) > k) {
                if (maxDeque.peekFirst() == left) {
                    maxDeque.pollFirst();
                }
                if (minDeque.peekFirst() == left) {
                    minDeque.pollFirst();
                }
                left++;
            }

            // Count all valid subarrays ending at right
            count += (right - left + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        // Sample Input 0
        List<Integer> load1 = Arrays.asList(1, 1, 1);
        int k1 = 0;
        System.out.println(getDiffSum(load1, k1)); // Output: 6

        // Sample Input 1
        List<Integer> load2 = Arrays.asList(4, 2);
        int k2 = 2;
        System.out.println(getDiffSum(load2, k2)); // Output: 1

        // Additional Test Case
        List<Integer> load3 = Arrays.asList(2, 4, 6);
        int k3 = 2;
        System.out.println(getDiffSum(load3, k3)); // Output: 2
    }
}