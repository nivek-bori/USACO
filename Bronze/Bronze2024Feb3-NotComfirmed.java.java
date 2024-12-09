import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        // BufferedReader in = new BufferedReader(new FileReader("system.in"));
        // PrintWriter out = new PrintWriter(new FileWriter("system.out"));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        String[] endsStr = in.readLine().split(" ");
        String[] timesStr = in.readLine().split(" ");
        
        LinkedList<ReverseInteger> requiredT = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            int end = Integer.parseInt(endsStr[i]);
            int time =  Integer.parseInt(timesStr[i]);
            requiredT.add(new ReverseInteger(end - time));
        }
        Collections.sort(requiredT);

        LinkedList<ReverseInteger> wakeUpToCount = new LinkedList<>();
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(in.readLine());
            int requiredCount = Integer.parseInt(st.nextToken());
            int wakeUpT = Integer.parseInt(st.nextToken());

            boolean done = false;
            for (ReverseInteger pair : wakeUpToCount) {
                if (pair.value > wakeUpT) {
                    if (pair.secondValue >= requiredCount) {
                        out.println("YES");
                        done = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (done) {continue;}

            int count = 0;
            for (ReverseInteger required : requiredT) {
                if (wakeUpT < required.value) {
                    count++;
                } else {
                    break;
                }
            }

            out.println((requiredCount <= count) ? "YES" : "NO");
            wakeUpToCount.add(new ReverseInteger(wakeUpT, count));
            Collections.sort(wakeUpToCount);
        }

        in.close();
        out.close();
    }

    public static class ReverseInteger implements Comparable<ReverseInteger> {
        int value, secondValue;

        public ReverseInteger(int value) {
            this.value = value;
        }

        public ReverseInteger(int value, int secondValue) {
            this.value = value;
            this.secondValue = secondValue;
        }

        public int compareTo(ReverseInteger other) {
            return Integer.compare(other.value, value);
        }
    }
}
