import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    boolean testing = false;

    BufferedReader in;
    PrintWriter out;

    if (!testing) {
      in = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
    } else {
      in = new BufferedReader(new FileReader("system.in"));
      out = new PrintWriter(new FileWriter("system.out"));
    }

    

    in.close(); out.close();
  }
}
