package scratch;

import java.util.List;

public interface Event
{
    public Series getSeries();
    public Timeframe getTimeFrame();
    public List<Participant> getParticipants();
}
