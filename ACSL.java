import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Result sim = new Result();
        sim.play2248(
                "4 128 4 128 32 16 16 4 256 16 32 4 16 64 4 8 64 64 256 8 16 2 2 256 4 32 128 2 64 8 256 32 128 16 2 8 32 32 4 32");
    }
}

public class Result {
    public static ArrayList<ArrayList<ArrayList<Integer>>> toConnections;
    public static ArrayList<ArrayList<ArrayList<Integer>>> fromConnections;

    public static int maxDepth = 0;
    public static ArrayList<Integer> maxDepthI = new ArrayList<>();

    public static int[] allowedPowers = new int[] { 32, 16, 8, 4, 2 };

    public String play2248(String board) {
        return solve(board);
    }

    public static String solve(String strBoard) {
        int[][] board = parseBoard(strBoard);

        for (int i = 0; i < 3; i++) {
            maxDepth = 0;
            maxDepthI = new ArrayList<>();

            findConnections(board);
            findDepth(connections.get(0));
            ArrayList<Integer> path = findMaxDepth(connections.get(1));

            board = updateBoard(board, path);
        }

        return unparseBoard(board);
    }

    public static int[][] parseBoard(String strBoard) {
        int[][] board = new int[8][5];
        String[] input = strBoard.split(" ");

        for (int i = 0; i < 40; i++) {
            int y = i / 5;
            int x = i % 5;

            board[y][x] = Integer.parseInt(input[i]);
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
            ArrayList<ArrayList<Integer>> sub1 = new ArrayList<>();
            for (int x = 0; x < 5; x++) {
                sub1.add(new ArrayList<Integer>());
            }
            toConnections.add(sub1);
            fromConnections.add(sub1);
        }

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 5; x++) {
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

                    if (y != check[0] && x != check[1] && (board[y][x] == board[yC][xC] || 2 * board[y][x] == board[yC][xC])) {
                        toConnections.get(y).get(x).add(compress(yC, xC));
                        fromConnections.get(yC).get(xC).add(compress(y, x));
                    }
                }
            }
        }
    }

    public static void findDepth(int[][][] to) {
        for (int i = 0; i < 40; i++) {
            findNextDepth(to, 0, i, new ArrayList<Integer>());
        }
    }

    public static void findNextDepth(int depth, int pos, ArrayList<Integer> previous) {
        depth += 1;
        updateDepth(depth, pos);

        int[] position = decompress(pos);
        ArrayList<Integer> paths = toConnections.get(position[0]).get(position[1]);
        for (int path : paths) {
            if (previous.contains(path)) {
                continue;
            }
            ArrayList<Integer> newPrevious = new ArrayList<>(previous);
            newPrevious.add(path);
            findNextDepth(depth, path, newPrevious);
        }
    }

    public static void updateDepth(int depth, int pos) {
        if (depth > maxDepth) {
            maxDepth = depth;
            maxDepthI = new ArrayList<>();
            maxDepthI.add(pos);
        } else if (depth == maxDepth) {
            maxDepthI.add(pos);
        }
    }

    public static ArrayList<Integer> findMaxDepth() {
        if (maxDepthI.size() == 1) {
            return nextMaxDepth(maxDepthI.get(0), new ArrayList<Integer>());
        }

        int maxPath = maxDepthI.get(0);
        for (int path : maxDepthI) {
            maxPath = Math.max(path, maxPath);
        }

        return nextMaxDepth(maxPath, new ArrayList<Integer>());
    }

    public static ArrayList<Integer> nextMaxDepth(int pos, ArrayList<Integer> currentPath) {
        currentPath.add(pos);
        int[] position = decompress(pos);

        ArrayList<Integer> paths = fromConnections.get(position[0]).get(position[1]);
        if (paths.size() == 0) {
            return currentPath;
        }
        if (paths.size() == 1) {
            return nextMaxDepth(from, paths.get(0), currentPath);
        }

        int maxPath = paths.get(0);
        for (int path : paths) {
            maxPath = Math.max(path, maxPath);
        }

        return nextMaxDepth(from, maxPath, currentPath);
    }

    public static int[][] updateBoard(int[][] board, ArrayList<Integer> path) {
        int sum = findSum(board, path);
        findAllowedPowers(sum);

        board = removePath(board, path);
        board = dropboard(board);
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
            boad[i][x] = -1;
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
        for (int i = 0; i < 39; i++) {
            str.append(value + " ");
        }
        str.append(board[39]);

        return str.toString();
    }
}
