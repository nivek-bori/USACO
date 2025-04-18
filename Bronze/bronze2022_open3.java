import java.io.*;
import java.util.*;

public class bronze2022_open3 {
   private static int[] metals;

   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      // BufferedReader in = new BufferedReader(
      // new
      // FileReader("/Users/kevinboriboonsomsin/Documents/Documents/Temp/system.in"));

      int n = Integer.parseInt(in.readLine());
      String[] metalsStr = in.readLine().split(" ");
      metals = new int[n];

      for (int i = 0; i < n; i++) {
         metals[i] = Integer.parseInt(metalsStr[i]);
      }

      int k = Integer.parseInt(in.readLine());
      HashMap<Integer, ArrayList<Integer>> metalToRecepie = new HashMap<>();

      for (int i = 0; i < k; i++) {
         String[] recepieStr = in.readLine().split(" ");
         int metal = Integer.parseInt(recepieStr[0]) - 1;
         int m = Integer.parseInt(recepieStr[1]);

         int[] counts = new int[metal];
         for (int j = 2; j < m + 2; j++) {
            counts[Integer.parseInt(recepieStr[j]) - 1]++;
         }

         ArrayList<Integer> recepie = new ArrayList<>();
         for (int count : counts) {
            recepie.add(count);
         }

         metalToRecepie.put(metal, recepie);
      }

      while (request(n - 1, metalToRecepie)) {
      }

      out.println(metals[n - 1]);

      in.close();
      out.close();
   }

   private static boolean request(int metal, HashMap<Integer, ArrayList<Integer>> metalToRecepie) {
      // for (int value : metals) {
      // System.out.print(value + " ");
      // }
      // System.out.println();
      // System.out.println("Request " + metal);
      if (!metalToRecepie.containsKey(metal)) {
         // System.out.println("No Recepie " + metal);
         return false;
      }

      ArrayList<Integer> recepie = metalToRecepie.get(metal);
      int n = recepie.size();

      for (int metalI = 0; metalI < n; metalI++) {
         while (metals[metalI] < recepie.get(metalI)) {
            if (metalI == 0 || !request(metalI, metalToRecepie)) {
               // System.out.println("Request Failed " + metal);
               return false;
            }
            exchange(metalI, metalToRecepie);
         }
      }

      exchange(metal, metalToRecepie);

      // System.out.println("Request Good " + metal);
      // for (int value : metals) {
      // System.out.print(value + " ");
      // }
      // System.out.println();
      return true;
   }

   private static void exchange(int metal, HashMap<Integer, ArrayList<Integer>> metalToRecepie) {
      ArrayList<Integer> recepie = metalToRecepie.get(metal);
      int n = recepie.size();

      metals[metal] += 1;
      for (int metalI = 0; metalI < n; metalI++) {
         metals[metalI] -= recepie.get(metalI);
      }
   }
}
