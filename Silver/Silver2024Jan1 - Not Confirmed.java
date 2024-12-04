import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class Main {
   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);

      int T = Integer.parseInt(in.readLine());

      String[] tempIO = in.readLine().split(" ");
      int n = Integer.parseInt(tempIO[0]);
      int q = Integer.parseInt(tempIO[1]);
      int c = Integer.parseInt(tempIO[2]);

      int[] values = new int[n];
      String[] strValues = in.readLine().split(" ");
      for (int i = 0; i < n; i++) {
         values[i] = Integer.parseInt(strValues[i]);
      }

      HashMap<Integer, Integer> queries = new HashMap<>();
      for (int i = 0; i < q; i++) {
         tempIO = in.readLine().split(" ");
         queries.put(Integer.parseInt(tempIO[0]), Integer.parseInt(tempIO[1]));
      }

      int[] maxAt = new int[n + 1];

      for (int b = 0; b < n; b++) {
         maxAt[b + 1] = Math.max(values[b], maxAt[b - 1]);

         if (values[b] == 0) {
            values[b] = 1;

            if (queries.containsKey(b)) {
               int a = queries.get(b);

               values[b] = maxAt[a + 1] + 1;
            }
         }
      }

      boolean ret = true;
      for (int val : values) {
         if (val > c) {
            ret = false;
         }
      }

      if (ret) {
         for (int val : values) {
            out.print(val + " ");
         }
      } else {
         out.print("-1");
      }
      out.println();

      in.close();
      out.close();
   }
}
