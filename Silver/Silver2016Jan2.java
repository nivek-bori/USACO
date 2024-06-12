import java.io.*;
import java.util.*;

public class Main {
   public static void main(String[] args) throws IOException {
      // BufferedReader in = new BufferedReader(
      // new
      // FileReader("/Users/kevinboriboonsomsin/Documents/Documents/Temp/system.in"));
      // PrintWriter out = new PrintWriter(System.out);
      BufferedReader in = new BufferedReader(new FileReader("div7.in"));
      PrintWriter out = new PrintWriter(new FileWriter("div7.out"));

      int n = Integer.parseInt(in.readLine());

      long maxLength = 0l;

      long[] sumAt = new long[n];
      long totalSum = 0;
      for (int i = 0; i < n; i++) {
         int value = Integer.parseInt(in.readLine());
         ;
         totalSum += value;
         sumAt[i] = totalSum;

         if (value % 7 == 0) {
            maxLength = 1l;
         }
      }

      for (int length = n; length > 1; length--) {
         int maxI = n - length;
         for (int i = 0; i <= maxI; i++) {
            int a = i;
            int b = i + length - 1;

            long sum;
            if (a != 0) {
               sum = sumAt[b] - sumAt[a - 1];
            } else {
               sum = sumAt[b];
            }

            if (sum % 7 == 0) {
               maxLength = length;
               break;
            }
         }

         if (maxLength != 1) {
            break;
         }
      }

      out.println(maxLength);

      in.close();
      out.close();
   }
}
