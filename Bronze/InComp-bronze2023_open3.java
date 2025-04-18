// Some test cases - Others time out
// Code isn't optimized enough

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        // BufferedReader in = new BufferedReader(new FileReader("system.in"));
        // PrintWriter out = new PrintWriter(new FileWriter("system.out"));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[] move = new int[k];
        String[] moveStr = in.readLine().split(" ");
        for (int i = 1; i < k; i++) {
            move[i] = Integer.parseInt(moveStr[i]);
        }
        
        LinkedList<int[]> moveStates = new LinkedList<>();
        moveStates.add(move);
        
        int temp = 0;
        int[] values = IntStream.range(0, n).toArray();
        for (int i = 0; i < n && i < T; i++) {
            // Apply Move
            temp = values[move[k - 1]];
            for (int j = k - 1; j > 0; j--) {
                values[move[j]] = values[move[j - 1]];
            }
            values[move[0]] = temp;
            
            // Update Move
            for (int j = 0; j < k; j++) {
                move[j] = (move[j] + 1) % k;
            }
            moveStates.add(move);
        }

        if (T <= n) {
            for (int i = 0; i < n - 1; i++) {
                out.print(values[i] + " ");
            }
            out.println(values[n - 1]);
            in.close();
            out.close();
            return;
        }

        // Optimized movement array
        int[] map = new int[n];
        for (int i = 0; i < n; i++) {
            map[values[i]] = i;
        }
        
        values = IntStream.range(0, n).toArray();
        for (int i = 0; i < T / n; i++) {
            values = applyMap(values, map);
        }
        for (int i = 0; i < T % n; i++) {
            move = moveStates.pop();
            moveStates.add(move);

            temp = values[move[k - 1]];
            for (int j = k; j > 0; j--) {
                values[move[j]] = values[move[j - 1]];
            }
            values[move[0]] = temp;
        }

        for (int i = 0; i < n - 1; i++) {
            out.print(values[i] + " ");
        }
        out.println(values[n - 1]);
        in.close();
        out.close();
    }

    public static int[] applyMap(int[] values, int[] map) {
        int n = map.length;
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[map[i]] = values[i];
        }
        return ret;
    }
}
