import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
   public static void main(String[] args) throws IOException {
      // BufferedReader in = new BufferedReader(
      // new
      // FileReader("/Users/kevinboriboonsomsin/Documents/Documents/Temp/system.in"));
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);

      StringTokenizer st = new StringTokenizer(in.readLine());
      int N = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
      int T = Integer.parseInt(st.nextToken());

      int[] y = new int[n];

      String[] strY = in.readLine().split(" ");
      for (int i = 0; i < n; i++) {
         y[i] = Integer.parseInt(strY[i]);
      }

      int[] gaps = new int[n];
      HashMap<Integer, ArrayList<Integer>> gapNums = new HashMap<>();
      for (int i = 0; i < n - 1; i++) {
         gaps[i] = y[i + 1] - y[i];
         ArrayList<Integer> numbers = new ArrayList<>();
         for (int j = y[i]; j < y[i + 1]; j++) {
            numbers.add(j);
            // System.out.println(i + " " + j);
         }
         gapNums.put(i, numbers);
      }

      gaps[n - 1] = y[0] - y[n - 1] + N;
      ArrayList<Integer> numbers = new ArrayList<>();
      for (int j = y[n - 1]; j < N; j++) {
         numbers.add(j);
      }
      for (int j = 0; j < y[0]; j++) {
         numbers.add(j);
      }
      gapNums.put(n - 1, numbers);

      for (int i = 0; i < n; i++) {
         // System.out.print(gaps[i] + ": ");
         // for (int num : gapNums.get(i)) {
         // System.out.print(num + " ");
         // }
         // System.out.println();
      }

      int[] x = new int[N];
      for (int g = 0; g < n; g++) {
         int gap = gaps[g];
         int cycles = (int) Math.floor((T) / gap);
         ArrayList<Integer> gapNum = gapNums.get(g);
         for (int i : gapNum) {
            int I = i;
            for (int c = 0; c < cycles; c++) {
               I = index(I, gap, N);
            }
            x[I] = i;
         }
      }

      for (int i = 0; i < N - 1; i++) {
         out.print(x[i] + " ");
      }
      out.println(x[N - 1]);

      in.close();
      out.close();
   }

   private static int index(int value, int amount, int N) {
      return (value + amount) % N;
   }

   private static int findGap(int A, int B, int N) {
      if (A < B) {
         return B - A;
      } else {
         return B - A + N;
      }
   }
}

// public class Main {
// public static void main(String[] args) throws IOException {
// // BufferedReader in = new BufferedReader(
// // new
// //
// FileReader("/Users/kevinboriboonsomsin/Documents/Documents/Temp/system.in"));
// BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
// PrintWriter out = new PrintWriter(System.out);

// StringTokenizer st = new StringTokenizer(in.readLine());
// int N = Integer.parseInt(st.nextToken());
// int n = Integer.parseInt(st.nextToken());
// int T = Integer.parseInt(st.nextToken());

// int[] x = new int[N];
// int[] y = new int[n];

// for (int i = 0; i < N; i++) {
// x[i] = i;
// }

// String[] strY = in.readLine().split(" ");
// for (int i = 0; i < n; i++) {
// y[i] = Integer.parseInt(strY[i]);
// }

// int[] Y = Arrays.copyOf(y, n);
// for (int t = 0; t < T; t++) {
// int[] X = Arrays.copyOf(x, N);

// // REMOVE
// // System.out.print("X ");
// // for (int i = 0; i < N; i++) {
// // System.out.print(X[i] + " ");
// // }
// // System.out.println();

// // for (int i = 0; i < n; i++) {
// // System.out.print(Y[i] + " ");
// // }
// // System.out.println();
// // REMOVE

// for (int i = 0; i < n - 1; i++) {
// X[Y[i + 1]] = x[Y[i]];
// }
// X[Y[0]] = x[Y[n - 1]];

// x = X;
// Y = indexMoves(Y, N);

// if (y == Y && T - t >= t) {
// T = T % t;
// t = -1;
// Y = Arrays.copyOf(y, n);
// for (int i = 0; i < N; i++) {
// x[i] = i;
// }
// }
// }

// for (int i = 0; i < N - 1; i++) {
// out.print(x[i] + " ");
// }
// out.println(x[N - 1]);

// in.close();
// out.close();
// }

// private static int[] indexMoves(int[] array, int N) {
// int n = array.length;
// for (int i = 0; i < n; i++) {
// array[i] = (array[i] + 1) % N;
// }
// return array;
// }
// }
