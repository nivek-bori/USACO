public class Main {
    private static int a;
    private static int b;
    
    private static int[] amountA;
    private static int[] amountB;
    private static int[] maxT;
    
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        amountA = new int[n];
        amountB = new int[n];
        maxT = new int[n];

        for (int i = 0; i < n; i++) {
            String[] friendStr = in.readLine().split(" ");
            amountA[i] = Integer.parseInt(friendStr[0]);
            amountB[i] = Integer.parseInt(friendStr[1]);
            maxT[i] = Integer.parseInt(friendStr[2]);
        }

        int lo = 0;
        int hi = 0;
        while (lo < hi) {
            int mid = (int) (0.5 * (hi + lo + 1));

            if (tryCost(mid)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
    }

    public static boolean tryCost(int c) {
        HashSet<Integer> attempted = new HashSet<>();
        int A = a - (int) (0.5 * c);
        int B = b - (c - A);
        int n = maxT.length;

        while (!attempted.add(1000 * A + B) && A > 1 && B > 1) {
            for (int i = 0; i < n; i++) {
                if (amountA[i] * A + amountB[i] * B > maxT[i]) {
                    if (amountA[i] * A > amountB[i] * B) {
                        A--;
                        B++;
                    } else {
                        A++;
                        B--;
                    }
                    break;
                }
            }
            return true;
        }

        return false;
    }
}
