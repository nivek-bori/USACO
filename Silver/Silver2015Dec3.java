import java.io.*;
import java.util.*;

public class Main {
   public static void main(String[] args) throws IOException {
      // BufferedReader in = new BufferedReader(
      // new
      // FileReader("/Users/kevinboriboonsomsin/Documents/Documents/Temp/system.in"));
      BufferedReader in = new BufferedReader(new FileReader("bcount.in"));
      PrintWriter out = new PrintWriter(new FileWriter("bcount.out"));

      String[] strNQ = in.readLine().split(" ");
      int n = Integer.parseInt(strNQ[0]);
      int q = Integer.parseInt(strNQ[1]);

      int[] sumA = new int[n];
      int[] sumB = new int[n];
      int[] sumC = new int[n];

      int A = 0;
      int B = 0;
      int C = 0;
      for (int i = 0; i < n; i++) {
         int cow = Integer.parseInt(in.readLine());
         if (cow == 1) {
            A += 1;
         } else if (cow == 2) {
            B += 1;
         } else {
            C += 1;
         }

         sumA[i] = A;
         sumB[i] = B;
         sumC[i] = C;
      }

      for (int i = 0; i < q; i++) {
         String[] strAB = in.readLine().split(" ");

         int a = Integer.parseInt(strAB[0]) - 1;
         int b = Integer.parseInt(strAB[1]) - 1;

         if (a != 0) {
            a--;
            out.println((sumA[b] - sumA[a]) + " " + (sumB[b] - sumB[a]) + " " + (sumC[b] - sumC[a]));
         } else {
            out.println(sumA[b] + " " + sumB[b] + " " + sumC[b]);
         }
      }

      in.close();
      out.close();
   }
}
