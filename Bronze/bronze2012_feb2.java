import java.io.*;

public class bronze2012_feb2 {
   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(new FileReader("cowtip.in"));
      PrintWriter out = new PrintWriter(new FileWriter("cowtip.out"));

      int n = Integer.parseInt(in.readLine());
      String[] linesStr = new String[n];

      for (int i = 0; i < n; i++) {
         linesStr[i] = in.readLine();
      }

      int cnt = 0;
      boolean[] columns = new boolean[n];
      for (int i = n - 1; i >= 0; i--) {
         String lineStr = linesStr[i];
         for (int j = n - 1; j >= 0; j--) {
            if (lineStr.substring(j, j + 1).equals("1") != columns[j]) {
               cnt++;

               for (int k = 0; k < j; k++) {
                  columns[k] = !columns[k];
               }
            }
         }
      }

      out.println(cnt);
      in.close(); out.close();
   }
}
