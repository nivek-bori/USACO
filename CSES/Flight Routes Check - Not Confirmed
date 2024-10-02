import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class USACOTemp {
   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);

      StringTokenizer st = new StringTokenizer(in.readLine());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());

      HashMap<Integer, LinkedList<Integer>> flights = new HashMap<>();
      for (int i = 0; i < m; i++) {
         st = new StringTokenizer(in.readLine());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());

         if (!flights.containsKey(a)) {
            flights.put(a, new LinkedList<>());
         }
         flights.get(a).add(b);
         if (!flights.containsKey(b)) {
            flights.put(b, new LinkedList<>());
         }
         flights.get(b).add(a);
      }

      boolean[] visited = new boolean[n];
      LinkedList<Integer> toVisitCities = new LinkedList<>();
      toVisitCities.add(0);

      int city;
      while (toVisitCities.size() > 0) {
         city = toVisitCities.pop();
         if (visited[city]) {
            continue;
         }

         toVisitCities.addAll(flights.get(city));
      }

      int trueI = -1;
      int falseI = -1;

      for (int i = 0; i < n; i++) {
         if (visited[i]) {
            trueI = i;
         } else {
            falseI = i;
         }
      }

      if (falseI == -1) {
         out.println("YES");
      } else {
         out.println("NO");
         out.println(trueI + " " + falseI);
      }

      in.close();
      out.close();
   }
}
