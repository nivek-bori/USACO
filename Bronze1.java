import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean testing = false;

        BufferedReader in;
        PrintWriter out;

        if (!testing) {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        } else {
            in = new BufferedReader(new FileReader("system.in"));
            out = new PrintWriter(new FileWriter("system.out"));
        }


        int T = Integer.parseInt(in.readLine());
        boolean nextTestCase = false;

        for (int t = 0; t < T; t++) {
            nextTestCase = false;

            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int[][] req = new int[n][n];
            ArrayList<Integer> doubles = new ArrayList<>(n / 4);

            for (int y = 0; y < n; y++) {
                String[] line = in.readLine().split("");
                for (int x = 0; x < n; x++) {
                    String str = line[x];
                    if (str.equals("W")) {
                        req[y][x] = 0;
                    } else if (str.equals("G")) {
                        req[y][x] = 1;
                    } else {
                        req[y][x] = 2;
                        doubles.add(n * y + x);
                    }
                }
            }

            boolean[][][] field = new boolean[n][n][2];

            int initStars = 0;

            for (int pos : doubles) {
                int y = pos / n;
                int x = pos % n;

                if (y - b < 0 || x - a < 0) {
                    nextTestCase = true;
                    break;
                }

                // Inverse start addition
                if (!field[y - b][x - a][0]) {
                    field[y - b][x - a][0] = true;
                    field[y][x][1] = true;
                    initStars++;
    
                    // Requirement check
                    int cntA = (field[y - b][x - a][0] ? 1 : 0) + (field[y - b][x - a][1] ? 1 : 0);
                    if (req[y - b][x - a] < cntA) {
                        nextTestCase = true;
                        break;
                    }
                }

                // Normal star addition
                if (!field[y][x][0]) {
                    field[y][x][0] = true;
                    if (y + b < n && x + a < n) {
                        int cntB = (field[y + b][x + a][0] ? 1 : 0) + (field[y + b][x + a][1] ? 1 : 0);

                        if (req[y + b][x + a] > cntB) {
                            field[y + b][x + a][1] = true;
                        }
                    }
                    initStars++;
                }
            }

            if (nextTestCase) {
                out.println(-1);
                continue;
            }

            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    if (req[y][x] == 1 && !field[y][x][0] && !field[y][x][1]) {
                        field[y][x][0] = true;
                        initStars++;

                        if (y + b < n && x + a < n && req[y + b][x + a] == 1 && !field[y + b][x + a][0] && !field[y + b][x + a][1]) {
                            field[y + b][x + a][1] = true;
                        }
                    }
                }
            }

            if (testing) {
                for (int y = 0; y < n; y++) {
                    for (int x = 0; x < n; x++ ){
                        out.print((field[y][x][0] ? 1 : 0) + "" + (field[y][x][1] ? 1 : 0) + " ");
                    }
                    out.println();
                }
            }

            out.println(initStars);
        }
        
        in.close(); out.close();
    }
}
