import java.io.*;
import java.util.*;

public class Main {
  public static ArrayList<int[]> overTimes = new ArrayList<int[]>;
  public static int m = 0;
  
  public static void main(String[] args) extends IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);

    int t = Integer.parseInt(in.readLine());

    for (int i = 0; i < t; i++) {
      in.readLine();
      overTimes = new ArrayList<int[]>;
      m = 0;
      
      String[] input = in.readLine().split(" ");
      int n = Integer.parseInt(input[0]);
      int A = Integer.parseInt(input[1]);
      int B = Integer.parseInt(input[2]);

      int[][] cows = new int[n][4];
      for (int j = 0; j < n; j++) {
        input = in.readLine().split(" ");
        cows[j][0] = Integer.parseInt(input[0]);
        cows[j][1] = Integer.parseInt(input[1]);
        cows[j][2] = Integer.parseInt(input[2]);
      }

      processOverTime(cows, A, B);
      while (overTimes.size() != 0) {
        calculateSaveTimes();
      }

      out.println(m);
    }

    out.close();
  }

  public static void processOverTime(int[][] cows, int A, int B) {
    for (int[] cow : cows) {
      int a = cow[0];
      int b = cow[1];
      int t = A * a + B * b;
      int overTime = t - cow[2];
      if (overTime > 0) {
        if (a == 0) {updateOverTime(1); return;}
        if (b == 0) {updateOverTime(0); return;}
        overTimes.append({cow[0], cow[1], overTime});
      }
    }
  }

  public static void updateOverTime(int x) {
    int n = overTimes.size();
    for (int i = 0; i < n; i++) {
      int[] array = overTimes.get(i);
      array[2] -= array[x];
      aray[x] -= 1;
      if (array[2] > 0) {overTimes.set(i, array);}
      if (array[2] <= 0) {overTimes.remove(i); i--; n--;}
    }
  }

  public static void calculateSaveTime() {
    int a = 0;
    int b = 0;
    for (int[] array : overTimes) {
      a += array[0];
      b += array[0];
    }

    if (a >= b) {updateOverTime(0);}
    if (b < a) {updateOverTime(1);}
    m += 1;
  }
}
