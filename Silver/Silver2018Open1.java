import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("sort.in"));
        PrintWriter out = new PrintWriter(new FileWriter("sort.out"));

        int n = Integer.parseInt(in.readLine());

        Integer[] indexes = new Integer[n];
        int[] values = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(in.readLine());
            indexes[i] = i;
        }

        Arrays.sort(indexes, Comparator.comparing(i -> values[i]));
        
        int maxD = 0;
        for (int i = 0; i < n; i++) {
            maxD = Math.max(Math.abs(i - indexes[i]), maxD);
        }

        out.println(maxD + 1);

        in.close();
        out.close();
    }
}
