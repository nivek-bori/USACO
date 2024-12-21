import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("maxcross.in"));
        PrintWriter out = new PrintWriter(new FileWriter("maxcross.out"));

        // BufferedReader in = new BufferedReader(new FileReader("system.in"));
        // PrintWriter out = new PrintWriter(new FileWriter("system.out"));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        if (b == 0) {
            out.println(0);
            in.close();
            out.close();
        }
        if (b == n) {
            out.println(k);
            in.close();
            out.close();
        }

        boolean[] broken = new boolean[n];
        for (int i = 0; i < b; i++) {
            broken[Integer.parseInt(in.readLine()) - 1] = true;
        }

        int minFixed = Integer.MAX_VALUE;
        int fixed = 0;
        int count = 0;
        int i = -1;
        int j = 0;
        while (i < n) {
            // System.out.println(i + " " + j + " " + count); // REMOVE
            if (count >= k) {
                minFixed = Math.min(fixed, minFixed);

                if (broken[j]) {fixed--;}
                count--;
                j++;
            } else {
                i++;
                if (i >= n) {break;}

                if (broken[i]) {fixed++;}
                count++;
            }
        }
        
        out.println(minFixed);
        in.close();
        out.close();
    }
}
