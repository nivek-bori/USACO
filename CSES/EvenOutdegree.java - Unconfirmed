class Main {
    private static boolean kill = false;
    
    private static int[] head;
    private static int[] to;
    private static int[] next;
    private static boolean[] degree;
    private static int[] direction;
    
    public static void main(String[] args) {
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        head = new int[n];
        to = new int[2 * m];
        next = new int[2 * m];
        degree = new boolean[n];
        direction = new int[2 * m];
        Arrays.fill(head, -1);
        Arrays.fill(direction, -1);
        
        for (int i = 0; i < 2 * m; i += 2) {
            String[] strI = in.readLine().split(" ");
            int a = Integer.parseInt(strI[0]);
            int b = Integer.parseInt(strI[1]);
            
            next[i] = head[i];
            head[i] = a;
            to[i] = b;
            next[i + 1] = head[i + 1];
            head[i + 1] = b;
            to[i + 1] = a;
        }
        
        dfs(0);
        
        if (kill) {
            out.println("IMPOSSIBLE");
        } else {
            for (int nodeDegree : degree) {
                out.print(nodeDegree + " ");
            }
            out.println():
        }
        
        in.close();
        out.close();
    }
    
    private static void dfs(int node) {
        int edge = head[node];
        int pairEdge = edge + (edge % 2 == 0) ? 1 : -1;
        while (edge != -1 && !kill) {
            if (direction[edge] == -1) {
                int toNode = to[edge];
                if (degree[node]) {
                    direction[edge] = toNode;
                    direction[pairEdge] = toNode;
                    degree[toNode] = !degree[toNode];
                } else {
                    direction[edge] = node;
                    direction[pairEdge] = node;
                    degree[node] = true;
                }
                dfs(toNode);
            }
                
            edge = next[edge];
        }
        
        kill = !degree[node];
    }
}
