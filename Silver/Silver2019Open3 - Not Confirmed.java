import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[] cowX = new int[n];
        int[] cowY = new int[n];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            cowX[i] = Integer.parseInt(st.nextToken()));
            cowY[i] = Integer.parseInt(st.nextToekn());
        }
        
        HashMap<Integer, ArrayList<Integer>> connections = new HashMap<>();
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (!connections.contains(a)) {
                connections.put(a, new ArrayList<Integer>);
            }
            connections.get(a).add(b);
            if (!connections.contains(b)) {
                connections.put(b, new ArrayList<Integer>);
            }
            connections.get(b).add(a);
        }
        
        int minPerimeter = Integer.MAX_INTEGER;
        
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
                for (int nextCow : connections.get(j)) {
                    toBeVisited.push(nextCow);
                    
                    minX = Math.min(minX, cowX[j]);
                    minY = Math.min(minY, cowY[j]);
                    maxX = Math.max(maxX, cowX[j]);
                    maxY = Math.max(maxY, cowY[j]);
                }
            }
            
            minPerimenter = Math.min(minPerimeter, (maxX - minX) + (maxY - minY));
        }

      
    }
}
