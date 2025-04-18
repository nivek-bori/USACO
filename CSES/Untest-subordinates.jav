// CSES Subordinates

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class USACOTemp {
   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(new FileReader("Files/usaco.in"));
      PrintWriter out = new PrintWriter(new PrintWriter("Files/usaco.out"));

      int n = Integer.parseInt(in.readLine());

      int[] cnt = new int[n];

      ArrayList<Pair> relations = new ArrayList<Pair>();
      String[] bossesStr = in.readLine().split(" ");

      for (int i = 0; i < n - 1; i++) {
         int bossI = Integer.parseInt(bossesStr[i]) - 1;

         relations.add(new Pair(bossI, i + 1));
      }

      Collections.sort(relations);

      for (Pair relation : relations) {
         cnt[relation.boss] += cnt[relation.sub] + 1;
      }

      for (int val : cnt) {
         System.out.print(val + " ");
      }

      in.close();
      out.close();
   }

   private static class Pair implements Comparable<Pair> {
      public int boss;
      public int sub;
      public Pair(int boss, int sub) {
         this.boss = boss;
         this.sub = sub;
      }

      public int compareTo(Pair other) {
         return Integer.compare(other.boss, this.boss);
      }
   }
}
