import java.io.*;
import java.util.*;

public class Main {

    static TreeMap<String, Integer> alphabet = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        // BufferedReader in = new BufferedReader(new FileReader("system.in"));
        // PrintWriter out = new PrintWriter(new FileWriter("system.out"));

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

        TreeMap<String, Integer> moos = new TreeMap<>();
        HashMap<String, ArrayList<Integer>> indexes = new HashMap<>();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int f = Integer.parseInt(st.nextToken());

        String[] string = in.readLine().split("");
        for (int i = 0; i < n - 2; i++) {
            if (string[i + 1].equals(string[i + 2])) {
                if (f == 1) {
                    for (String key : alphabet.keySet()) {
                        String str = key + string[i + 1];
                        if (!moos.containsKey(str)) {
                            moos.put(str, 1);
                            indexes.put(str, new ArrayList<>());
                        } else {
                            moos.put(str, moos.get(str) + 1);
                        }
                        indexes.get(str).add(i);
                        indexes.get(str).add(i + 1);
                        indexes.get(str).add(i + 2);
                    }
                } else if (!string[i].equals(string[i + 1])) {
                    String str = string[i] + string[i + 1];
                    if (!moos.containsKey(str)) {
                        moos.put(str, 1);
                        indexes.put(str, new ArrayList<>());
                    } else {
                        moos.put(str, moos.get(str) + 1);
                    }
                    indexes.get(str).add(i);
                    indexes.get(str).add(i + 1);
                    indexes.get(str).add(i + 2);
                }
            }
        }

        LinkedList<String> works = new LinkedList<>();

        for (String key : moos.keySet()) {

            if (moos.get(key) >= f) {
                works.add(key + key.substring(1, 2));
            }

            if (moos.get(key) == f - 1) {
                String A = key.substring(0, 1);
                String B = key.substring(1, 2);
                
                if (string[n - 2].equals(B) && string[n - 1].equals(B)) {
                    works.add(key + B);
                    continue;
                }

                for (int i = 0; i < n - 2; i++) {
                    String x = string[i];
                    String y = string[i + 1];
                    String z = string[i + 2];
                    if (y.equals(B) && z.equals(B) && !indexes.get(key).contains(i)) {
                        works.add(key + B);
                        break;
                    }

                    if (x.equals(A) && y.equals(B) && !indexes.get(key).contains(i + 2)) {
                        works.add(key + B);
                        break;
                    }

                    if (x.equals(A) && z.equals(B) && !indexes.get(key).contains(i + 1)) {
                        works.add(key + B);
                        break;
                    }
                }
            }
        }

        out.println(works.size());
        for (String word : works) {
            out.println(word);
        }
        in.close();
        out.close();
    }

    public static int encode(String a, String b) {
        return (26 * (alphabet.get(a) - 1)) + (alphabet.get(b) - 1);
    }

    public static int[] decode(int val) {
        return new int[] { val / 26, val % 26 };
    }

    public static class Request {
        int A, B;

        public Request(int A, int B) {
            this.A = A;
            this.B = B;
        }
    }
}
