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
        int participantsSize = 8;

        for (int i = 0; i < (participantsSize); i++)
        {
            PrayerParticipant participant = new PrayerParticipant();
            participant.setFirst(CharacterHelper.lowerCaseAlphabet[i] + "");
            participant.setLast(CharacterHelper.lowerCaseAlphabet[i] + "");
            prayerCircle.getParticipants().add(participant);
        }


        Schedule schedule = prayerCircle.getSchedule();

        ScheduleAsCSVPrinter print = new ScheduleAsCSVPrinter();
        print.print(prayerCircle);




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
