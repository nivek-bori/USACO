import java.io.*;
import java.util.*;

public class bronze2024_feb3 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        // BufferedReader in = new BufferedReader(new FileReader("system.in"));
        // PrintWriter out = new PrintWriter(new FileWriter("system.out"));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        String[] endsStr = in.readLine().split(" ");
        String[] timesStr = in.readLine().split(" ");

        Integer[] requiredWakeUpTimes = new Integer[n];
        for (int i = 0; i < n; i++) {
            requiredWakeUpTimes[i] = Integer.parseInt(endsStr[i]) - Integer.parseInt(timesStr[i]) - 1;
        }
        Arrays.sort(requiredWakeUpTimes, Collections.reverseOrder());

        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(in.readLine());
            int requiredCount = Integer.parseInt(st.nextToken());
            int wakeUp = Integer.parseInt(st.nextToken());

            int lo = 0;
            int hi = n - 1;
            while (lo < hi) {
                int mid = (lo + hi) / 2;

                if (wakeUp <= requiredWakeUpTimes[mid]) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }

            if (requiredCount <= lo) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }

        in.close();
        out.close();
    }
}
