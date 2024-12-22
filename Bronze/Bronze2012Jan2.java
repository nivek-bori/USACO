import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // BufferedReader in = new BufferedReader(new FileReader("stacking.in"));
        // PrintWriter out = new PrintWriter(new FileWriter("stacking.out"));

        BufferedReader in = new BufferedReader(new FileReader("system.in"));
        PrintWriter out = new PrintWriter(new FileWriter("system.out"));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] diff = new int[n + 1];
 
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            diff[a]++;
            diff[b + 1]--;
        }

        int[] heights = new int[n];
        heights[0] = diff[0];
        for (int i = 1; i < n; i++) {
            heights[i] = heights[i - 1] + diff[i];
        } 

        Arrays.sort(heights);
        
        out.println(heights[n / 2]);
        in.close();
        out.close();
    }
}
