import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("lazy.in"));
        PrintWriter out = new PrintWriter(new FileWriter("lazy.out"));

        // PrintWriter out = new PrintWriter(new FileWriter("system.out"));
        // BufferedReader in = new BufferedReader(new FileReader("system.in"));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int K = k + 1;
        int s = n + 2 * k + 1;
        int b = n + k + 1;

        int[][] pref = new int[s][s];
        for (int i = K; i < b; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = K; j < b; j++) {
                pref[i][j] = Integer.parseInt(st.nextToken()) + pref[i][j - 1];
            }
        }

        int maxSum = 0;
        for (int i = K; i < b; i++) {
            int imk = i - k;
            int ipk = i + k;
            for (int j = K; j < b; j++) {
                int sum = pref[i][j + k] - pref[i][j - K];

                for (int d = 0; d < k; d++) {
                    int Y1 = imk + d;
                    int Y2 = ipk - d;
                    int X1 = j - d - 1;
                    int X2 = j + d;

                    sum += pref[Y1][X2] - pref[Y1][X1] + pref[Y2][X2] - pref[Y2][X1];
                }

                maxSum = Math.max(sum, maxSum);
            }
        }

        out.println(maxSum);
        in.close();
        out.close();
    }
}
