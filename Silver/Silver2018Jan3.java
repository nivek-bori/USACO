//Silver2018Jan3.java

import java.io.*;
import java.util.*;

public class Main {

   private static int[] head;
   private static int[] to;
   private static int[] next;
   private static int[] relevance;

   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(new FileReader("mootube.in"));
      PrintWriter out = new PrintWriter(new FileWriter("mootube.out"));
      // BufferedReader in = new BufferedReader(new
      // FileReader("/Users/kevinboriboonsomsin/Documents/Temp/system.in"));
      // PrintWriter out = new PrintWriter(new
      // FileWriter("/Users/kevinboriboonsomsin/Documents/Temp/system.put"));

      String[] tempI = in.readLine().split(" ");
      int n = Integer.parseInt(tempI[0]);
      int q = Integer.parseInt(tempI[1]);

      head = new int[n];
      to = new int[2 * (n - 1)];
      next = new int[2 * (n - 1)];
      relevance = new int[2 * (n - 1)];
      Arrays.fill(head, -1);

      for (int edgeI = 0; edgeI < 2 * (n - 1); edgeI += 2) {
         tempI = in.readLine().split(" ");

         int i = Integer.parseInt(tempI[0]) - 1;
         int j = Integer.parseInt(tempI[1]) - 1;
         int r = Integer.parseInt(tempI[2]);

         next[edgeI] = head[i];
         head[i] = edgeI;
         to[edgeI] = j;
         relevance[edgeI] = r;

         next[edgeI + 1] = head[j];
         head[j] = edgeI + 1;
         to[edgeI + 1] = i;
         relevance[edgeI + 1] = r;
      }

      for (int queryI = 0; queryI < q; queryI++) {
         tempI = in.readLine().split(" ");
         int minR = Integer.parseInt(tempI[0]);
         int node = Integer.parseInt(tempI[1]) - 1;

         int count = 0;
         HashSet<Integer> visited = new HashSet<>();
         LinkedList<Integer> nextNodes = new LinkedList<>();
         nextNodes.add(node);

         while (nextNodes.size() > 0) {
            node = nextNodes.pop();
            if (visited.contains(node)) {
               continue;
            }

            visited.add(node);

            int edge = head[node];
            while (edge != -1) {
               if (relevance[edge] >= minR && !visited.contains(to[edge])) {
                  count++;
                  nextNodes.add(to[edge]);
               }

               edge = next[edge];
            }
         }

         out.println(count);
      }

      in.close();
      out.close();
   }

}
