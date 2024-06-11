import java.io.*;
import java.util.*;

public class Main {

   private static HashMap<Integer, Boolean> targetSlopesPairings = new HashMap<>();
   private static HashMap<Integer, ArrayList<ArrayList<Integer>>> slopePairings = new HashMap<>();
   private static long[][][] targets;
   private static long[] slopes;
   private static long maxValue = 0;
   private static long minDistance = Long.MAX_VALUE;

   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);

      int T = Integer.parseInt(in.readLine());

      for (int t = 0; t < T; t++) {
         StringTokenizer st = new StringTokenizer(in.readLine());
         long n = Long.parseLong(st.nextToken());
         long xA = Long.parseLong(st.nextToken());

         targetSlopesPairings = new HashMap<>();
         slopePairings = new HashMap<>();
         targets = new long[(int) n][4][2];
         slopes = new long[(int) 4 * (int) n];
         maxValue = n + 1;
         minDistance = Long.MAX_VALUE;

         ArrayList<Integer> targetIDs = new ArrayList<>();
         ArrayList<Integer> slopeIDs = new ArrayList<>();

         for (int i = 0; i < n; i++) {
            long[][] target = new long[4][2];
            st = new StringTokenizer(in.readLine());
            long yA = Long.parseLong(st.nextToken());
            long xB = Long.parseLong(st.nextToken());
            long yB = Long.parseLong(st.nextToken());

            target[0] = new long[] { xA, yA };
            target[1] = new long[] { xA, yB };
            target[2] = new long[] { xB, yA };
            target[3] = new long[] { xB, yB };
            targets[i] = target;

            targetIDs.add(i);
         }

         st = new StringTokenizer(st.nextToken());
         for (int i = 0; i < 4 * n; i++) {
            long value = Long.parseLong(st.nextToken());
            slopes[i] = value;
            maxValue = Math.max(value + 1, maxValue);

            slopeIDs.add(i);
         }

         explore(targetIDs, slopeIDs, Long.MAX_VALUE, 0);

         out.println(minDistance);
      }

      in.close();
      out.close();
   }

   private static void explore(ArrayList<Integer> targetIDs, ArrayList<Integer> slopeIDs, long minY, long maxY) {
      if (targetIDs.size() == 0) {
         minDistance = Math.min(maxY - minY, minDistance);
         return;
      }

      int N = targetIDs.size();
      for (int t = 0; t < N; t++) {
         int target = targetIDs.get(t);

         int slopeID = slopeID(slopeIDs);
         if (slopePairings.containsKey(slopeID)) {
            ArrayList<ArrayList<Integer>> pairings = new ArrayList<>();

            for (ArrayList<Integer> pairing : pairings) {
               int a = pairing.get(0);
               int b = pairing.get(1);
               int c = pairing.get(2);
               int d = pairing.get(3);

               exploreID(targetIDs, slopeIDs, target, minY, maxY, a, b, c, d);
            }
         } else {
            ArrayList<ArrayList<Integer>> pairings = new ArrayList<>();

            int n = slopeIDs.size();
            for (int a = 0; a < n - 3; a++) {
               for (int b = a + 1; b < n - 2; b++) {
                  for (int c = b + 1; c < n - 1; c++) {
                     for (int d = c + 1; d < n; d++) {
                        ArrayList<Integer> pairing = new ArrayList<>();
                        pairing.add(a);
                        pairing.add(b);
                        pairing.add(c);
                        pairing.add(d);

                        pairings.add(pairing);

                        exploreID(targetIDs, slopeIDs, target, minY, maxY, a, b, c, d);
                     }
                  }
               }
            }

            slopePairings.put(slopeID, pairings);
         }
      }
   }

   private static void exploreID(ArrayList<Integer> targetIDs, ArrayList<Integer> slopeIDs, int targetID, long minY,
         long maxY, int a, int b, int c, int d) {
      int A = slopeIDs.get(a);
      int B = slopeIDs.get(b);
      int C = slopeIDs.get(c);
      int D = slopeIDs.get(d);

      int ID = ID(targetID, A, B, C, D);

      if (targetSlopesPairings.containsKey(ID) && targetSlopesPairings.get(ID)) {
         ArrayList<Integer> newTargetIDs = new ArrayList<>(targetIDs);
         ArrayList<Integer> newSlopeIDs = new ArrayList<>(slopeIDs);

         newTargetIDs.remove(targetIDs.indexOf(targetID));
         newSlopeIDs.remove(a);
         newSlopeIDs.remove(b);
         newSlopeIDs.remove(c);
         newSlopeIDs.remove(d);

         long[] positions = getMinMax(minY, maxY, targetID, A, B, C, D);
         explore(newTargetIDs, slopeIDs, positions[0], positions[1]);
      } else {
         boolean works = check(targetID, A, B, C, D);
         targetSlopesPairings.put(ID, works);

         if (works) {
            ArrayList<Integer> newTargetIDs = new ArrayList<>(targetIDs);
            ArrayList<Integer> newSlopeIDs = new ArrayList<>(slopeIDs);

            newTargetIDs.remove(targetIDs.indexOf(targetID));
            newSlopeIDs.remove(a);
            newSlopeIDs.remove(b);
            newSlopeIDs.remove(c);
            newSlopeIDs.remove(d);

            long[] positions = getMinMax(minY, maxY, targetID, A, B, C, D);
            explore(newTargetIDs, slopeIDs, positions[0], positions[1]);
         }
      }
   }

   private static int ID(int targetID, int A, int B, int C, int D) {
      int ID = targetID;

      ID += maxValue * A;
      ID += maxValue * maxValue * B;
      ID += maxValue * maxValue * maxValue * C;
      ID += maxValue * maxValue * maxValue * maxValue * D;

      return ID;
   }

   private static int slopeID(ArrayList<Integer> slopeIDs) {
      int n = slopeIDs.size();

      int ID = 0;
      for (int i = 0; i < n; i++) {
         ID += Math.pow((double) maxValue, (double) i) * slopeIDs.get(i);
      }

      return ID;
   }

   private static boolean check(int target, int A, int B, int C, int D) {
      int pos = 0;
      int neg = 0;

      if (slopes[A] > 0) {
         pos++;
      } else {
         neg++;
      }

      if (slopes[A] > 0) {
         pos++;
      } else {
         neg++;
      }

      if (slopes[A] > 0) {
         pos++;
      } else {
         neg++;
      }

      if (slopes[A] > 0) {
         pos++;
      } else {
         neg++;
      }

      if (pos != 4 && neg != 4) {
         return true;
      }
      return false;
   }

   private static long[] getMinMax(long minY, long maxY, int targetID, int A, int B, int C, int D) {
      long[][] target = targets[targetID];

      long yA = slopes[A] * target[A][0] + target[A][1];
      long yB = slopes[B] * target[B][0] + target[B][1];
      long yC = slopes[C] * target[C][0] + target[C][1];
      long yD = slopes[D] * target[D][0] + target[D][1];

      long min = Math.min(minY, Math.min(Math.min(yA, yB), Math.min(yC, yD)));
      long max = Math.max(maxY, Math.max(Math.max(yA, yB), Math.max(yC, yD)));

      return new long[] { min, max };
   }
}
