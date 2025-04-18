import java.io.*;
import java.util.*;
public class InComp_R845_Div2 {
   public static void main(String[] args) throws IOException {
      boolean print = false;
//      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//      PrintWriter out = new PrintWriter(System.out);

      BufferedReader in = new BufferedReader(new FileReader("src/system.in"));
      PrintWriter out = new PrintWriter(new FileWriter("src/system.out"));
      print = true;

      int T = Integer.parseInt(in.readLine());

      for (int t = 0; t < T; t++) {
         StringTokenizer st = new StringTokenizer(in.readLine());
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());

         int[] smartness = new int[n];
         String[] smartnessStr = in.readLine().split(" ");
         for (int i = 0; i < n; i++) {
            smartness[i] = Integer.parseInt(smartnessStr[i]);
         }
         Arrays.sort(smartness);

         int l = 0;
         int r = 0;
         int minDist = Integer.MAX_VALUE;

         MultiSet topics = new MultiSet();

         while (l < n && r < n) {
            while (r < n && topics.size() < m) {
               for (int i = 1; i < m && i <= smartness[r]; i++) {
                  if (smartness[r] % i == 0) {topics.add(i);}
               }
               r++;
            }

            // REMOVE
            if (print) {
               for (int val : topics.map.keySet()) {
                  out.print(val + " ");
               }
               out.println();
            }
            // REMOVE

            // REMOVE
//            if (print) {
//               if (topics.size() == m && smartness[r - 1] - smartness[l] < minDist) {
//                  out.println("A" + (smartness[r - 1] - smartness[l]) + " " + r + " " + l);
//               }
//            }
            // REMOVE

            if (topics.size() == m) {minDist = Math.min(smartness[r - 1] - smartness[l], minDist);}

            for (int i = 1; i < m && i <= smartness[l]; i++) {
               if (smartness[l] % i == 0) {topics.remove(i);}
            }
            l++;
         }

         out.println((minDist == Integer.MAX_VALUE) ? -1 : minDist);
      }

      in.close(); out.close();
   }

   public static class MultiSet {
      TreeMap<Integer, Integer> map = new TreeMap<>();

      public void add(int val) {
         if (!map.containsKey(val)) {
            map.put(val, 0);
         }
         map.put(val, map.get(val) + 1);
      }

      public void remove(int val ) {
         map.put(val, map.get(val) - 1);
         if (map.get(val) == 0) {
            map.remove(val);
         }
      }

      public int size() {return map.size();}
   }
}
