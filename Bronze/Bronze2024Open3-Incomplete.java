import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        if (Integer.parseInt(writtenStr[n - 1]) != 1) {
            return false;
        }
        
        if (n == 1) {return true;}
        
        int[] written = new int[n];
        for (int i = 0; i < n; i++) {
            written[i] = Integer.parseInt(writtenStr[i]);
            if (written[i] == 1 && i < n - 2) {
                return false;
            }
        }
        
        LinkedList<Integer> missing = getMissing(written, n);
        if (missing.size() > 2) {
            return false;
        }
        if (missing.size() == 1){
            if (written[n - 2] == 1) {
                return false;
            } else {
                missing.insert(0, 1);
            }
        }
        
        int lo = 0;
        int hi = n - 1;
        written[0] = mising.get(0);
        written[n - 1] = missing.get(1);
        
        for (int val : written) {
            if (written[lo] > written[hi]) {
                lo++;
                written[lo] = val;
            } else {
                hi--;
                written[hi] = val;
            }
        }
        
        for (int val : written) {
            out.print(val + " ");
        }
        out.println();
        
        in.close();
        out.close();
    }
}
