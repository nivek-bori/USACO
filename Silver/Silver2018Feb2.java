import java.io.*;
import java.util.*;

public class Main {
   private static int[] sDepth;
   private static int[] bDepth;
   private static int[] bStep;
   private static int N;
   private static int B;

   private static int minBoots = Integer.MAX_VALUE;

   private static boolean[][] explored;

   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(new FileReader("snowboots.in"));
      PrintWriter out = new PrintWriter(new FileWriter("snowboots.out"));
      // BufferedReader in = new BufferedReader(
      // new
      // FileReader("/Users/kevinboriboonsomsin/Documents/Documents/Temp/system.in"));
      // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      // PrintWriter out = new PrintWriter(System.out);

      String[] tempI = in.readLine().split(" ");
      int n = Integer.parseInt(tempI[0]);
      int b = Integer.parseInt(tempI[1]);

      explored = new boolean[n][b];
      N = n;
      B = b;

      String[] depthStr = in.readLine().split(" ");
      sDepth = new int[n];
      for (int i = 0; i < n; i++) {
         sDepth[i] = Integer.parseInt(depthStr[i]);
      }

      bDepth = new int[b];
      bStep = new int[b];
      for (int i = 0; i < b; i++) {
         String[] bootStr = in.readLine().split(" ");
         bDepth[i] = Integer.parseInt(bootStr[0]);
         bStep[i] = Integer.parseInt(bootStr[1]);
      }

      bfs(0, 0);

      out.println(minBoots);
      in.close();
      out.close();
   }

   private static void bfs(int tI, int bI) {
      // System.out.println(tI + " " + bI);
      if (explored[tI][bI]) {
         return;
      }
      explored[tI][bI] = true;

      if (tI == N - 1) {
         minBoots = Math.min(bI, minBoots);
         return;
      }

      int maxStep = Math.min(tI + bStep[bI], N - 1);
      for (int step = tI + 1; step <= maxStep; step++) {
         if (sDepth[step] <= bDepth[bI]) {
            bfs(step, bI);
         }
      }

      for (int boot = bI + 1; boot < B; boot++) {
         if (sDepth[tI] <= bDepth[boot]) {
            bfs(tI, boot);
         }
      }
   }
}
