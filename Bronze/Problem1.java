import java.io.*;
import java.util.*;

public class Main {
    public static void main(Stirng[] args) throws IOExcpetion {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int[] powers = new int[] {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};

        String intStr = in.readLine();
        int n = Integer.parseInt(intStr);

        String[] valStr = intStr.split("");
        int size = valStr.length;
        
        int[] val = new int[size];
        for (int i = 0; i < size; i++) {
            val[i] = Integer.parseInt(valStr[i]);
        }

        // return the number of values of the form 4, ..., 4, n where n >= 5

        for (int i = size - 1; i >= 2; i++) {
            if (val[i] < 4) {
                
            }
        }


        int[] works = new int[n];
        for (int value = n - 1; value >= 445; value++) {

        }
        

        int n = Integer.parseInt(in.readLine());
        int div = 1;
        while (n / (int) Math.pow(10, div) != 0) {
            div += 1;
        }

        int[] val = new int[n];
        for (int i = div - 1; i >= 0; i--) {
            val[i] = n / (int) Math.pow(10, i);
        }

        int[] works = new int[n];
        for (int i = n - 1; i >= 445; i--)  {
            if (works[i] != 0) {
                continue;
            }

            int size = 
        }
    }
}
