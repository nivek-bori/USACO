import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean print = false;
        BufferedReader in = new BufferedReader(new FileReader("art.in"));
        PrintWriter out = new PrintWriter(new FileWriter("art.out"));
        
        // BufferedReader in = new BufferedReader(new FileReader("system.in"));
        // PrintWriter out = new PrintWriter(new FileWriter("system.out"));
        // print = true;

        int n = Integer.parseInt(in.readLine());


        int[][] values = new int[n + 1][n + 1];
        int[] minX = new int[n * n + 1];
        int[] minY = new int[n * n + 1];
        int[] maxX = new int[n * n + 1];
        int[] maxY = new int[n * n + 1];
        Arrays.fill(minX, n + 1);
        Arrays.fill(minY, n + 1);
        Arrays.fill(maxX, -1);
        Arrays.fill(maxY, -1);

        for (int i = 1; i < n + 1; i++) {
            String[] lineStr = in.readLine().split(" ");
            int[] line = new int[n + 1];
            for (int j = 1; j < n + 1; j++) {
                int val = Integer.parseInt(lineStr[j - 1]);
                line[j] = val;

                minX[val] = Math.min(j, minX[val]);
                minY[val] = Math.min(i, minY[val]);
                maxX[val] = Math.max(j, maxX[val]);
                maxY[val] = Math.max(i, maxY[val]);
            }
            values[i] = line;
        }

        // REMOVE
        if (print) {
            for (int i = 0; i < n * n + 1; i++) {
                String str = i + "  ";
                out.print(str.substring(0, 3));
            }
            out.println();
            for (int val : minX) {
                String str = val + "  ";
                out.print(str.substring(0, 3));
            }
            out.println();
            for (int val : minY) {
                String str = val + "  ";
                out.print(str.substring(0, 3));
            }
            out.println();
            for (int val : maxX) {
                String str = val + "  ";
                out.print(str.substring(0, 3));
            }
            out.println();
            for (int val : maxY) {
                String str = val + "  ";
                out.print(str.substring(0, 3));
            }
            out.println();
            out.println();
        }
        // REMOVE


        long[][] sum = new long[n + 2][n + 2];
        for (int i = 1; i < n * n + 1; i++) {
            if (minX[i] == n + 1 || minY[i] == n + 1 || maxX[i] == -1 || maxY[i] == -1) {continue;}
            
            int x1 = minX[i];
            int y1 = minY[i];
            int x2 = maxX[i];
            int y2 = maxY[i];

            sum[y1][x1] += i;
            sum[y2 + 1][x1] -= i;
            sum[y1][x2 + 1] -= i;
            sum[y2 + 1][x2 + 1] += i;
        }

        if (print) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    String str = sum[i][j] + "  ";
                    out.print(str.substring(0, 3));
                }
                out.println();
            }
            out.println();
        }
        // REMOVE
        
        boolean[] covers = new boolean[n * n + 1];
        
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                sum[i][j] += sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
                
                if (sum[i][j] != values[i][j]) {
                    covers[values[i][j]] = true;
                }
            }
        }

        // REMOVE
        if (print) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    String str = sum[i][j] + "  ";
                    out.print(str.substring(0, 3));
                }
                out.println();
            }
            out.println();
        }
        // REMOVE
        
        // REMOVE
        if (print) {
            for (int i = 0; i < n * n + 1; i++) {
                String str = i + "  ";
                out.print(str.substring(0, 3));
            }
            out.println();
            for (boolean bool : covers) {
                out.print(((bool) ? "1  " : "0  "));
            }
            out.println();
            out.println();
        }
        // REMOVE
        

        int cnt = (covers[0]) ? 0 : -1;
        for (boolean bool : covers) {
            if (!bool) {
                cnt++;
            }
        }

        out.println(Math.max(cnt, 0));
        in.close();
        out.close();
    }
}
