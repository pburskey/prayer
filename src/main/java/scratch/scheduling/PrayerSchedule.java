package scratch.scheduling;

import scratch.Participant;
import scratch.PrayerSeries;
import scratch.Series;

import java.util.ArrayList;
import java.util.List;

public class PrayerSchedule implements Schedule {


    private List<Participant> participants = null;
    private PrayerSeries series = null;
    private SchedulingConfiguration configuration = null;
    private ScheduleFactory factory = null;


    public PrayerSchedule(List<Participant> participants, ScheduleFactory factory) {
        this.participants = participants;
        this.factory = factory;
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
