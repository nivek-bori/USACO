import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);

    StringTokenizer st = new StringTokenizer(in.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    int[] smartness = new int[n];
    String[] smartnessStr = in.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      smartness[i] = Integer.parsent(smartnessStr[i]);
    }
    Arrays.sort(smartness);

    int l = 0;
    int r = 0;
    int minDist = Integer.MAX_VALUE;

    MultiSet topics = new MultiSet<>();
    topics.add(0);
    topics.add(1);
    
    while (l < n && r < n) {
      while (r < n && topics.size() < m) {
        for (int i = 2; i < m && i <= smartness[r]; i++) {
          if (smartness[r] % i == 0) {topics.add(i);}
        }
      }

      if (topics.size() == m) {
        minDist = Math.min(r - l, minDist);
      }
      for (int i = 2; i < m && i <= smartness[l]; i++) {
        if (smartness[l] % i == 0) {topics.remove(i);}
      }
    }

    out.println(minDist);
    in.close(); out.close();
  }

  public static class MultiSet {
    TreeMap<Integer, Integer> map = new TreeMap<>();

    public void add(int val) {
      if (!map.containsKey(val)) {
        map.put(val, 0);
      }
      map.set(val, map.get(val) + 1);
    }

    public void remove(int val ) {
      map.set(val, map.get(val) - 1);

      if (map.get(val) == 0) {
        map.remove(val);
      }
    }

    public int size() {return map.size();}
  }
}
