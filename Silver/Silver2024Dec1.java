import java.io.*;
import java.util.*;

public class Main {

   public static int[][] cows;
   public static ArrayList<Integer> best = new ArrayList<>();

   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(
            new FileReader("/Users/kevinboriboonsomsin/Documents/Documents/Temp/system.in"));
      // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);

      String[] tempInput = in.readLine().split(" ");
      int N = Integer.parseInt(tempInput[0]);
      int max = Integer.parseInt(tempInput[1]);
      int step = Integer.parseInt(tempInput[2]);

      TreeMap<Integer, Integer> tempTree = new TreeMap<>();
      for (int i = 0; i < N; i++) {
         tempInput = in.readLine().split(" ");
         tempTree.put(Integer.parseInt(tempInput[0]), Integer.parseInt(tempInput[1]));
      }

      cows = new int[N][2];
      int l = 0;
      for (int value : tempTree.keySet()) {
         cows[l][0] = value;
         cows[l][1] = tempTree.get(value);
         l++;
      }

      for (int i = 0; i < max; i++) {
         // Add bottom into here latter
         int n = cows.length;
      }

      ArrayList<Integer> bestPath = new ArrayList<>();
      int bestDepth = 0;
      int W = cows[0][0] + step;
      for (int j = 0; j < n && cows[j][0] <= W; j++) {
         ArrayList<Integer> newPath = new ArrayList<>();
         newPath.add(j);

         System.out.println("Find Next Called A " + j);
         ArrayList<Integer> returnPath = findNext(j, step, newPath);
         if (returnPath.size() > bestDepth) {
            System.out.println("Success");
            bestPath = returnPath;
            bestDepth = returnPath.size();
         }
      }

      in.close();
      out.close();
   }

   private static ArrayList<Integer> findNext(int i, int step, ArrayList<Integer> path) {
      int n = cows.length;
      if (i == n) {
         return path;
      }

      int W = cows[i][0] + step;
      System.out.println("First W " + W);

      for (; i < n && cows[i][0] < W; i++) {
      }
      W += step;
      System.out.println("Second W " + W);

      ArrayList<Integer> bestPath = new ArrayList<>(path);
      int bestDepth = bestPath.size();
      while (i < n && cows[i][0] < W) {
         ArrayList<Integer> newPath = new ArrayList<>(path);
         newPath.add(i);

         System.out.println("Find Next Called B " + i);
         ArrayList<Integer> returnPath = findNext(i, step, newPath);
         if (bestDepth < returnPath.size()) {
            bestPath = returnPath;
            bestDepth = returnPath.size();
         }

         i++;
      }

      return bestPath;
   }

   private static void removePath(ArrayList<Integer> path) {
      ArrayList<Integer> ones = new ArrayList<>();

      int n = cows.length;
      for (int i = 0; i < n; i++) {
         if (path.contains(i) && cows[i][1] <= 1) {
            ones.add(i);
         }
      }

      n -= ones.size();
      int[][] newCows = new int[n][2];
      for (int i = 0; i < n;) {
         if (path.contains(i)) {
            continue;
         }

         newCows[i] = new int[] { cows[i][0], cows[i][1] - 1 };
         i++;
      }

      cows = newCows;
   }
}
