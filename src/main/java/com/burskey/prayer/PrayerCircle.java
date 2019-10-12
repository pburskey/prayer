package com.burskey.prayer;

import com.burskey.prayer.participant.Participant;
import com.burskey.prayer.participant.PrayerParticipant;
import com.burskey.prayer.print.ScheduleAsCSVPrinter;
import com.burskey.prayer.schedule.*;
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


        prayerCircle.getConfiguration().merge(new ConfigurableItem<Integer>(TEAM_SIZE, 2));
        prayerCircle.getConfiguration().merge(new ConfigurableItem<Boolean>(HAS_LEAD, true));
        prayerCircle.getConfiguration().merge(new ConfigurableItem<Integer>(EVENT_LENGTH_DAYS, 4));
        prayerCircle.getConfiguration().merge(new ConfigurableItem<Integer>(SCHEDULE_LENGTH_DAYS, 40));





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
            PrayerScheduleFactory factory = new PrayerScheduleFactory();

            int retry = 1;
            while (this.schedule == null && retry < 5) {
                try {
                    this.schedule = factory.buildFor(this.getParticipants(), this.getConfiguration());
                    this.validate();

                }
                catch (Exception e) {
                    System.out.println("Schedule is invalid. Try #: " + retry);
                    this.schedule = null;
                    retry++;

                }
            }
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


    public final SchedulingConfiguration getConfiguration()
    {
        if (this.configuration == null) {
            this.configuration = this.defaultConfiguration();
        }
        return configuration;
    }

    public void configure(SchedulingConfiguration configuration) {
        if (configuration != null)
        {
            for(Iterator<Configurable> iterator = configuration.iterator(); iterator.hasNext();)
            {
                Configurable newConfiguration = iterator.next();
                this.getConfiguration().merge(newConfiguration);
            }
        }
    }
}
