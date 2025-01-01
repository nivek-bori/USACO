// CSES Subordinates

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class USACOTemp {

   private static int[] head; // Points to leading edge from every node
   private static int[] to; // Points to where edge goes for every edge
   private static int[] next; // Points to next edge with the same form node for every edge

   private static int[] cnts;

   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(new FileReader("Files/usaco.in"));
      PrintWriter out = new PrintWriter(new PrintWriter("Files/usaco.out"));

      int n = Integer.parseInt(in.readLine());

      head = new int[n];
      to = new int[n];
      next = new int[n];
      cnts = new int[n];
      Arrays.fill(head, -1);

      String[] bossesStr = in.readLine().split(" ");
      for (int edgeI = 0; edgeI < n - 1; edgeI++) { //edge
         int toI = edgeI + 1; //node
         int fromI = Integer.parseInt(bossesStr[edgeI]) - 1; //node

         to[edgeI] = toI;
         next[edgeI] = head[fromI];
         head[fromI] = edgeI;
      }

      dfs(0);

      for (int val : cnts) {
         out.print(val + " ");
      }
      out.println();

      in.close();
      out.close();
   }

   private static int dfs(int node) {
      int count = 1;
      int edge = head[node];

      while (edge != -1) {
         count += dfs(to[edge]);

         edge = next[edge];
      }

      cnts[node] = count - 1;

      return count;
   }
}
