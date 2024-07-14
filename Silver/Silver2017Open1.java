import java.io.*;
import java.util.*;

public class Main {
   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(new FileReader("pairup.in"));
      PrintWriter out = new PrintWriter(new FileWriter("pairup.out"));
      // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      // PrintWriter out = new PrintWriter(System.out);

      int n = Integer.parseInt(in.readLine());

      int[] count = new int[n];
      int[] time = new int[n];
      Integer[] indexes = new Integer[n];

      for (int i = 0; i < n; i++) {
         String[] tempI = in.readLine().split(" ");
         count[i] = Integer.parseInt(tempI[0]);
         time[i] = Integer.parseInt(tempI[1]);
         indexes[i] = i;
      }

      Arrays.sort(indexes, Comparator.comparingInt(i -> time[i]));

      int left = 0;
      int right = n - 1;
      int maxTime = 0;

      while (left < right) {
         int i = indexes[left];
         int j = indexes[right];

         if (count[i] <= 0) {
            left += 1;
            continue;
         }
         if (count[j] <= 0) {
            right -= 1;
            continue;
         }

         int min = Math.min(count[i], count[j]);
         maxTime = Math.max(time[i] + time[j], maxTime);
         count[i] -= min;
         count[j] -= min;
      }

      if (left == right && count[indexes[left]] != 0) {
         maxTime = Math.max(2 * time[indexes[left]], maxTime);
      }

      out.println(maxTime);
      in.close();
      out.close();
   }
}
