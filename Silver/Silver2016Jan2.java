import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("div7.in"));
        PrintWriter out = new PrintWriter(new FileWriter("div7.out"));
        // BufferedReader in = new BufferedReader(new FileReader("system.in"));
        // PrintWriter out = new PrintWriter(new FileWriter("system.out"));

        int n = Integer.parseInt(in.readLine());

        int[] sumAt = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            Long val = Long.parseLong(in.readLine());

            sumAt[i] = (int) (val + sumAt[i - 1]) % 7;
        }

        in.close();

        for (int d = n; d > 0; d--) {
            for (int a = 1; a + d < n + 1; a++) {
                int b = a + d;

                if ((sumAt[b] - sumAt[a - 1]) % 7 == 0) {
                    out.println(b - a + 1);
                    out.close();
                    return;
                }
            }
        }

        out.println(0);
        out.close();
    }
}
