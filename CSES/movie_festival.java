import java.io.*;
import java.util.*;

public class Main {

   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);

      int n = Integer.parseInt(in.readLine());

      int[] startsUnsorted = new int[n];
      int[] endsUnsorted = new int[n];
      Integer[] indexes = new Integer[n];

      for (int i = 0; i < n; i++) {
         StringTokenizer st = new StringTokenizer(in.readLine());
         startsUnsorted[i] = Integer.parseInt(st.nextToken());
         endsUnsorted[i] = Integer.parseInt(st.nextToken());
         indexes[i] = i;
      }

      Arrays.sort(indexes, Comparator.comparingInt(j -> endsUnsorted[j]));

      int[] starts = new int[n];
      int[] ends = new int[n];

      for (int i = 0; i < n; i++) {
         starts[i] = startsUnsorted[indexes[i]];
         ends[i] = endsUnsorted[indexes[i]];
      }

      int i = 1;
      int t = ends[0];
      int count = 1;
      while (i < n) {
         if (starts[i] < t) {
            i++;
         } else {
            t = ends[i];
            count++;
         }
      }

      out.println(count);
      in.close();
      out.close();
   }
}
