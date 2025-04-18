import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class bronze2024_feb2 {
   public static void main(String[] args) throws IOException {
      // BufferedReader in = new BufferedReader(
      // new
      // FileReader("/Users/kevinboriboonsomsin/Documents/Documents/Temp/system.in"));
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);

      StringTokenizer st = new StringTokenizer(in.readLine());
      int n = Integer.parseInt(st.nextToken());
      int t = Integer.parseInt(st.nextToken());

      String[] strDirections = in.readLine().split("");
      boolean[] directions = new boolean[n];

      for (int i = 0; i < n; i++) {
         directions[i] = (strDirections[i].equals("R")) ? true : false;
      }

      String[] strCows = in.readLine().split(" ");
      int[] amount = new int[n];
      int sum = 0;

      for (int i = 0; i < n; i++) {
         int value = Integer.parseInt(strCows[i]);
         amount[i] = value;
         sum += value;
      }

      ArrayList<Integer> heads = new ArrayList<>();
      ArrayList<Integer> tails = new ArrayList<>();
      HashSet<Integer> pairs = new HashSet<>();
      HashMap<Integer, Integer> paths = new HashMap<>();

      ArrayList<Integer> unexplored = resetUnexplored(n);
      while (unexplored.size() != 0) {
         int i = unexplored.get(0);
         heads.add(i);
         while (unexplored.contains(i)) {
            unexplored.remove(unexplored.indexOf(i));

            int j = (directions[i]) ? add(i, n) : minus(i, n);
            paths.put(i, j);

            if (heads.contains(j)) {
               if (!tails.contains(i)) {
                  heads.remove(heads.indexOf(j));
                  if (!heads.contains(i)) {
                     heads.add(i);
                  }
               } else {
                  heads.remove(heads.indexOf(j));
               }
            }
            if (!tails.contains(j)) {
               tails.add(j);
            }

            if (directions[i] != directions[j]) {
               pairs.add(i);
               pairs.add(j);
            }

            i = j;
         }
      }

      if (heads.size() == n - 1) {
         out.println(sum);
         in.close();
         out.close();
      }

      for (int pair : pairs) {
         if (heads.contains(pair)) {
            heads.remove(heads.indexOf(pair));
         }
      }

      for (int i : heads) {
         int pathSum = 0;
         while (!pairs.contains(i)) {
            pathSum += amount[i];
            i = (directions[i]) ? add(i, n) : minus(i, n);
         }
         sum -= Math.min(t, pathSum);
      }

      out.println(sum);

      in.close();
      out.close();
   }

   private static int add(int i, int n) {
      if (i == n - 1) {
         return 0;
      }

      return i + 1;
   }

   private static int minus(int i, int n) {
      if (i == 0) {
         return n - 1;
      }

      return i - 1;
   }

   private static ArrayList<Integer> resetUnexplored(int n) {
      ArrayList<Integer> list = new ArrayList<>();
      for (int i = 0; i < n; i++) {
         list.add(i);
      }
      return list;
   }
}
