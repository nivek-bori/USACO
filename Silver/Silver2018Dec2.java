import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean print = false;
        BufferedReader in = new BufferedReader(new FileReader("convention2.in"));
        PrintWriter out = new PrintWriter(new FileWriter("convention2.out"));
        
        // BufferedReader in = new BufferedReader(new FileReader("system.in"));
        // PrintWriter out = new PrintWriter(new FileWriter("system.out"));
        // print = true;

        int n = Integer.parseInt(in.readLine());
        ArrayList<Cow> cows = new ArrayList<>(); // Sorted by arrival time

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            cows.add(new Cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i));
        }
        Collections.sort(cows);

        TreeSet<Cow> waiting = new TreeSet<>(); // Sorted by senority
        HashSet<Integer> processed = new HashSet<>();
        int currT = 0;
        int maxWaiting = 0;
        int i = 0;
        while (i < n || waiting.size() > 0) {
            Cow nextCow;
            if (waiting.size() == 0) {nextCow = cows.get(i); processed.add(i); i++;}
            else {nextCow = waiting.pollFirst();}
            
            if (nextCow.arrive >= currT) {
                currT = nextCow.arrive + nextCow.length + 1;
            } else {
                maxWaiting = Math.max(currT - nextCow.arrive, maxWaiting);
                currT += nextCow.length;
            }

            if (print) { // REMOVE
                out.print(nextCow.i + " " + currT + ": ");
                for (Cow temp : waiting) {out.print(temp.i + " ");}
                out.println();
            } // REMOVE

            while (i < n && cows.get(i).arrive <= currT) {
                if (processed.add(i)) {waiting.add(cows.get(i).switchSort());}
                i++;
            }
        }

        out.println(maxWaiting - 1);
        in.close(); out.close();
    }

    public static class Cow implements Comparable<Cow> {
        int arrive, length, i;
        boolean sortTime = true;

        public Cow(int arrive, int length, int i) {
            this.arrive = arrive;
            this.length = length;
            this.i = i;
        }

        public Cow switchSort() {
            sortTime = false;
            return this;
        }

        public int compareTo(Cow other) {
            if (sortTime) {
                int retA = Integer.compare(this.arrive, other.arrive);
                if (retA != 0) {return retA;}
            }

            int retB = Integer.compare(this.i, other.i);
            return retB;

        }
    }
}

