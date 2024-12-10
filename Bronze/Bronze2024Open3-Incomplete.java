import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
//        BufferedReader in = new BufferedReader(new FileReader("src/system.in"));
//        PrintWriter out = new PrintWriter(new FileWriter("src/system.out"));

        int n = Integer.parseInt(in.readLine());

        if (n == 2) {
            out.println("1 2");
            return;
        }

        int countOne = 0;
        String[] valuesStr = in.readLine().split(" ");
        int[] values = new int[n - 1];

        if (Integer.parseInt(valuesStr[n - 2]) != 1) {
            out.println("-1");
            return;
        }

        for (int i = 0; i < n - 1; i++) {
            values[i] = Integer.parseInt(valuesStr[i]);
            if (values[i] == 1) {
                if (i < n - 2) {
                    out.println("-1");
                    break;
                }
                countOne++;
            }
        }

        if (countOne == 0 || countOne > 2) {
            out.println("-1");
            return;
        }

        if (countOne == 1) {
            for (int i = 0; i < n; i++) {
                out.print(i + " ");
            }
            out.println();
        } else {

        }
    }
}
