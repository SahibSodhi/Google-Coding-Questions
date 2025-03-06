public class LicensePlateGenerator {
    public static String licensePlate(int n) {
        int[] ranges = new int[6];
        for (int place = 0; place < 5; place++) {
            ranges[place + 1] = ranges[place] + (int) Math.pow(10, 5 - place) * (int) Math.pow(26, place);
        }

        String[] parts = {"", ""};
        for (int place = 5; place >= 0; place--) {
            int low = ranges[place];
            if (n < low) continue;
            n -= low;
            
            int[][] configs = {{5 - place, 10, '0'}, {place, 26, 'A'}};
            for (int i = 0; i < configs.length; i++) {
                int part = configs[i][0];
                int base = configs[i][1];
                char first = (char) configs[i][2];
                
                for (int j = 0; j < part; j++) {
                    int k = n % base;
                    n /= base;
                    parts[i] = (char) (k + first) + parts[i];
                }
            }
            return parts[0] + parts[1];
        }
        return "";
    }

    public static void main(String[] args) {
        Object[][] testCases = {
            {0, "00000"},
            {1, "00001"},
            {99999, "99999"},
            {100000, "A0000"},
            {110001, "B0001"},
            {359999, "Z9999"},
            {360000, "AA000"},
            {361001, "AB001"},
            {1035999, "ZZ999"},
            {1036000, "AAA00"},
            {1036101, "AAB01"},
            {2793599, "ZZZ99"},
            {2793600, "AAAA0"},
            {2793611, "AAAB1"},
            {7363359, "ZZZZ9"},
            {7363360, "AAAAA"},
            {7363361, "AAAAB"},
            {19244735, "ZZZZZ"}
        };

        for (Object[] testCase : testCases) {
            int n = (int) testCase[0];
            String expected = (String) testCase[1];
            assert licensePlate(n).equals(expected) : "Test failed for n = " + n;
        }
        System.out.println("All tests passed!");
    }
}
