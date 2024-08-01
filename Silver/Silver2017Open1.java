// First Solution

import java.io.*;
import java.util.*;

public class Main {
   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(new FileReader("pairup.in"));
      PrintWriter out = new PrintWriter(new FileWriter("pairup.out"));
      // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      // PrintWriter out = new PrintWriter(System.out);

      int n = Integer.parseInt(in.readLine());

      int[] count = new int[n];
      int[] time = new int[n];
      Integer[] indexes = new Integer[n];

      for (int i = 0; i < n; i++) {
         String[] tempI = in.readLine().split(" ");
         count[i] = Integer.parseInt(tempI[0]);
         time[i] = Integer.parseInt(tempI[1]);
         indexes[i] = i;
      }

      Arrays.sort(indexes, Comparator.comparingInt(i -> time[i]));

      int left = 0;
      int right = n - 1;
      int maxTime = 0;

      while (left < right) {
         int i = indexes[left];
         int j = indexes[right];

         if (count[i] <= 0) {
            left += 1;
            continue;
         }
         if (count[j] <= 0) {
            right -= 1;
            continue;
         }

         int min = Math.min(count[i], count[j]);
         maxTime = Math.max(time[i] + time[j], maxTime);
         count[i] -= min;
         count[j] -= min;
      }

      if (left == right && count[indexes[left]] != 0) {
         maxTime = Math.max(2 * time[indexes[left]], maxTime);
      }

      out.println(maxTime);
      in.close();
      out.close();
   }
}

// Second Solution
//Silver2017Open1.java

import java.io.*;
import java.util.*;

public class Main {
   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(new FileReader("pairup.in"));
      PrintWriter out = new PrintWriter(new FileWriter("pairup.out"));

      int n = Integer.parseInt(in.readLine());

      Cows[] cows = new Cows[n];
      for (int i = 0; i < n; i++) {
         String[] strCow = in.readLine().split(" ");
         cows[i] = new Cows(Integer.parseInt(strCow[0]), Integer.parseInt(strCow[1]));
      }

      Arrays.sort(cows, new CowComparer());

      int maxTime = 0;
      int i = 0;
      int j = n - 1;
      while (i < j) {
         Cows cowA = cows[i];
         Cows cowB = cows[j];
         maxTime = Math.max(cowA.t + cowB.t, maxTime);
         if (cowA.num < cowB.num) {
            cowB.num -= cowA.num;
            i++;
         } else if (cowB.num < cowA.num) {
            cowA.num -= cowB.num;
            j--;
         } else {
            i++;
            j--;
         }
      }

      if (i == j && cows[i].num > 0) {
         maxTime = Math.max(2 * cows[i].num, maxTime);
      }

      out.println(maxTime);
      in.close();
      out.close();
   }

   public static class Cows {
      public int num;
      public int t;

      public Cows(Integer _num, Integer _t) {
         num = _num;
         t = _t;
      }
   }

   public static class CowComparer implements Comparator<Cows> {
      @Override
      public int compare(Cows a, Cows b) {
         return Integer.compare(a.t, b.t);
      }
   }
}
