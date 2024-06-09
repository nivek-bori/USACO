import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);

      StringTokenizer st = new StringTokenizer(in.readLine());
      int n = Integer.parseInt(st.nextToken());
      int t = Integer.parseInt(st.nextToken());

      boolean[] direction = new boolean[n];

      String[] tempInput = in.readLine().split("");
      for (int i = 0; i < n; i++) {
         direction[i] = (tempInput[i].equals("R") ? true : false);
      }

      int[] amount = new int[n];
      int sum = 0;

      tempInput = in.readLine().split(" ");
      for (int i = 0; i < n; i++) {
         int value = Integer.parseInt(tempInput[i]);
         amount[i] = value;
         sum += value;
      }

      ArrayList<ArrayList<Integer>> returnData = findBreaks(direction);
      ArrayList<Integer> pairs = returnData.get(0);
      ArrayList<Integer> splits = returnData.get(1);

      if (pairs.size() == 0) {
         out.println(sum);
         in.close();
         out.close();
         return;
      }

      for (int left : splits) {
         sum -= Math.min(followLeft(pairs, amount, left), t);
         sum -= Math.min(followRight(pairs, amount, (left == n - 1) ? 0 : left + 1), t);
      }

      out.println(sum);
      in.close();
      out.close();
   }

   private static ArrayList<ArrayList<Integer>> findBreaks(boolean[] direction) {
      ArrayList<Integer> pairs = new ArrayList<>();
      ArrayList<Integer> splits = new ArrayList<>();
      int n = direction.length;
      for (int i = 0; i < n - 1; i++) {
         if (direction[i] && !direction[i + 1]) {
            pairs.add(i);
            pairs.add(i + 1);
         }
         if (!direction[i] && direction[i + 1]) {
            splits.add(i);
         }
      }

      if (direction[n - 1] && !direction[0]) {
         pairs.add(n - 1);
         pairs.add(0);
      }
      if (!direction[n - 1] && direction[0]) {
         splits.add(n - 1);
      }

      ArrayList<ArrayList<Integer>> returnData = new ArrayList<>();
      returnData.add(pairs);
      returnData.add(splits);
      return returnData;
   }

   private static int followLeft(ArrayList<Integer> pairs, int[] amount, int i) {
      ArrayList<Integer> previous = new ArrayList<>();
      int sum = 0;
      while (!pairs.contains(i)) {
         if (previous.contains(i)) {
            return 0;
         }

         previous.add(i);
         sum += amount[i];

         i = (i != 0) ? i - 1 : amount.length - 1;
      }

      return sum;
   }

   private static int followRight(ArrayList<Integer> pairs, int[] amount, int i) {
      ArrayList<Integer> previous = new ArrayList<>();
      int sum = 0;
      while (!pairs.contains(i)) {
         if (previous.contains(i)) {
            return 0;
         }

         previous.add(i);
         sum += amount[i];

         i = (i != amount.length - 1) ? i + 1 : 0;
      }

      return sum;
   }
}
