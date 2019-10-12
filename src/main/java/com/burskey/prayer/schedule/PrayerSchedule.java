package com.burskey.prayer.schedule;

import com.burskey.prayer.participant.Participant;
import com.burskey.prayer.event.PrayerSeries;
import com.burskey.prayer.event.Series;

import java.util.ArrayList;
import java.util.List;

public class PrayerSchedule implements Schedule {


    private List<Participant> participants = null;
    private PrayerSeries series = null;
    private SchedulingConfiguration configuration = null;
    private ScheduleFactory factory = null;


    public PrayerSchedule(List<Participant> participants, ScheduleFactory factory, SchedulingConfiguration configuration) {
        this.participants = participants;
        this.factory = factory;
        this.configuration = configuration;
    }

    @Override
    public Series series() {
        if (this.series == null) {
            this.series = new PrayerSeries();
        }
        return this.series;
    }

    @Override
    public List<Participant> participants() {
        if (this.participants == null) {
            this.participants = new ArrayList<>();
        }
        return this.participants;
    }

    @Override
    public SchedulingConfiguration configuration() {
        if (this.configuration == null) {
            this.configuration = new SchedulingConfiguration();
        }
        return this.configuration;
    }

    @Override
    public ScheduleFactory factory() {

        if (this.factory == null) {
            this.factory = new PrayerScheduleFactory();
        }
        return factory;
    }
}
