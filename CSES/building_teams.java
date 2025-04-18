// Use FastIO for all testcases

import java.io.*;
import java.util.*;

public class building_teams {
   public static LinkedList<Integer>[] paths;
   public static int[] assignments;
   public static boolean impossible = false;

   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      StringTokenizer st = new StringTokenizer(in.readLine());
      int n = Integer.parseInt(st.nextToken());
      int numPaths = Integer.parseInt(st.nextToken());

      paths = new LinkedList[n];
      assignments = new int[n];
      for (int i = 0; i < n; i++) {
         paths[i] = new LinkedList<>();
      }

      for (int i = 0; i < numPaths; i++) {
         st = new StringTokenizer(in.readLine());
         int a = Integer.parseInt(st.nextToken()) - 1;
         int b = Integer.parseInt(st.nextToken()) - 1;
         paths[a].add(b);
         paths[b].add(a);
      }

      for (int i = 0; i < n && !impossible; i++) {
         if (assignments[i] == 0) {
            assignments[i] = 1;
            followPaths(i);
         }
      }

      if (!impossible) {
         PrintWriter out = new PrintWriter(System.out);
         for (int assigned : assignments) {
            out.print(assigned + " ");
         }
         out.close();
      }

      in.close();
   }

   public static void followPaths(int node) {
      LinkedList<Integer> exitPaths = paths[node];
      for (int path : exitPaths) {
         if (assignments[path] == 0) {
            assignments[path] = assignments[node] % 2 + 1;
            followPaths(path);
         } else if (assignments[path] == assignments[node]) {
            PrintWriter out = new PrintWriter(System.out);
            out.println("IMPOSSIBLE");
            out.close();
            impossible = true;
            return;
         }
      }
   }
}
