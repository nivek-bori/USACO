// Unable to submit -> untested

import java.io.*;
import java.util.*;

public class Main {
   public static void main(String[] args) throws IOException {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);

      StringTokenizer st = new StringTokenizer(in.readLine());
      int antiNum = Integer.parseInt(st.nextToken());
      int proNum = Integer.parseInt(st.nextToken());

      // Remove all
      // attack d w/ cards.higher(defense)
      // final damage = remaining attack - remaining d

      // Dont remove all
      // attack a.smallest() w/ cards.largest()

      PriorityQueue<Integer> attackCards = new PriorityQueue<>();
      LinkedList<Integer> defenseCards = new LinkedList<>();

      TreeSet<Integer> cards = new TreeSet<>();

      for (int i = 0; i < antiNum; i++) {
         String[] strCard = in.readLine().split(" ");
         if (strCard[0].charAt(0) == 'A') {
            attackCards.add(Integer.parseInt(strCard[1]));
         } else {
            defenseCards.add(Integer.parseInt(strCard[1]));
         }
      }

      for (int i = 0; i < proNum; i++) {
         cards.add(Integer.parseInt(in.readLine));
      }

      int maxDamage = Math.max(removeAll(attack, defenseCards, cards), dontRemoveAll(attackCards, defenseCards, cards));

      out.println(maxDamage);
      in.close();
      out.close();
   }

   public static int removeAll(PriorityQueue<Integer> attackCards, LinkedList<Integer> defenseCards,
         TreeSet<Integer> cards) {
      for (int defense : defenseCards) {
         Integer ret = cards.higher(defense);

         if (ret == null) {
            return 0;
         }

         cards.remove(ret);
      }

      int remainingAttack = 0;
      for (int strength : cards) {
         remainingAttack += strength;
      }

      int remainingDefense = 0;
      while (!attackCards.isEmpty()) {
         remainingDefense += attack.poll();
      }

      return remainingAttack - remainingDefense;
   }

   public static int dontRemoveAll(PriorityQueue<Integer> attackCards, LinkedList<Integer> defenseCards,
         TreeSet<Integer> cards) {
      int damage = 0;

      int n = cards.size();
      for (int i = n - 1; i >= 0 && !attackCards.isEmpty(); i--) {
         damage += cards.pollLast() - attackCards.poll();
      }

      return damage;
   }
}
