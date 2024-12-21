import java.io.*;
import java.util.*;

public class Main {

    private static int height;
    private static int[][] board;

    private static LinkedList<Integer> checkReq = new LinkedList<>();
    private static LinkedList<Integer> fallReq = new LinkedList<>();

    private static HashSet<Integer> visited = new HashSet<>();

    private static int[] xChng = {0, 1, 0, -1};
    private static int[] yChng = {1, 0, -1, 0};


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("mooyomooyo.in"));
        PrintWriter out = new PrintWriter(new FileWriter("mooyomooyo.out"));
        // BufferedReader in = new BufferedReader(new FileReader("system.in"));
        // PrintWriter out = new PrintWriter(new FileWriter("system.out"));

        String[] constantsStr = in.readLine().split(" ");
        height = Integer.parseInt(constantsStr[0]);
        int k = Integer.parseInt(constantsStr[1]);

        board = new int[height][10];

        for (int i = 0; i < height; i++) {
            String[] lineStr = in.readLine().split("");
            for (int j = 0; j < 10; j++) {
                board[i][j] = Integer.parseInt(lineStr[j]);

                if (board[i][j] != 0) {
                    checkReq.add(encode(i, j));
                }
            }
        }

        while (true) {
            // Checking connections
            fallReq = new LinkedList<>();
            visited = new HashSet<>();

            while (checkReq.size() > 0) {
                int val = checkReq.pop();
                if (visited.contains(val)) {continue;}

                int[] pos = decode(val);

                HashSet<Integer> connectors = floodfill(pos[0], pos[1], board[pos[0]][pos[1]], new HashSet<>());

                if (connectors.size() >= k) {
                    fallReq.addAll(connectors);
                }
            }

            // Checking if board changes
            if (fallReq.isEmpty()) {
                break;
            }
            
            // Falling values
            checkReq = new LinkedList<>();
            
            Collections.sort(fallReq);
            while (fallReq.size() > 0) {
                int[] pos = decode(fallReq.pop());

                checkReq = fall(pos, checkReq);
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < 10; x++) {
                out.print(board[y][x]);
            }
            out.println();
        }

        in.close();
        out.close();
    }


    public static HashSet<Integer> floodfill(int y, int x, int value, HashSet<Integer> connectors) {
        if (x < 0 || x >= 10 || y < 0 || y >= height || value != board[y][x] || board[y][x] == 0) {return new HashSet<>();}

        int val = encode(y, x);
        if (visited.contains(val)) {return new HashSet<>();}
        visited.add(val);

        connectors.add(val);

        for (int i = 0; i < 4; i++) {
            connectors.addAll(floodfill(y + yChng[i], x + xChng[i], value, connectors));
        }

        return connectors;
    }

    public static LinkedList<Integer> fall(int[] pos, LinkedList<Integer> ret) {
        int y = pos[0];
        int x = pos[1];

        for (int Y = y; Y > 0; Y--) {
            board[Y][x] = board[Y - 1][x];
            ret.add(encode(Y, x));
        }
        board[0][x] = 0;

        return ret;
    }
    

    public static int encode(int y, int x) {
        return 100 * y + x;
    }

    public static int[] decode(int val) {
        return new int[] {val / 100, val % 100};
    }
}
