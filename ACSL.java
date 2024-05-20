import java.io.*;
import java.util.*;

public class Result {
   public static int maxDepth = 0;
   public static ArrayList<Integer> maxDepthI = new ArrayList<>();

   public static String play2248(String board) {

   }

   public static String solve(String strBoard) {
      int[][] board = parseBoard(strBoard);
   }

   public static int[][] parseBoard(String strBoard) {
      int[][] board = new int[8][5];
      String[] input = strBoard.split(" ");

      for (int i = 0; i < n; i++) {
         int y = i / 5;
         int x = i % 5;

         board[y][x] = Integer.parseInt(input[i]);
      }

      return board;
   }

   public static int compress(int a, int b) {
      return 5 * a + b;
   }

   public static int[] decompress(int a) {
      return new int[] { a / 5, a % 5 };
   }

   public static ArrayList<Integer>[][][] findConnections(int[][] board) {
      ArrayList<Integer>[][] toConnections = new ArrayList<Integer>[8][5];
      ArrayList<Integer>[][] fromConnections = new ArrayList<Integer>[8][5];

      for (int y = 0; y < 8; y++) {
         for (int x = 0; x < 5; x++) {
            if (connections[y][x] != -1) {
               continue;
            }

            int[][] positions = new int[][] { new int[] { y - 1, x }, new int[] { y - 1, x + 1 },
                  new int[] { y, x + 1 }, new int[] { y + 1, x + 1 }, new int[] { y + 1, x },
                  new int[] { y + 1, x - 1 }, new int[] { y, x - 1 }, new int[] { y - 1, x - 1 } };
            int[][] checks = new int[][] { new int[] { 0, -1 }, new int[] { 0, 4 }, new int[] { -1, 4 },
                  new int[] { 4, 4 }, new int[] { 4, -1 }, new int[] { 4, 0 }, new int[] { -1, 0 },
                  new int[] { 0, 0 } };

            for (int i = 0; i < 8; i++) {
               int yC = positions[i][0];
               int xC = positions[i][1];
               int[] check = checks[i];

               if (y != check[0] && x != check[1]
                     && (board[y][x] == board[yC][xC] || 2 * board[y][x] == board[yC][xC])) {
                  toConnections[y][x].add(compress(yC, xC));
                  fromConnections[yC][xC].add(compress(y, x));
               }
            }
         }
      }

      return new ArrayList<Integer>[][][] { toConnections, fromConnections };
   }

   public static void findPath(ArrayList<Integer>[][] to, ArrayList<Integer>[][] from, int num) {
      TreeMap<Integer, Integer> length = new TreeMap<>();

      for (int y = 0; y < 8; y++) {
         for (int x = 0; x < 5; x++) {
            if (length.containsKey(compress(y, x))) {
               continue;
            }

            length = next(to, length, compress(y, x), 0);
         }
      }
   }

   public static TreeMap<Integer, Integer> next(ArrayList<Integer>[][] to, TreeMap<Integer, Integer> length, int pos,
         int depth) {
      int[] position = decompress(pos);
      ArrayList<Integer> paths = to[position[0]][position[1]];

      depth += 1;
      handleMaxDepth(depth, pos);
      length.put(pos, depth);

      for (int path : paths) {
         length = (length.containsKey(paths)) ? combinePaths(to, length, pos, depth) : next(to, length, path, depth);
         if (length.containsKey(paths)) {
            length = combinePaths();
         } else {
            length = next(to, length, path, depth);
         }
      }

      return length;
   }

   public static TreeMap<Integer, Integer> combinePaths(ArrayList<Integer>[][] to, TreeMap<Integer, Integer> length,
         int pos, int depth) {
      if (length.get(pos) >= depth) {
         return length;
      }

      int[] position = decompress(pos);
      ArrayList<Integer> paths = to[position[0]][position[1]];

      depth += 1;
      handleMaxDepth(depth, pos);
      for (int path : paths) {
         length.set(path, depth);
         length = combinePaths(to, length, path, depth);
      }

      return length;
   }

   public static void handleMaxDepth(int depth, int pos) {
      if (depth > maxDepth) {
         maxDepth = depth;
         maxDepthI = new ArrayList<>();
         maxDepthI.add(pos);
      } else if (depth == maxDepth) {
         maxDepthI.add(pos);
      }
   }
}
