package scratch;

import java.util.*;

public class PrayerCircle {

    int maxParticipants = 8;
    int groupSize = 2;
    private List<Participant> participants = null;
    private PrayerSeries series = null;
    private Schedule schedule = null;



    public PrayerSeries getSeries() {
        return series;
    }

    public void setSeries(PrayerSeries series) {
        this.series = series;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants)
    {
        this.participants = participants;
    }

    public static void main(String[] args) throws Exception
    {

        PrayerCircle prayerCircle = new PrayerCircle();
        prayerCircle.setSeries(new PrayerSeries());


        prayerCircle.participants = new ArrayList<Participant>();
        for (int i = 0; i < (prayerCircle.getMaxParticipants()); i++)
        {
            PrayerParticipant participant = new PrayerParticipant();
            participant.setFirst(CharacterHelper.lowerCaseAlphabet[i] + "");
            participant.setLast(CharacterHelper.lowerCaseAlphabet[i] + "");
            prayerCircle.getParticipants().add(participant);

        }



        int maxGroups = prayerCircle.getParticipants().size() / prayerCircle.getGroupSize();

        for (int i = 0; i < 8; i++) {

            PrayerEvent prayerEvent = new PrayerEvent();
            prayerCircle.getSeries().addEvent(prayerEvent);
            List<Participant> randomList = new ArrayList();
            randomList.addAll(prayerCircle.getParticipants());
            List<Set> matches = new ArrayList<>();
            Random random = new Random();
            while (!randomList.isEmpty()) {

                Team team = new Team();
                int choice = random.nextInt(randomList.size());
                Participant participant = randomList.remove(choice);
                team.addParticipant(participant);
                team.setLead(participant);

                choice = random.nextInt(randomList.size());
                participant = randomList.remove(choice);
                team.addParticipant(participant);

                prayerEvent.addParticipant(team);
            }

            System.out.println("Week: " + i);
            for (Set aMatch : matches) {
                System.out.println(aMatch);
            }
        }

        System.exit(1);


    }


}
