import java.io.*;
import java.util.*;

public class Main {
   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(
            new FileReader("/Users/kevinboriboonsomsin/Documents/Documents/Temp/system.in"));
      // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);

      int T = Integer.parseInt(in.readLine());

      for (int t = 0; t < T; t++) {
         int n = Integer.parseInt(in.readLine());
         int[] heights = new int[n];
         int[] growths = new int[n];
         int[] ranks = new int[n];

         String[] strHeights = in.readLine().split(" ");
         String[] strGrowth = in.readLine().split(" ");
         String[] strRanks = in.readLine().split(" ");
         for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(strHeights[i]);
            growths[i] = Integer.parseInt(strGrowth[i]);
            ranks[Integer.parseInt(strRanks[i])] = i;
         }

         int minTime = 0;
         int maxTime = Integer.MAX_VALUE;
         for (int i = 0; i < n - 1; i++) {
            int[] returnData = compare(heights, growths, ranks[i], ranks[i + 1]);
            System.out.println(ranks[i] + " " + ranks[i + 1] + " " + returnData[0]);

            if (returnData[0] == -1) {
               out.println(-1);
               continue;
            } else if (returnData[0] == 0) {
               minTime = Math.max(returnData[1], minTime);
            } else {
               maxTime = Math.min(returnData[1], maxTime);
            }

            if (maxTime < minTime) {
               minTime = -1;
               break;
            }
         }

         out.println(minTime);
      }

      in.close();
      out.close();
   }

   private static int[] compare(int[] heights, int[] growths, int a, int b) {
      int heightA = heights[a];
      int heightB = heights[b];
      int growthA = growths[a];
      int growthB = growths[b];

      if (growthA == growthB) {
         if (heightA > heightB) {
            return new int[] { 0, Integer.MAX_VALUE };
         } else {
            return new int[] { -1, -1 };
         }
      }

      if (heightA < heightB) {
         int t = (int) Math.floor((heightB - heightA) / (growthA - growthB)) + 1;
         return new int[] { 0, t };
      } else {
         int t = (int) Math.floor((heightB - heightA) / (growthA - growthB));
         return new int[] { 1, t };
      }
   }
}
