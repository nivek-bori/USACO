import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean print = false;
        BufferedReader in = new BufferedReader(new FileReader("mountains.in"));
        PrintWriter out = new PrintWriter(new FileWriter("mountains.out"));
        
        // BufferedReader in = new BufferedReader(new FileReader("system.in"));
        // PrintWriter out = new PrintWriter(new FileWriter("system.out"));
        // print = true;
        
        int n = Integer.parseInt(in.readLine());

        ArrayList<PairY> peaks = new ArrayList<>();
        for (int i = 0; i< n; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            peaks.add(new PairY(y, x + y, y - x));
        }
        Collections.sort(peaks);

        // REMOVE
        if (print) {for (PairY pair : peaks) {out.println(pair.X + " " + pair.Y);} out.println();}
        // REMOVE
       
        HashSet<PairD> truePeaks = new HashSet<>();
        for (PairY peak : peaks) {
            // REMOVE
            if (print) {for (PairD pair : truePeaks) {out.print("(" + pair.x + ", " + pair.y + ") ");} out.println();}
            // REMOVE

            boolean add = true;
            for (PairD truePeak : truePeaks) {
                if (peak.Y <= truePeak.y && peak.X <= truePeak.x) {add = false; break;}
            }

            if (add) {truePeaks.add(peak.convert());}

            // REMOVE
            if (print) {out.println(peak.X + " " + peak.Y + " " + add); out.println();}
            // REMOVE
        }

        out.println(truePeaks.size());
        in.close();
        out.close();
    }

    public static class PairY implements Comparable<PairY> {
        public int y, X, Y;
        public PairY(int y, int X, int Y) {
            this.y = y;
            this.X = X;
            this.Y = Y;
        }

        public int compareTo(PairY other) {
            return Integer.compare(other.y, y);
        }

        public PairD convert() {
            return new PairD(X, Y);
        }
    }

    public static class PairD implements Comparable<PairD> {
        public int x, y;
        public int d;
        public PairD(int x, int y) {
            this.x = x;
            this.y = y;
            this.d = x + y;
        }

        public int compareTo(PairD other) {
            return Integer.compare(other.d, d);
        }
    }
}
