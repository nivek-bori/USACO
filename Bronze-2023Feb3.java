import java.io.*;
import java.util.*;

public class Main {
   public static void main(String[] args) throws IOException {
      // BufferedReader in = new BufferedReader(new
      // FileReader("/Users/kevinboriboonsomsin/Documents/Documents/Temp/system.in"));
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);

      int T = Integer.parseInt(in.readLine());

      for (int t = 0; t < T; t++) {
         String[] strLetters = in.readLine().split("");
         int n = strLetters.length;

         ArrayList<Integer> centers = new ArrayList<>();
         boolean[] letters = new boolean[n];
         for (int i = 0; i < n; i++) {
            boolean value = strLetters[i].equals("O");
            letters[i] = value;
            if (value && 0 < i && i < n - 1) {
               centers.add(i);
            }
         }

         int moves = Integer.MAX_VALUE;
         if (centers.size() != 0) {
            for (int center : centers) {
               boolean I = letters[center - 1];
               boolean F = letters[center + 1];
               if (!I && F) {
                  moves = n - 3;
                  break;
               } else if (I && F) {
                  moves = Math.min(n - 2, moves);
               } else if (!I && !F) {
                  moves = Math.min(n - 2, moves);
               } else if (I && !F) {
                  moves = Math.min(n - 1, moves);
               }
            }
         }

         out.println((moves != Integer.MAX_VALUE) ? moves : -1);
      }

      in.close();
      out.close();
   }
}
