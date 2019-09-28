package main.java.scratch;

import java.util.*;

public class Scratch {
    public static void main(String[] args) throws Exception {
        int maxParticipants = 8;
        List<Integer> participants = new ArrayList<Integer>();
        for (int i = 1; i < (maxParticipants + 1); i++) {
            participants.add(i);

        }


        int groupSize = 2;

        int maxGroups = participants.size() / groupSize;


        List<List> weeks = new ArrayList<>();


        for (int i = 0; i < 8; i++) {


            List<Integer> randomList = new ArrayList();
            randomList.addAll(participants);
            List<Set> matches = new ArrayList<>();
            Random random = new Random();
            while (!randomList.isEmpty()) {

                Set<Integer> set = new HashSet<Integer>();
                int choice = random.nextInt(randomList.size());
                set.add(randomList.remove(choice));

                choice = random.nextInt(randomList.size());
                set.add(randomList.remove(choice));


                matches.add(set);
            }

            System.out.println("Week: " + i);
            for (Set aMatch : matches) {
                System.out.println(aMatch);
            }
        }

        System.exit(1);


    }


}
