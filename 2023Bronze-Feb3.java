import java.io.*;
import java.util.*;

public class Main {
   public static void main(String[] args) throws IOException {
      // BufferedReader in = new BufferedReader(
      // new
      // FileReader("/Users/kevinboriboonsomsin/Documents/Documents/Temp/system.in"));
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);

      StringTokenizer st = new StringTokenizer(in.readLine());
      long n = Long.parseLong(st.nextToken());
      long k = Long.parseLong(st.nextToken());

      st = new StringTokenizer(in.readLine());
      long total = k + 1;
      long previousPos = Long.parseLong(st.nextToken());
      for (long i = 1; i < n; i++) {
         long currentPos = Long.parseLong(st.nextToken());

         long dist = currentPos - previousPos;
         if (dist <= k) {
            total += dist;
         } else {
            total += k + 1;
         }

         previousPos = currentPos;
      }

      out.println(total);

      in.close();
      out.close();
   }
}
