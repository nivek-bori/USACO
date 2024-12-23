import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("paintbarn.in"));
        PrintWriter out = new PrintWriter(new FileWriter("paintbarn.out"));

        // PrintWriter out = new PrintWriter(new FileWriter("system.out"));
        // BufferedReader in = new BufferedReader(new FileReader("system.in"));

        int sizeConstant = 1003;

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] diff = new int[sizeConstant][sizeConstant];
        for (int q = 0; q < n; q++) {
            st = new StringTokenizer(in.readLine());
            int x1 = Integer.parseInt(st.nextToken()) + 1;
            int y1 = Integer.parseInt(st.nextToken()) + 1;
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            diff[y1][x1]++;
            diff[y1][x2 + 1]--;
            diff[y2 + 1][x1]--;
            diff[y2 + 1][x2 + 1]++;
        }

        int cnt = 0;
        int[][] pref = new int[sizeConstant][sizeConstant];
        for (int y = 1; y < sizeConstant; y++) {
            for (int x = 1; x < sizeConstant; x++) {
                pref[y][x] = pref[y - 1][x] + pref[y][x - 1] - pref[y - 1][x - 1] + diff[y][x];

                if (pref[y][x] == k) {
                    // System.out.println(y + " " + x); // REMOVE
                    cnt++;
                }
            }
        }

        out.println(cnt);
        in.close();
        out.close();
    }
}
