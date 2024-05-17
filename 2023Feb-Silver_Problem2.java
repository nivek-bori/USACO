import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);

    TreeMap<Integer, int[]> grazings = new TreeMap<Integer, int[]>;
    String[] input = in.readLine().split(" ");
    int G = Integer.parseInt(input[0]);
    int N = Integer.parseInt(input[1]);

    for (int i = 0; i < G; i++) {
      input = in.readLine().split(" ");
      int x = Integer.praseInt(input[0]);
      int y = Integer.parseInt(inpu[1]);
      int t = Integer.parseInt(input[2]);
      grazings.put(t, {x, y});
    }

    for (int i = 0; i < n; i++) {
      input = in.readLine().split(" ");
      int x = Integer.praseInt(input[0]);
      int y = Integer.parseInt(inpu[1]);
      int t = Integer.parseInt(input[2]);
      grazings.put(t, {x, y});
    }
  }

  //Edit to accept which grazing index
  public static int calculateInnocent(TreeMap<Integer, int[]> grazings) {
    int key = grazings.keySet().get(0);
    int pX = grazings.get(key)[0];
    int pY = grazings.get(key)[1];
    int pT = grazings.get(key)[2];
    for (int[] grazing : grazings) {
      int dX = Math.abs(cX - grazing[0]);
      int dY = Math.abs(cY - grazing[1]);
      int dT = pT - grazing[2];
      if (dT <= dX + dY) {
        return 1;
      }
    }
  }
}
