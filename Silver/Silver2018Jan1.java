import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean print = false;
        BufferedReader in = new BufferedReader(new FileReader("lifeguards.in"));
        PrintWriter out = new PrintWriter(new FileWriter("lifeguards.out"));
        
        // BufferedReader in = new BufferedReader(new FileReader("system.in"));
        // PrintWriter out = new PrintWriter(new FileWriter("system.out"));
        // print = true;

        int n = Integer.parseInt(in.readLine());

        ArrayList<Event> events = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            events.add(new Event(a, i, true));
            events.add(new Event(b, i, false));
        }
        Collections.sort(events);
        

        HashSet<Integer> active = new HashSet<>();
        int[] soloTime = new int[n];
        int coveredTime = 0;

        int tLast = 0;
        for (Event event : events) {
            // if (print) {out.println("Active: "); for (int val : active) {out.print(val + " ");} out.println();} // REMOVE

            if (active.size() > 0) {
                coveredTime += event.t - tLast;
                
                if (active.size() == 1) {
                    for (int val : active) {
                        soloTime[val] += event.t - tLast;
                    }
                }
            }

            if (event.action) {active.add(event.i);}
            else {active.remove(event.i);}

            tLast = event.t;
        }
        // if (print) {out.println(); out.println(coveredTime); out.println();} // REMOVE

        // if (print) {for (int val : soloTime) {out.print(val + " ");} out.println(); out.println();} // REMOVE

        int minSolo = Integer.MAX_VALUE;
        for (int time : soloTime) {
            minSolo = Math.min(time, minSolo);
        }

        out.println(coveredTime - minSolo);
        in.close();
        out.close();
    }

    public static class Event implements Comparable<Event> {
        public int t, i;
        public boolean action;

        public Event(int t, int i, boolean action) {
            this.t = t;
            this.i = i;
            this.action = action;
        }

        public int compareTo(Event other) {
            return Integer.compare(t, other.t);
        }
    }
}
