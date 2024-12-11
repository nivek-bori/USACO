import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
   public static void main(String[] args) throws IOException {
//      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//      PrintWriter out = new PrintWriter(System.out);
      BufferedReader in = new BufferedReader(new FileReader("src/system.in"));
      PrintWriter out = new PrintWriter(new FileWriter("src/system.out"));

      StringTokenizer st = new StringTokenizer(in.readLine());
      int n = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());
      int T = Integer.parseInt(st.nextToken());

      int[] move = new int[k];
      String[] moveStr = in.readLine().split(" ");
      for (int i = 1; i < k; i++) {
         move[i] = Integer.parseInt(moveStr[i]);
      }

      int[] values = IntStream.range(0, n).toArray();
      for (int i = 0; i < n; i++) {
         values = applyMove(values, move);
         move = updateMove(move, n);
      }

      for (int val : values) {
         out.print(val + " ");
      }
      out.println();

      for (int val : move) {
         out.print(val + " ");
      }
      out.println();

      int[] map = new int[n];
      for (int i = 0; i < n; i++) {
         map[values[i]] = i;
      }

      for (int i = 0; i < T / n; i++) {
         applyMap(values, map);
      }
      for (int i = 0; i < T % n; i++) {
         values = applyMove(values, move);
         move = updateMove(move, n);
      }

      out.println();
      for (int val : values) {
         out.print(val + " ");
      }
      out.println();

      in.close();
      out.close();
   }

   public static int[] applyMap(int[] values, int[] map) {
      int n = map.length;
      int[] ret = new int[n];
      for (int i = 0; i < n; i++) {
         ret[map[i]] = values[i];
      }
      return ret;
   }

   public static int[] applyMove(int[] values, int[] move) { // Optimizable by removing ret and applying move from the back
      int[] ret = new int[values.length];
      int n = move.length;
      for (int i = 0; i < n - 1; i++) {
         ret[move[i + 1]] = values[move[i]];
         for (int val : ret) {
            System.out.print(val);
         }
         System.out.println();
      }
      System.out.println();
      for(int i = 0; i < n; i++) {
         if (ret[i] == 0) {
            ret[i] = values[i];
         }
      }
      ret[move[0]] = values[move[n - 1]];
      return ret;
   }

   public static int[] updateMove(int[] move, int N) {
      int n = move.length;
      for (int i = 0; i < n; i++) {
         move[i] = (move[i] + 1) % N;
      }
      return move;
   }
}
