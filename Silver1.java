import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean testing = false;

        BufferedReader in;
        PrintWriter out;

        if (!testing) {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        } else {
            in = new BufferedReader(new FileReader("system.in"));
            out = new PrintWriter(new FileWriter("system.out"));
        }

        int n = Integer.parseInt(in.readLine());

        StringTokenizer stA = new StringTokenizer(in.readLine());
        StringTokenizer stB = new StringTokenizer(in.readLine());

        HashMap<Integer, ArrayList<Integer>> goal = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> curr = new HashMap<>();

        int[][] delta = new int[2 * n - 1][n];
        int norm = 0;

        for (int i = 0; i < 2 * n - 1; i += 2) {
            int A = Integer.parseInt(stA.nextToken());
            int B = Integer.parseInt(stB.nextToken());

            if (!goal.containsKey(A)) {goal.put(A, new ArrayList<>());}
            goal.get(A).add(i);

            if (!curr.containsKey(B)) {curr.put(B, new ArrayList<>());} 
            curr.get(B).add(i);

            if (A == B) {
                norm++;

                for (int c = i - 1; c >= i / 2; c--) {
                    int maxD = Math.min(c, 2 * (n - 1) - c);
                    for (int d = (c % 2 == 1) ? 1 : 2; d <= maxD; d += 2) {
                        if (testing) {out.println(i + " " + c + " " + d);}
                        delta[c][d]--;
                    }
                }
                
                for (int c = i + 1; c <= n - i / 2; c++) {
                    int maxD = Math.min(c, 2 * (n - 1) - c);
                    for (int d = (c % 2 == 1) ? 1 : 2; d <= maxD; d += 2) {
                        if (testing) {out.println(i + " " + c + " " + d);}
                        delta[c][d]--;
                    }
                }
            }
        }
        if (testing) {out.println();}

        for (int key : goal.keySet()) {
            for (int A : goal.get(key)) {
                for (int B : curr.get(key)) {
                    if (testing) {out.println(A + " " + B);}
                    if (A != B) {
                        delta[(A + B) / 2][Math.abs(B - A) / 2]++;
                    }
                }
            }
        }
        if (testing) {out.println();}

        int sum = ((n + 1) * n / 2) * norm;
        for (int[] centers : delta) {
            for (int val : centers) {
                sum += val;

                if (testing) {out.print(val + " ");}
            }
            if (testing) {out.println();}
        }
        if (testing) {out.println();}

        out.println(sum);
        in.close(); out.close();
    }
}
