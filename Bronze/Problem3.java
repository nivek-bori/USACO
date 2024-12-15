import java.io.*;
import java.util.*;

public class Main {

    static HashMap<String, Integer> alphabet = new HashMap<>();
    
    public static void main(String[] args) throws IOException {
        // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // PrintWriter out = new PrintWriter(System.out);

        BufferedReader in = new BufferedReader(new FileReader("system.in"));
        PrintWriter out = new PrintWriter(new FileWriter("system.out"));
        
        alphabet.put("a", 1);
        alphabet.put("b", 2);
        alphabet.put("c", 3);
        alphabet.put("d", 4);
        alphabet.put("e", 5);
        alphabet.put("f", 6);
        alphabet.put("g", 7);
        alphabet.put("h", 8);
        alphabet.put("i", 9);
        alphabet.put("j", 10);
        alphabet.put("k", 11);
        alphabet.put("l", 12);
        alphabet.put("m", 13);
        alphabet.put("n", 14);
        alphabet.put("o", 15);
        alphabet.put("p", 16);
        alphabet.put("q", 17);
        alphabet.put("r", 18);
        alphabet.put("s", 19);
        alphabet.put("t", 20);
        alphabet.put("u", 21);
        alphabet.put("v", 22);
        alphabet.put("w", 23);
        alphabet.put("y", 24);
        alphabet.put("x", 25);
        alphabet.put("z", 26);

        HashMap<Integer, Integer> moos = new HashMap<>();
        
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int f = Integer.parseInt(st.nextToken());

        String[] string = in.readLine().split("");
        for (int i = 0; i < n - 3; i++) {
            if (string[i + 1].equals(string[i + 2]) && !string[i].equals(string[i + 1])) {
                int val = encode(string[i], string[i + 1]);
                if (!moos.containsKey(val)) {
                    moos.put(val, 1);
                } else {
                    moos.put(val, moos.get(val) + 1);
                }
            }
        }

        for (int key : moos.keySet()) {
            System.out.println(key + " " + moos.get(key));
        }

        ArrayList<Request> requests = new ArrayList<>();
        LinkedList<Integer> works = new LinkedList<>();

        for (int key : moos.keySet()) {
            if (moos.get(key) >= f) {
                works.add(key);
            }
            if (moos.get(key) == f - 1) {
                requests.add(new Request(key, key % 26));
            }
        }

        for (int i = 0; i < n - 3; i++) {
            if (string[i].equals(string[i + 1])) {
                int N = requests.size();
                for (int j = 0; j < N; j++) {
                    Request req = requests.get(j);

                    if (req.B == alphabet.get(string[i])) {
                        requests.remove(j);
                        j--;
                        N--;
                        works.add(req.A);
                    }
                }
            }

            if (!string[i].equals(string[i + 2])) {
                int N = requests.size();
                for (int j = 0; j < N; j++) {
                    Request req = requests.get(j);

                    if (req.A == encode(string[i], string[i + 2])) {
                        requests.remove(j);
                        j--;
                        N--;
                        works.add(req.A);
                    }
                }
            }
        }

        if (string[n - 2] == string[n - 1]) {
            int N = requests.size();
            for (int j = 0; j < N; j++) {
                Request req = requests.get(j);

                if (req.B == alphabet.get(string[n - 2])) {
                    requests.remove(j);
                    j--;
                    N--;
                    works.add(req.A);
                }
            }
        }

        out.println(works.size());
        char[] reverseAlphabet = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'y', 'x', 'z'};
        for (int val : works) {
            int[] pos = decode(val);

            out.println(reverseAlphabet[pos[0]] + reverseAlphabet[pos[1]] + reverseAlphabet[pos[1]]);
        }
        in.close();
        out.close();
    }

    public static int encode(String a, String b) {
        return (26 * (alphabet.get(a) - 1)) + (alphabet.get(b) - 1);
    }

    public static int[] decode(int val) {
        return new int[] {val / 26, val % 26};
    }

    public static class Request {
        int A, B;
        public Request(int A, int B) {
            this.A = A;
            this.B = B;
        }
    }
}
