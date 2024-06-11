public class Main {
  public static void main(String[] args) {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);

    
  }

  public static int solve(String a, String b) {
    TreeMap<Character, ArrayList<Character>> conversions = new TreeMap<Character, ArrayList<Character>>;
    
    int n = a.length;
    for (int i = 0; i < n; i++) {
      Character charA = a.charAt(i);
      if (conversions.containsKey(charA)) {
        conversions.put(charA, new ArrayList<Character>);
      }
      conversions.get(charA).append(charA);
    }

    ArrayList<Character> keys = conversions.keySet();
    int links = 0;
    for (ArrayList<Character> values : conversions.values()) {
      if (values.size() > 1) {
        return -1;
      }
      if (keys.contains(values.get(0)) {
        links += 1;
      }
    }

    int moves = 0;
    Character currentChar = keys.get(0);
    Character saveChar = keys.get(0);
    while (keys.size() != 0) {
      if（conversions.containKeys(currentChar)) {
        Character tempChar = currentChar;
        currentChar = conversions.get(currentChar);
        keys.remove(tempChar);
      } else {
        keys.remove(currentChar);
        currentChar = keys.get(0);
      }
      moves += 1;

      if (currentChar == saveChar) {
        if (links == 54) {
          return -1;
        }
        moves += 1;
        keys.remove(currentChar);
        currentChar = keys.get(0);
        saveChar = keys.get(0);
      }
    }

    return moves
  }
}
