import java.io.*;
import java.util.*;

import javax.imageio.plugins.tiff.FaxTIFFTagSet;

public class Main {
   public static void main(String[] args) throws IOException {
      // BufferedReader in = new BufferedReader(
      // new
      // FileReader("/Users/kevinboriboonsomsin/Documents/Documents/Temp/system.in"));
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);

      StringTokenizer st = new StringTokenizer(in.readLine());
      int cowNum = Integer.parseInt(st.nextToken());
      int farmerNum = Integer.parseInt(st.nextToken());

      ArrayList<Integer> times = new ArrayList<>();
      st = new StringTokenizer(in.readLine());
      for (int i = 0; i < cowNum; i++) {
         times.add(Integer.parseInt(st.nextToken()));
      }

      TreeSet<Integer> finishTimes = new TreeSet<>();
      TreeMap<Integer, ArrayList<Integer>> finishTimesToFarmer = new TreeMap<>();
      HashMap<Integer, ArrayList<Integer>> sharedFarmers = new HashMap<>();

      for (int currentCow = 0; currentCow < farmerNum; currentCow++) {
         int time = times.get(currentCow);

         if (!finishTimesToFarmer.containsKey(time)) {
            finishTimesToFarmer.put(time, new ArrayList<Integer>());
         }
         finishTimesToFarmer.get(time).add(currentCow);
         finishTimes.add(time);
      }

      for (int currentCow = farmerNum; currentCow < cowNum; currentCow++) {
         int finishTime = finishTimes.first();

         ArrayList<Integer> farmers = finishTimesToFarmer.get(finishTime);

         int nextTime = finishTime + times.get(currentCow);
         // System.out.println(finishTime + " " + currentCow + " " + nextTime + " " +
         // farmers.get(0)); // REMOVE
         if (!finishTimesToFarmer.containsKey(nextTime)) {
            finishTimesToFarmer.put(nextTime, new ArrayList<Integer>());
         }
         finishTimesToFarmer.get(nextTime).add(farmers.get(0));
         finishTimes.add(nextTime);

         if (farmers.size() == 1) {
            finishTimesToFarmer.remove(finishTime);
            finishTimes.remove(finishTime);
         } else {
            int n = farmers.size();
            int A = farmers.get(0);
            for (int b = 1; b < n; b++) {
               int B = farmers.get(b);
               if (!sharedFarmers.containsKey(A)) {
                  sharedFarmers.put(A, new ArrayList<Integer>());
               }
               sharedFarmers.get(A).add(B);
               if (!sharedFarmers.containsKey(B)) {
                  sharedFarmers.put(B, new ArrayList<Integer>());
               }
               sharedFarmers.get(B).add(A);
               ;
            }

            finishTimesToFarmer.get(finishTime).remove(0);
         }
      }

      // System.out.println(); // REMOVE

      int interviewTime = finishTimes.first();
      ArrayList<Integer> finalFarmers = finishTimesToFarmer.get(interviewTime);
      boolean[] possibleFarmers = new boolean[farmerNum];
      for (int finalFarmer : finalFarmers) {
         // System.out.println(finalFarmer); // REMOVE
         possibleFarmers[finalFarmer] = true;
         if (sharedFarmers.containsKey(finalFarmer)) {
            ArrayList<Integer> shared = sharedFarmers.get(finalFarmer);
            for (int farmer : shared) {
               // System.out.println(farmer); // REMOVE
               possibleFarmers[farmer] = true;
            }
         }
      }

      out.println(interviewTime);
      for (boolean possible : possibleFarmers) {
         out.print((possible) ? 1 : 0);
      }
      out.println();

      in.close();
      out.close();
   }
}
