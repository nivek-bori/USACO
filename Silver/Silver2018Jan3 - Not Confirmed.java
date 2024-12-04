
import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class USACOTemp {
   public static void main(String[] args) throws IOException  {
      BufferedReader in = new BufferedReader(new FileReader("Files/usaco.in"));
      PrintWriter out = new PrintWriter(new FileWriter("Files/usaco.out"));

      String[] tempI = in.readLine().split(" ");
      int N = Integer.parseInt(tempI[0]);
      int Q = Integer.parseInt(tempI[1]);

      int[] head = new int[N]; Arrays.fill(head, -1);//node
      int[] to = new int[2 * (N - 1)]; // edge
      int[] next = new int[2 * (N - 1)]; //edge
      int[] rev = new int[2 * (N - 1)]; // edge

      for (int edgeI = 0; edgeI < 2 * (N - 1); edgeI += 2) {
         tempI = in.readLine().split(" ");

         int a = Integer.parseInt(tempI[0]) - 1; //node
         int b = Integer.parseInt(tempI[1]) - 1; //node
         int r = Integer.parseInt(tempI[2]); // edge

         next[edgeI] = head[a];
         head[a] = edgeI;
         to[edgeI] = b;
         rev[edgeI] = r;

         next[edgeI + 1] = head[b];
         head[b] = edgeI + 1;
         to[edgeI + 1] = a;
         rev[edgeI + 1] = r;
      }

      for (int q = 0; q < Q; q++) {
         tempI = in.readLine().split(" ");
         int minR = Integer.parseInt(tempI[0]);
         int node = Integer.parseInt(tempI[1]) - 1;

         LinkedList<Integer> toBeExplored = new LinkedList<>();
         HashSet<Integer> visited = new HashSet<>();
         toBeExplored.add(node);

         int count = 0;
         while (!toBeExplored.isEmpty() && visited.add(toBeExplored.peek())) {
            node = toBeExplored.pop();

            int edgeI = head[node];
            while (edgeI != -1) {
               if (rev[edgeI] >= minR && !visited.contains(to[edgeI])) {
                  count++;
                  toBeExplored.add(to[edgeI]);
               }

               edgeI = next[edgeI];
            }
         }

         out.println(count);
      }

      in.close();
      out.close();
   }
}
