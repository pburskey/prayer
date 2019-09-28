package scratch;

import java.util.Date;

public interface Series
{
    public Timeframe[] getTimeFrames();
    public Date getStart();
    public Date getStop();
    public Event[] getEvents();
    public Series addEvent(Event event);
}
