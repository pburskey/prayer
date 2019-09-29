package scratch;

import java.util.ArrayList;
import java.util.List;

public class PrayerEvent implements Event {

    Timeframe timeFrame = null;
    private List<Participant> participants;



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
