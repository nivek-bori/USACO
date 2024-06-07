// Add Edge Case where b is zero --> array[b] = max(array[0 : a]) *Proccesed after array[a : b] case

import java.io.*;
import java.util.*;

public class Main {
   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(
            new FileReader("/Users/kevinboriboonsomsin/Documents/Documents/Temp/system.in"));
      // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);

      int t = Integer.parseInt(in.readLine());

      String[] tempInput = in.readLine().split(" ");
      int N = Integer.parseInt(tempInput[0]);
      int Q = Integer.parseInt(tempInput[1]);
      int C = Integer.parseInt(tempInput[2]);

      tempInput = in.readLine().split(" ");
      int[] c = new int[N]; // Comptemnt List
      int num = 0; // Num of zeros
      for (int i = 0; i < N; i++) {
         int value = Integer.parseInt(tempInput[i]);
         c[i] = value;
         if (value == 0) {
            num++;
         }
      }

      if (num == 0) {
         PrintLN(out, c);
         in.close();
         out.close();
         return;
      }

      ArrayList<Integer> keys = new ArrayList<>();
      HashMap<Integer, Integer> queries = new HashMap<>();
      for (int i = 0; i < Q; i++) {
         tempInput = in.readLine().split(" ");
         int a = Integer.parseInt(tempInput[0]) - 1;
         int b = Integer.parseInt(tempInput[1]) - 1;
         if (keys.contains(b)) {
            queries.put(b, Math.min(a, queries.get(b)));
         } else {
            keys.add(b);
            queries.put(b, a);
         }
      }

      // REMOVE
      for (int value : c) {
         System.out.print(value + " ");
      }
      System.out.println();

      for (int key : queries.keySet()) {
         System.out.println(key + " " + queries.get(key));
      }
      // REMOVE

      Collections.sort(keys);
      int n = keys.size();
      int[] newC = Arrays.copyOf(c, N);
      for (int i = n - 1; i >= 0; i--) {
         int b = keys.get(i);
         ArrayList<Integer> returnData = maxPos(c, queries.get(b), b);
         // REMOVE
         // System.out.println(queries.get(b) + " " + b + " " + returnData.get(0));
         // REMOVE
         if (returnData.get(0) != -1) {
            newC = fillZeros(returnData, newC);
         }
      }

      newC = fillAllZeros(newC);

      // REMOVE
      for (int value : newC) {
         System.out.print(value + " ");
      }
      System.out.println();
      System.out.println();
      // REMOVE

      if (checkArray(queries, newC)) {
         for (int value : newC) {
            out.print(value + " ");
         }
         out.println();
      } else {
         out.println(-1);
      }

      in.close();
      out.close();
   }

   private static ArrayList<Integer> maxPos(int[] c, int a, int b) {
      ArrayList<Integer> returnData = new ArrayList<>();
      returnData.add(-1);
      int maxA = c[0];
      int maxB = c[0];

      for (int i = 0; i < b; i++) {
         int value = c[i];
         if (i <= a && value > maxA) {
            maxA = value;
         }
         if (value > maxB) {
            maxB = value;
         }
         if (value == 0) {
            returnData.add(i);
         }
      }

      if (b - a == 1) {
         returnData.set(0, maxA);
      } else if (maxB > maxA && returnData.size() != 1) {
         returnData.set(0, maxB);
      }
      // REMOVE
      System.out.println(a + " " + b + " " + maxA + " " + maxB);
      // REMOVE
      return returnData;
   }

   private static int[] fillZeros(ArrayList<Integer> zeros, int[] array) {
      int value = zeros.get(0);
      zeros.remove(0);
      for (int index : zeros) {
         if (array[index] < value) {
            array[index] = value;
         }
      }

      return array;
   }

   private static boolean checkArray(HashMap<Integer, Integer> queries, int[] array) {
      for (int b : queries.keySet()) {
         if (compareMax(array, queries.get(b), b)) {
            return false;
         }
      }

      return true;
   }

   private static boolean compareMax(int[] array, int a, int b) {
      int maxA = array[0];
      int maxB = array[0];
      for (int i = 0; i < b; i++) {
         int value = array[i];
         if (i <= a && value > maxA) {
            maxA = value;
         }
         if (value > maxB) {
            maxB = value;
         }
      }

      return (maxB > maxA) ? true : false;
   }

   private static int[] fillAllZeros(int[] array) {
      int n = array.length;
      for (int i = 0; i < n; i++) {
         if (array[i] == 0) {
            array[i] = 1;
         }
      }

      return array;
   }

   private static void PrintLN(PrintWriter out, int[] array) {
      for (int value : array) {
         out.print(value + " ");
      }
      out.println();
   }
}
