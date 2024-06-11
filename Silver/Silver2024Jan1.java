import java.io.*;
import java.util.*;

public class Main {
   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);

      int T = Integer.parseInt(in.readLine());

      // TODO - Put in numT loop using t as indexer
      StringTokenizer st = new StringTokenizer(in.readLine());
      int N = Integer.parseInt(st.nextToken());
      int Q = Integer.parseInt(st.nextToken());
      int max = Integer.parseInt(st.nextToken());

      int[] x = new int[N];
      st = new StringTokenizer(in.readLine());
      boolean quit = false;
      for (int i = 0; i < N; i++) {
         int value = Integer.parseInt(st.nextToken());
         if (value >= max) {
            quit = true;
            break;
         }
         x[i] = max;
      }

      if (quit) {
         out.println(-1);
         continue;
      }

      if (x[0] == 0) {
         x[0] = 1;
      }

      int[] X = Arrays.copyOf(x, N);

      ArrayList<Integer> qC = new ArrayList<>(); // if (b - a == 1) { .add(b) }
      ArrayList<Integer> qB = new ArrayList<>(); // TODO - Add reverse sorting to this
      HashMap<Integer, Integer> tBA = new ArrayList<>(); // Saves query b to query a, allows b to be reverse sorted
      for (int i = 0; i < Q; i++) {
         st = new StringTokenizer(in.readLine());
         int a = Integer.parseInt(st.nextToken()) - 1;
         int b = Integer.parseInt(st.nextToken()) - 1;

         if (b - a == 1) {
            qC.add(b);
         } else {
            if (!qB.contains(b)) {
               qB.add(b);
               tBA.put(b, a);
            } else {
               tBA.put(b, Math.max(a, tBA.get(b)));
            }
         }
      }

      if (qC.size() != 0) {
         Collections.sort(qC);
         int n = qC.size();
         for (int i = n - 1; i >= 0; i--) {
            int value = X[qC.get(i) - 1] + 1;
            if (value >= max) {
               out.println(-1);
               continue;
            }
            X[qC.get(i)] = value;
         }
      }

      Collections.sort(qB);

      int[] maxArray = maxFromZero(x);
      int n = qB.size();
      for (int i = n - 1; i >= 0; i--) {
         int b = qB.get(i);
         int a = tBA.get(b);
         int maxA = maxArray[a];
         int maxB = maxAToB(X, a, b);

         if (maxB > maxA) {
            if (maxB + 1 == X[b]) {
               int[][] returnData = fillZeros(x, X, a, maxB);
               X = returnData[0];
               maxArray = returnData[1];
            } else {
               out.println(-1);
               continue;
            }
         }
      }



   }

   private static int[] maxFromZero(int[] array) {
      int n = array.length;
      int[] rArray = new int[n];

      int max = 0;
      for (int value : array) {
         if (value > max) {
            max = value;
         }
         rArray[i] = max;
      }

      return rArray;
   }

   private static int maxAToB(int[] array, int a, int b) {
      a++;
      b--;
      int max = array[a];
      for (int i = a; i < b; i++) {
         max = Math.max(array[i], max);
      }

      return max;
   }

   private static int[][] fillZeros(int[] x, int[] X, int[] maxArray, int a, int value) {
      for (int i = 0; i <= a; i++) {
         if (x[i] == 0) {
            X[i] = Math.max(value, X[i]);
            maxArray[i] = Math.amx(value, maxArray[i]);
         }
      }

      return new int[][] {X, maxArray};
   }
}
