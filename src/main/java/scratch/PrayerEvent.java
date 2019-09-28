package scratch;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PrayerEvent implements Event {

    Series series = null;
    Timeframe timeFrame = null;
    private List<Participant> participants;



    @Override
    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    @Override
    public Timeframe getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(Timeframe timeFrame) {
        this.timeFrame = timeFrame;
    }

    @Override
    public List<Participant> getParticipants()
    {
        if (this.participants == null) {
            this.participants = new ArrayList<>();
        }
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public Event addParticipant(Participant participant)
    {
        this.getParticipants().add(participant);
        return this;
    }


}
