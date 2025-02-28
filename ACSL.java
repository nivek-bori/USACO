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

   public static int[] allowedPowers = new int[] { 256, 128, 64, 32, 16, 8, 4, 2 };

   public static void main(String[] args) throws IOException {
      BufferedInputStream in = new BufferedInputStream(System.in);
      PrintWriter out = new PrintWriter(System.out);

      // String input = in.toString();
      String input = "4 128 4 128 32 16 16 4 256 16 32 4 16 64 4 8 64 64 256 8 16 2 2 256 4 32 128 2 64 8 256 32 128 16 2 8 32 32 4 32";

      // printLN("Input", parseBoard(input));
      String output = solve(input);
      printLN(output);

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
         // printLN("Max Depth Before", maxDepth, maxDepthI); // REMOVE
         findMaxDepth();
         // printLN("Max Depth", maxDepth, maxDepthI); // REMOVE

         board = updateBoard(board);

         printLN("First", unparseBoard(board));
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
         findNextDepth(0, i + 1, new ArrayList<Integer>());
      }
   }

   public static void findNextDepth(int depth, int pos, ArrayList<Integer> previous) {
      depth += 1;
      previous.add(pos);
      updateDepth(depth, previous);

      int[] position = decompress(pos);
      ArrayList<Integer> paths = (depth != 1) ? toConnections.get(position[0]).get(position[1])
            : toSameConnections.get(position[0]).get(position[1]);
      for (int path : paths) {
         if (previous.contains(path)) {
            continue;
         }

         findNextDepth(depth, path, new ArrayList<>(previous));
      }
   }

   public static void updateDepth(int depth, ArrayList<Integer> path) {
      if (depth > maxDepth) {
         maxDepth = depth;
         maxDepthI = new ArrayList<>();
         maxDepthI.add(path);
      } else if (depth == maxDepth) {
         maxDepthI.add(path);
      }
   }

   public static void findMaxDepth() {
      if (maxDepthI.size() == 1) {
         return;
      }

      int maxLength = 0;
      for (ArrayList<Integer> maxPath : maxDepthI) {
         maxLength = Math.max(maxPath.size(), maxLength);
      }

      for (int t = 0; t < maxLength; t++) {
         int maxValue = 0;
         for (int i = 0; i < maxDepthI.size(); i++) {
            ArrayList<Integer> path = maxDepthI.get(i);

            if (path.size() <= t) {
               maxDepthI.remove(i);
               i--;
               continue;
            }

            int value = path.get(i);
            if (value > maxValue) {
               for (int j = 0; j < i;) {
                  maxDepthI.remove(0);
                  i--;
               }
               continue;
            }
            if (value < maxValue) {
               maxDepthI.remove(i);
               i--;
               continue;
            }
         }

         if (maxDepthI.size() == 1) {
            break;
         }

         for (ArrayList<Integer> maxPath : maxDepthI) {
            maxLength = Math.max(maxPath.size(), maxLength);
         }
      }
   }

   public static int[][] updateBoard(int[][] board) {
      ArrayList<Integer> path = maxDepthI.get(0);
      int sum = findSum(board, path);
      findAllowedPowers(sum);
      // printLN("Sum", sum, allowedPowers[0], allowedPowers[4]);

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
      while (closestPower < sum) {
         closestPower *= 2;
      }

      for (int i = 0; i < 8; i++) {
         allowedPowers[i] = (int) closestPower;
         closestPower /= 2;
      }
   }

   public static int[][] removePath(int[][] board, ArrayList<Integer> path) {
      for (int pos : path) {
         int[] position = decompress(pos);
         board[position[0]][position[1]] = -1;
      }

      int[] position = decompress(path.get(0));
      board[position[0]][position[1]] = allowedPowers[0];

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
      for (int i = y; i - d < 0; i++) {
         board[i][x] = board[i - d][x];
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
            currentPower = (currentPower + 1) % 8;
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

   public static void printLN(Object... objects) {
      for (Object value : objects) {
         System.out.print(value + " ");
      }
      System.out.println();
   }

   public static void printL(Object... objects) {
      for (Object value : objects) {
         System.out.print(value + " ");
      }
   }
}
