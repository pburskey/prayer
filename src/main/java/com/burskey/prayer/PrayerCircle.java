package com.burskey.prayer;

import com.burskey.prayer.participant.Participant;
import com.burskey.prayer.participant.PrayerParticipant;
import com.burskey.prayer.print.ScheduleAsCSVPrinter;
import com.burskey.prayer.schedule.ConfigurableItem;
import com.burskey.prayer.schedule.PrayerScheduleFactory;
import com.burskey.prayer.schedule.Schedule;
import com.burskey.prayer.utility.CharacterHelper;
import com.burskey.prayer.validation.EvenNumberOfParticipants;

import java.util.*;
import java.util.function.Predicate;

public class PrayerCircle {

    private List<Participant> participants = null;
    private Schedule schedule = null;


    public PrayerCircle() {
    }

    public static void main(String[] args) throws Exception {



        Predicate<String> aPredicate = s -> s.startsWith("a");
        System.out.println(aPredicate.test("apple"));
        System.out.println(aPredicate.test("orange"));


        PrayerCircle prayerCircle = new PrayerCircle();
        int participantsSize = 8;

        for (int i = 0; i < (participantsSize); i++) {
            PrayerParticipant participant = new PrayerParticipant();
            participant.setFirst(CharacterHelper.lowerCaseAlphabet[i] + "");
            participant.setLast(CharacterHelper.lowerCaseAlphabet[i] + "");
            prayerCircle.getParticipants().add(participant);
        }

        EvenNumberOfParticipants validation = new EvenNumberOfParticipants();
        validation.validate(prayerCircle.getParticipants());


        Schedule schedule = prayerCircle.getSchedule(new ConfigurableItem<Integer>(PrayerScheduleFactory.TEAM_SIZE, 2)
                , new ConfigurableItem<Boolean>(PrayerScheduleFactory.HAS_LEAD, true)
                , new ConfigurableItem<Integer>(PrayerScheduleFactory.NUMBER_SERIES, 10)
                , new ConfigurableItem<Integer>(PrayerScheduleFactory.SERIES_LENGTH_DAYS, 4));

        ScheduleAsCSVPrinter print = new ScheduleAsCSVPrinter();
        print.print(prayerCircle);


        System.exit(1);


    }

    public List<Participant> getParticipants() {
        if (this.participants == null) {
            this.participants = new ArrayList();
        }
        return participants;
    }

    public Schedule getSchedule() {
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


    public Schedule getSchedule(ConfigurableItem... configurations) {
        if (this.schedule == null) {
            PrayerScheduleFactory factory = new PrayerScheduleFactory();

            this.schedule = factory.buildFor(this.getParticipants(), configurations
            );

        }
        return schedule;
    }


}
