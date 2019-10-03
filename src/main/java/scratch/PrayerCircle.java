package scratch;

import scratch.scheduling.ConfigurableItem;
import scratch.scheduling.PrayerScheduleFactory;
import scratch.scheduling.Schedule;

import java.text.MessageFormat;
import java.util.*;

public class PrayerCircle {

    private List<Participant> participants = null;
    private Schedule schedule = null;




    public PrayerCircle() {
    }

    public static void main(String[] args) throws Exception
    {

        PrayerCircle prayerCircle = new PrayerCircle();
        int participantsSize = 4;

        for (int i = 0; i < (participantsSize); i++)
        {
            PrayerParticipant participant = new PrayerParticipant();
            participant.setFirst(CharacterHelper.lowerCaseAlphabet[i] + "");
            participant.setLast(CharacterHelper.lowerCaseAlphabet[i] + "");
            prayerCircle.getParticipants().add(participant);
        }


        Schedule schedule = prayerCircle.getSchedule();


//        for(Participant participant: schedule.participants())
//        {
//            System.out.println(participant.toString());
//        }

        Series series = schedule.series();

        int i = 1;
        for(Event event : series.getEvents())
        {
            System.out.println("Event " + i + " Start");
            for(Participant participant: event.getParticipants())
            {
                Team team = (Team) participant;
                List<Participant> aListOfParticipants = Participant.sortByIdentifier(team.getParticipantsAsList());
                Iterator<Participant> iterator = aListOfParticipants.iterator();
                PrayerParticipant first = (PrayerParticipant) iterator.next();
                PrayerParticipant second = (PrayerParticipant) iterator.next();
                PrayerParticipant lead = (PrayerParticipant) team.getLead();
                String pattern = "Team [ {0} {1} ] Lead: {2}";
                MessageFormat format = new MessageFormat(pattern);

                String[] strings = new String[]
                        {
                                first.getFirst()
                                , second.getFirst()
                                , lead.getFirst()
                        };
                System.out.println(format.format(strings));
            }
            System.out.println("");
            System.out.println("");
            i++;
        }



        System.exit(1);


    }

    public List<Participant> getParticipants()
    {
        if (this.participants == null)
        {
            this.participants = new ArrayList();
        }
        return participants;
    }

    public Schedule getSchedule()
    {
        if (this.schedule == null) {
            PrayerScheduleFactory factory = new PrayerScheduleFactory();

            this.schedule = factory.buildFor(this.getParticipants()
            , new ConfigurableItem<Integer>("TEAM_SIZE", 2)
            , new ConfigurableItem<Boolean>("HAS_LEAD", true)
            , new ConfigurableItem<Integer>("SCHEDULE_DURATION_SERIES", 10)
            , new ConfigurableItem<Integer>("SCHEDULE_DURATION_DAYS", 4)
            );

            }
        return schedule;
    }
}
