import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < 26; i++) {charMap.put((char) ('a' + i), i);}


        // IO
        boolean print = false;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        // BufferedReader in = new BufferedReader(new FileReader("system.in"));
        // PrintWriter out = new PrintWriter(new FileWriter("system.out"));
        // print = true;
        
        int n = Integer.parseInt(in.readLine());

        int[] chars = new int[n];
        String[] charsStr = in.readLine().split("");

        int[] minI = new int[26];
        int[] maxI = new int[26];
        Arrays.fill(minI, n);
        Arrays.fill(maxI, -1);

        for (int i = 0; i < n; i++) {
            int charVal = charMap.get(charsStr[i].charAt(0));
            chars[i] = charVal;
            
            minI[charVal] = Math.min(i, minI[charVal]);
            maxI[charVal] = Math.max(i, maxI[charVal]);
        }
        
        int Q = Integer.parseInt(in.readLine());
        for (int q = 0; q < Q; q++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int repaints = Integer.parseInt(st.nextToken());
            int key = charMap.get(st.nextToken().charAt(0));

            int left = 0;
            int right = 0;
            int maxDiff = 0;

            while (left < n && right < n) {
                while (right < n) {
                    if (chars[right] != key) {
                        if (repaints == 0) {break;}
                        repaints--;
                    }

                    right++;

                }

                maxDiff = Math.max(right - left, maxDiff);
                if (chars[left++] != key) {repaints++;}
            }

            out.println(maxDiff);
        }

        in.close(); out.close();
    }
}
