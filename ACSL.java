import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Main {
   public static ArrayList<ArrayList<ArrayList<Integer>>> toConnections = new ArrayList<>();
   public static ArrayList<ArrayList<ArrayList<Integer>>> toSameConnections = new ArrayList<>();
   public static ArrayList<ArrayList<ArrayList<Integer>>> fromConnections = new ArrayList<>();

   public static int maxDepth = 0;
   public static ArrayList<ArrayList<Integer>> maxDepthI = new ArrayList<>();

   public static int[] allowedPowers = new int[] { 32, 16, 8, 4, 2 };

   public static void main(String[] args) throws IOException {
      BufferedInputStream in = new BufferedInputStream(System.in);
      PrintWriter out = new PrintWriter(System.out);

      // String input = in.toString();
      String input = "4 128 4 128 32 16 16 4 256 16 32 4 16 64 4 8 64 64 256 8 16 2 2 256 4 32 128 2 64 8 256 32 128 16 2 8 32 32 4 32";

      String output = solve(input);
      out.println("Output " + output);

      in.close();
      out.close();
   }

   public static String solve(String strBoard) {
      int[][] board = parseBoard(strBoard);

      for (int i = 0; i < 3; i++) {
         maxDepth = 0;
         maxDepthI = new ArrayList<>();

         findConnections(board);
         findDepth();
         findMaxDepthPath();
         printL("Max Depth", maxDepth, maxDepthI); // REMOVE

         board = updateBoard(board);

         // REMOVE
         break;
         // REMOVE
      }

      return unparseBoard(board);
   }

   public static int[][] parseBoard(String strBoard) {
      int[][] board = new int[8][5];
      StringTokenizer st = new StringTokenizer(strBoard);

      for (int i = 0; i < 40; i++) {
         int y = i / 5;
         int x = i % 5;

         board[y][x] = Integer.parseInt(st.nextToken());
      }

      return board;
   }

   public static int compress(int a, int b) {
      return 5 * a + b + 1;
   }

   public static int[] decompress(int a) {
      a -= 1;
      return new int[] { a / 5, a % 5 };
   }

   public static void findConnections(int[][] board) {
      for (int y = 0; y < 8; y++) {
         toConnections.add(new ArrayList<ArrayList<Integer>>());
         toSameConnections.add(new ArrayList<ArrayList<Integer>>());
         fromConnections.add(new ArrayList<ArrayList<Integer>>());
         for (int x = 0; x < 5; x++) {
            toConnections.get(y).add(new ArrayList<Integer>());
            toSameConnections.get(y).add(new ArrayList<Integer>());
            fromConnections.get(y).add(new ArrayList<Integer>());
         }
      }

      for (int y = 0; y < 8; y++) {
         for (int x = 0; x < 5; x++) {
            int[][] positions = new int[][] { new int[] { y - 1, x }, new int[] { y - 1, x + 1 },
                  new int[] { y, x + 1 }, new int[] { y + 1, x + 1 }, new int[] { y + 1, x },
                  new int[] { y + 1, x - 1 }, new int[] { y, x - 1 }, new int[] { y - 1, x - 1 } };
            int[][] checks = new int[][] { new int[] { 0, -1 }, new int[] { 0, 4 }, new int[] { -1, 4 },
                  new int[] { 7, 4 }, new int[] { 7, -1 }, new int[] { 7, 0 }, new int[] { -1, 0 },
                  new int[] { 0, 0 } };

            for (int i = 0; i < 8; i++) {
               int yC = positions[i][0];
               int xC = positions[i][1];
               int[] check = checks[i];

               if (y != check[0] && x != check[1]) {
                  if (board[y][x] == board[yC][xC]) {
                     toConnections.get(y).get(x).add(compress(yC, xC));
                     toSameConnections.get(y).get(x).add(compress(yC, xC));
                     fromConnections.get(yC).get(xC).add(compress(y, x));
                  }
                  if (2 * board[y][x] == board[yC][xC]) {
                     toConnections.get(y).get(x).add(compress(yC, xC));
                     toSameConnections.get(y).get(x).add(compress(yC, xC));
                  }
               }
            }
         }
      }
   }

   public static void findDepth() {
      for (int i = 0; i < 40; i++) {
         System.out.println("Find Depth From: " + i); // REMOVE
         findNextDepth(0, i + 1, new ArrayList<Integer>());
      }
   }

   public static void findNextDepth(int depth, int pos, ArrayList<Integer> previous) {
      // REMOVE
      if (depth == 1) {
         return;
      }
      // REMOVE

      depth += 1;
      previous.add(pos);
      updateDepth(depth, previous);

      int[] position = decompress(pos);
      ArrayList<Integer> paths = (depth != 1) ? toConnections.get(position[0]).get(position[1])
            : depths == 0 && !toSameConnections.get(postion[0]).get(position[1]).contains(path);
      for (int path : paths) {
         if (previous.contains(path)) {
            continue;
         }

         // REMOVE
         System.out.println("Find Next Depth at : " + path + " - " + previous);
         // REMOVE

         findNextDepth(depth, path, new ArrayList<>(previous));
      }
   }

   public static void updateDepth(int depth, ArrayList<Integer> path) {
      if (depth > maxDepth) {
         printL("Depth", depth, maxDepth, path);
         maxDepth = depth;
         maxDepthI = new ArrayList<>();
         maxDepthI.add(path);
      } else if (depth == maxDepth) {
         maxDepthI.add(path);
      }
   }

   public static void findMaxDepthPath() {
      if (maxDepthI.size() == 1) {
         return;
      }

   }

   public static int[][] updateBoard(int[][] board) {
      ArrayList<Integer> path = maxDepthI.get(0);
      int sum = findSum(board, path);
      findAllowedPowers(sum);

      board = removePath(board, path);
      board = dropBoard(board);
      board = fillBoard(board);

      return board;
   }

   public static int findSum(int[][] board, ArrayList<Integer> currentPath) {
      int sum = 0;

      for (int path : currentPath) {
         int[] position = decompress(path);
         sum += board[position[0]][position[1]];
      }

      return sum;
   }

   public static void findAllowedPowers(int sum) {
      if (sum <= allowedPowers[0]) {
         return;
      }

      int closestPower = 2;
      while (sum < closestPower) {
         sum *= 2;
      }

      for (int i = 0; i < 5; i++) {
         allowedPowers[i] = (int) closestPower;
         closestPower /= 2;
      }
   }

   public static int[][] removePath(int[][] board, ArrayList<Integer> path) {
      for (int pos : path) {
         int[] position = decompress(pos);
         board[position[0]][position[1]] = -1;
      }

      return board;
   }

   public static int[][] dropBoard(int[][] board) {
      for (int y = 7; y >= 1; y--) {
         for (int x = 0; x < 5; x++) {
            if (board[y][x] != -1) {
               continue;
            }

            int d = 1;
            while (y - d > 0) {
               if (board[y - d][x] != -1) {
                  board = dropPosition(board, y, x, d);
                  break;
               }
               d++;
            }
         }
      }

      return board;
   }

   public static int[][] dropPosition(int[][] board, int y, int x, int d) {
      for (int i = y; i >= y - d; i--) {
         board[y][x] = board[i][x];
      }
      for (int i = 0; i < d; i++) {
         board[i][x] = -1;
      }

      return board;
   }

   public static int[][] fillBoard(int[][] board) {
      int currentPower = 0;
      for (int y = 0; y < 8; y++) {
         for (int x = 0; x < 5; x++) {
            if (board[y][x] != -1) {
               continue;
            }

            board[y][x] = allowedPowers[currentPower];
            currentPower = (currentPower + 1) % 5;
         }
      }

      return board;
   }

   public static String unparseBoard(int[][] board) {
      StringBuilder str = new StringBuilder();
      for (int y = 0; y < 8; y++) {
         for (int x = 0; x < 5; x++) {
            str.append(board[y][x]);
            str.append(" ");
         }
      }

      return str.toString();
   }

   public static void printL(Object... objects) {
      for (Object value : objects) {
         System.out.print(value + " ");
      }
      System.out.println();
   }
}
