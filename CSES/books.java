import java.io.*;
import java.util.*;

public class books {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[] values = new int[n];
        st = new StringTokenizer(in.readLine());

        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 1;
        int sum = values[left];

        int maxDelta = 0;
        if (sum < t) {
            maxDelta = 1;
        }

        while (right < n) {
            if (sum + values[right] <= t) {
                sum += values[right++];
                maxDelta = Math.max(right - left, maxDelta);
            } else {
                sum -= values[left];
                left++;

                if (left == right) {
                    sum += values[right++];

                    if (sum < t) {
                        maxDelta = Math.max(1, maxDelta);
                    }
                }
            }
        }

        out.println(maxDelta);
        in.close();
        out.close();

    }
}
