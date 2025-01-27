import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.readLine());

        int[] des = new int[n];
        int[] curr = new int[n];

        int norm = 0;

        StringTokenizer stA = new StringTokenizer(in.readLine());
        StringTokenizer stB = new StringTokenizer(in.readLine());

        for (int i = 0; i < n; i++) {
            des[i] = Integer.parseInt(stA.nextToken());
            curr[i] = Integer.parseInt(stB.nextToken());

            if (des[i] == curr[i]) {norm++;}
        }

        int[] ret = new int[n + 1];

        for (int c = 0; c < n - 1; c++) {
            int cntC = norm;
            int cntO = norm;
            if (des[c] == curr[c + 1]) {cntO++;}
            if (des[c + 1] == curr[c]) {cntO++;}
            if (des[c] == curr[c]) {cntO--;}
            if (des[c + 1] == curr[c + 1]) {cntO--;}

            ret[cntO]++;

            for (int d = 1; d <= Math.min(c, n - c - 2); d++) {
                int A = c - d;
                int B = c + d;

                if (des[A] == curr[B]) {cntC++;}
                if (des[B] == curr[A]) {cntC++;}
                if (des[A] == curr[A]) {cntC--;}
                if (des[B] == curr[B]) {cntC--;}

                if (des[A] == curr[B + 1]) {cntO++;}
                if (des[B + 1] == curr[A]) {cntO++;}
                if (des[A] == curr[A]) {cntO--;}
                if (des[B + 1] == curr[B + 1]) {cntO--;}

                ret[cntC]++;
                ret[cntO]++;
            }
        }

        for (int val : ret) {
            out.println(val);
        }

        in.close(); out.close();
    }
}
