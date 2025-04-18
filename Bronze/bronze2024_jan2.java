import java.io.*;
import java.util.*;

public class bronze2024_jan2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
    
        HashMap<Integer, LinkedList<Integer>> removedX = new HashMap<>();
        HashMap<Integer, LinkedList<Integer>> removedY = new HashMap<>();
        HashMap<Integer, LinkedList<Integer>> removedZ = new HashMap<>();
        int cnt = 0;
        
        int term;
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            term = n * x + y;
            if (!removedZ.containsKey(term)) {
                removedZ.put(term, new LinkedList<>());
            }
            removedZ.get(term).add(z);
            if (removedZ.get(term).size() == n) {
                cnt++;
            }

            term = n * y + z;
            if (!removedX.containsKey(term)) {
                removedX.put(term, new LinkedList<>());
            }
            removedX.get(term).add(x);
            if (removedX.get(term).size() == n) {
                cnt++;
            }

            term = n * x + z;
            if (!removedY.containsKey(term)) {
                removedY.put(term, new LinkedList<>());
            }
            removedY.get(term).add(y);
            if (removedY.get(term).size() == n) {
                cnt++;
            }

            out.println(cnt);
        }

        in.close();
        out.close();
    }



}
