package com.burskey.prayer;

import com.burskey.prayer.participant.Participant;
import com.burskey.prayer.participant.PrayerParticipant;
import com.burskey.prayer.print.ScheduleAsCSVPrinter;
import com.burskey.prayer.schedule.ConfigurableItem;
import com.burskey.prayer.schedule.PrayerScheduleFactory;
import com.burskey.prayer.schedule.Schedule;
import com.burskey.prayer.schedule.SchedulingConfiguration;
import com.burskey.prayer.utility.CharacterHelper;
import com.burskey.prayer.validation.EvenNumberOfParticipants;
import com.burskey.prayer.validation.PrayerCircleValidation;

import java.util.*;
import java.util.function.Predicate;

public class PrayerCircle {

    private List<Participant> participants = null;
    private Schedule schedule = null;
    private SchedulingConfiguration configuration = null;

    public static String TEAM_SIZE = "TEAM_SIZE";
    public static String HAS_LEAD = "HAS_LEAD";
    public static String EVENT_LENGTH_DAYS = "EVENT_LENGTH_DAYS";
    public static String SCHEDULE_LENGTH_DAYS = "SCHEDULE_LENGTH_DAYS";


    public PrayerCircle() {
    }

    public static void main(String[] args) throws Exception {



//        Predicate<String> aPredicate = s -> s.startsWith("a");
//        System.out.println(aPredicate.test("apple"));
//        System.out.println(aPredicate.test("orange"));


        PrayerCircle prayerCircle = new PrayerCircle();
        int participantsSize = 8;

        for (int i = 0; i < (participantsSize); i++) {
            PrayerParticipant participant = new PrayerParticipant();
            participant.setFirst(CharacterHelper.lowerCaseAlphabet[i] + "");
            participant.setLast(CharacterHelper.lowerCaseAlphabet[i] + "");
            prayerCircle.addParticipant(participant);
        }




        Schedule schedule = prayerCircle.getSchedule(new ConfigurableItem<Integer>(TEAM_SIZE, 2)
                , new ConfigurableItem<Boolean>(HAS_LEAD, true)
                , new ConfigurableItem<Integer>(EVENT_LENGTH_DAYS, 4)
                , new ConfigurableItem<Integer>(SCHEDULE_LENGTH_DAYS, 40));




        ScheduleAsCSVPrinter print = new ScheduleAsCSVPrinter();
        print.print(prayerCircle);


        System.exit(1);


    }

    public PrayerCircle addParticipant(Participant aParticipant)
    {
        this.getParticipants().add(aParticipant);
        return this;
    }


    public final List<Participant> getParticipants() {
        if (this.participants == null) {
            this.participants = new ArrayList();
        }
        return participants;
    }

    public Schedule getSchedule() {
        if (this.schedule == null) {
            this.getSchedule(
                    new ConfigurableItem<Integer>(TEAM_SIZE, 2)
                    , new ConfigurableItem<Boolean>(HAS_LEAD, true)
                    , new ConfigurableItem<Integer>(EVENT_LENGTH_DAYS, 4)
                    , new ConfigurableItem<Integer>(SCHEDULE_LENGTH_DAYS, 40)
            );
        }
        return schedule;
    }


    public Schedule getSchedule(ConfigurableItem... configurations) {
        if (this.schedule == null) {
            PrayerScheduleFactory factory = new PrayerScheduleFactory();

            this.schedule = factory.buildFor(this.getParticipants(), configurations
            );
            this.validate();
        }
        return schedule;
    }


    public void validate()
    {
        (new PrayerCircleValidation()).validate(this);
    }


    private SchedulingConfiguration defaultConfiguration()
    {
        SchedulingConfiguration defaultConfiguration = new SchedulingConfiguration();


        ConfigurableItem[] items = new ConfigurableItem[] {
                new ConfigurableItem<Integer>(TEAM_SIZE, 2)
                , new ConfigurableItem<Boolean>(HAS_LEAD, true)
                , new ConfigurableItem<Integer>(EVENT_LENGTH_DAYS, 4)
                , new ConfigurableItem<Integer>(SCHEDULE_LENGTH_DAYS, 40)};


        for (ConfigurableItem item : items) {
            defaultConfiguration.configuration().put(item.name(), item);
        }

        return defaultConfiguration;

    }


}
