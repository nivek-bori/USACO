import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("fenceplan.in"));
        PrintWriter out = new PrintWriter(new FileWriter("fenceplan.out"));

        StringTokenizer st = new StringTokenizer(in.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] cowX = new int[n];
        int[] cowY = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            cowX[i] = Integer.parseInt(st.nextToken());
            cowY[i] = Integer.parseInt(st.nextToken());
        }

        HashMap<Integer, ArrayList<Integer>> connections = new HashMap<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (!connections.containsKey(a)) {
                connections.put(a, new ArrayList<>());
            }
            connections.get(a).add(b);
            if (!connections.containsKey(b)) {
                connections.put(b, new ArrayList<>());
            }
            connections.get(b).add(a);
        }

        int minPerimeter = Integer.MAX_VALUE;

        boolean[] visited = new boolean[m];
        for (int i = 0; i < m; i++) {
            if (visited[i]) {continue;}

            int minX = cowX[i];
            int minY = cowY[i];
            int maxX = cowX[i];
            int maxY = cowY[i];

            LinkedList<Integer> toBeVisited = new LinkedList<>();
            toBeVisited.add(i);
            while (toBeVisited.size() > 0) {
                int j = toBeVisited.pop();
                if (visited[j]) {continue;}
                visited[j] = true;
                for (int nextCow : connections.get(j)) {
                    toBeVisited.push(nextCow);

                    minX = Math.min(minX, cowX[j]);
                    minY = Math.min(minY, cowY[j]);
                    maxX = Math.max(maxX, cowX[j]);
                    maxY = Math.max(maxY, cowY[j]);
                }
            }

            minPerimeter = Math.min(minPerimeter, (maxX - minX) + (maxY - minY));
        }

        out.println(minPerimeter);
        in.close();
        out.close();
    }
}
