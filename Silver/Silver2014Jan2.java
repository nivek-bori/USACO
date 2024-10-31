// Silver2014Jan2.java

import java.io.*;
import java.util.LinkedList;

public class USACOTemp {
   private static LinkedList<Integer> checkpoints = new LinkedList<>();
   private static LinkedList<Step> next = new LinkedList<>();
   private static int difficulty = 1;

   private static int[][] heights;
   private static boolean[][] visited;

   private static int height;
   private static int length;

   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(new FileReader("Files/usaco.in"));
      PrintWriter out = new PrintWriter(new FileWriter("Files/usaco.out"));

      String[] sizesStr = in.readLine().split(" ");
      height = Integer.parseInt(sizesStr[0]);
      length = Integer.parseInt(sizesStr[1]);

      heights = new int[height][length];
      visited = new boolean[height][length];

      for (int y = 0; y < height; y++) {
         String[] row = in.readLine().split(" ");
         for (int x = 0; x < length; x++) {
            heights[y][x] = Integer.parseInt(row[x]);
         }
      }

      next.add(new Step(-1, -1, -1));
      for (int y = 0; y < height; y++) {
         String[] row = in.readLine().split(" ");
         for (int x = 0; x < length; x++) {

            if (row[x].equals("1")) {
               checkpoints.add(encode(y, x));
               next.set(0, new Step(y, x, heights[y][x]));
            }

         }
      }

      while (checkpoints.size() > 0) {
         Step step = next.pop();
         int y = step.y;
         int x = step.x;

         if (visited[y][x]) {
            continue;
         }

         if (Math.abs(heights[y][x] - step.formHeight) > difficulty) {

         }
      }
   }

   private static int encode(int y, int x) {
      return length * y + x;
   }

   private static int[] decode(int z) {
      return new int[] {z / length, z % length};
   }

   private static class Step {
      public int y;
      public int x;
      public int formHeight;
      public Step(int y, int x, int fromHeight) {
         this.y = y;
         this.x = x;
         this.formHeight = fromHeight;
      }
   }
}
