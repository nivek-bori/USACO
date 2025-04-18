import java.io.*;

public class bronze2024_jan3 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
//        BufferedReader in = new BufferedReader(new FileReader("src/system.in"));
//        PrintWriter out = new PrintWriter(new FileWriter("src/system.out"));

        int n = Integer.parseInt(in.readLine());

        String[] fieldStr = in.readLine().split(" ");
        long sum = 0;
        long delta = 0;
        long cnt = 0;
        for (int i = 0; i < n; i++) {
            long val = Long.parseLong(fieldStr[i]);
            sum += delta;
            cnt += Math.abs(sum + val);
            delta -= sum + val;
            sum -= sum + val;
        }

        out.println(cnt);
        in.close();
        out.close();
    }
}
