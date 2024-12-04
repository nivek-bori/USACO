public class Main {
    private static int[][] field;
    private static HashSet visited = new HashSet<>();
    
    private static LinkedList<Integer> chars = new LinkedList<>();
    private static int count;
    private static int x1, y1;
    private static int x2, y2;

    public static voud main(String[] args) {
        BufferedReader in = new BufferedReader(new FileReader("where.in"));
        PrintWriter out = new PrintWriter(new FileWriter("where.out"));

        int n = Integer.parseInt(in.readLine());

        LinkedList<String> map = new LinkedList<>();
        for (int y = 0; y < n; y++) {
            String[] row = in.readLine();
            for (int x = 0; x < n; x++) {
                String currChar = row[x];
                if (!map.contains(currChar)) {map.add(currChar);}
                field[y][x] = map.indexOf(currChar);
            }
        }

        LinkedList<Box> boxes = new LinkedList<>();
        for (int y1 = 0; y1 < n - 2; y1++) { // n - 2 bc min size is 3
            this.y1 = y1;
            for (int x1 = 0; x1 < n - 2; x1++) {
                this.x1 = x1;
                for (int y2 = y1 + 3; y2 < n; y2++) {
                    this.y2 = y2;
                    for (int x2 = x1 + 3; x2 < n; x2++) {
                      this.x2 = x2;
                      Box currBox = new Box(y1, x1, y2, x2);
                      boolean good = true;
                      for (Box box : boxes) {
                          if (box.contains(currBox)) {
                              good = false;
                              break;
                          }
                      }
                      if (good) {
                          count = 0;
                          chars = new LinkedList<>();
                          floodfill(y1, x1, field[y1][x1]);
                          if (validBox) {boxes.add(new Box(y1, x1, y2, x2));}
                      }
                    }
                }
            }
        }
    }

    public static void floodfill(int y, int x, int initVal) {
        if (!visited.add(encode(y, x)) {return;}

        if (y < y1 || y > y2 || x < x1 || x > x2) {return;}

        if (field[y][x] != initVal) {
            count++;
            chars.add(field[y][x]);
        }

        if (count <= 3) {
            for (int i = 0; i < 4; i++) {
                floodfill(y + yChng[i], x + xChng[i], field[y][x]);
            }
        }
    }

    public static void validBox() {
      return (chars.length() == 3) && (chars.get(0) == chars.get(1) || chars.get(0) == chars.get(2) || chars.get(1) == chars.get(2));
    }

    public class Box {
        public int y1, x1, y2, x2;
        public Box(int y1, int x1, int y2, int x2) {
          this.y1 = y1;
          this.x1 = x1;
          this.y2 = y2;
          this.x2 = x2;
        }

        public boolean contains(Box other) {
          return (y1 <= other.y1 && y2 >= other.y2 && x1 <= other.x1 && x2 >= other.x2);
        }
    }
}
