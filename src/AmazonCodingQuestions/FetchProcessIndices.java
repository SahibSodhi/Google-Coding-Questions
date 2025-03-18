package AmazonCodingQuestions;

import java.util.*;

public class FetchProcessIndices {
    public static List<Integer> fetchProcessIndices(List<Integer> robots) {
        int n = robots.size();
        List<int[]> indexedRobots = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            indexedRobots.add(new int[]{robots.get(i), i + 1}); // 1-based indexing
        }
        
        // Sort based on the number of robots in ascending order
        indexedRobots.sort((a, b) -> Integer.compare(a[0], b[0]));
        
        long sum = 0;
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            int[] current = indexedRobots.get(i);
            if (current[0] > sum) {
                result.clear();
            }
            sum += current[0];
            result.add(current[1]);
        }
        
        // Sort the result in ascending order
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        // Sample Input 1
        List<Integer> robots1 = Arrays.asList(4, 1, 2, 5);
        System.out.println(fetchProcessIndices(robots1)); // Output: [1, 4]

        // Sample Input 2
        List<Integer> robots2 = Arrays.asList(2, 15, 5, 2, 10);
        System.out.println(fetchProcessIndices(robots2)); // Output: [2, 5]

        List<Integer> robots3 = Arrays.asList(1, 6, 2, 7, 2);
        System.out.println(fetchProcessIndices(robots3));  // Output: [2, 4]

        List<Integer> robots4 = Arrays.asList(10, 2, 3, 15, 8, 1);
        System.out.println(fetchProcessIndices(robots4));  // Output: [1, 4, 5]
    }
}