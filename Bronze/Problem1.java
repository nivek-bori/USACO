import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // PrintWriter out = new PrintWriter(System.out);

        BufferedReader in = new BufferedReader(new FileReader("system.in"));
        PrintWriter out = new PrintWriter(new FileWriter("system.out"));

        int[] powers = new int[] {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};

        int T = Integer.parseInt(in.readLine());

        for (int t = 0; t < T; t++) {
            String[] numStr = in.readLine().split("");
            int s = numStr.length;
            
            int[] num = new int[s];
            for (int i = 0; i < s; i++) {
                num[i] = Integer.parseInt(numStr[i]);
            }

            System.out.println("TEST CASE: " + t + " " + s);
            for (int val : num) {
                System.out.print(val + " ");
            }
            System.out.println();
    
            int totalCnt = 0;
            for (int pos = 1; pos <= s - 2; pos++) {
                int[] values = new int[s];
                values[pos] = 4;
    
                for (int i = 0; i < pos; i++) {
                    values[i] = num[i];
                }
                for (int i = pos + 1; i < s; i++) {
                    values[i] = 9;
                }

                // System.out.print("POS: " + pos + " - ");
                // for (int val : values) {
                //     System.out.print(val + " ");
                // }
                // System.out.println();
    
    
                for (int i = 0; i < s; i++) {
                    if (values[i] < num[i]) {
                        break;
                    }
    
                    if (values[i] > num[i]) {
                        values = reduce(values, i, pos);
                        i = -1;
                    }
                }
    
                int number = 0;
                for (int i = 0; i < s; i++) {
                    number += values[i] * powers[s - i - 1];
                }
                System.out.print("Final Number: " + number);
                number = Math.max(number / powers[pos + 1] - 1, 0);
    
                int cnt = number * 5 * powers[Math.max(0, s - pos - 2)];
    
                int extra = 0;
                if (pos + 1 < s) {
                    extra = values[pos + 1] - 4;
                    for (int i = pos + 2; i < s; i++) {
                        extra *= values[i] + 1;
                    }
                }
    
                System.out.println(" " + cnt + extra);
                totalCnt += cnt + extra;
            }
    
            out.println(totalCnt);
            System.out.println();
        }

        in.close();
        out.close();
    }

    public static int[] reduce(int[] values, int i, int pos) {
        if (i == pos)  {
            return reduce(values, i - 1, pos);
        }

        if (i == pos + 1 && values[i] == 5) {
            values = reduce(values, i - 1, pos);
            values[i] = 9;
            return values;
        }

        if (values[i] == 0) {
            values = reduce(values, i - 1, pos);
            values[i] = 9;
            return values;
        }

        values[i] -= 1;
        return values;
    }
}
