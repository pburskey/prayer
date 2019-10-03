package scratch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrayerSeries implements Series {
    List<Timeframe> timeframes = new ArrayList<>();
    List<Event> events = new ArrayList<>();

    @Override
    public Timeframe[] getTimeFrames() {
        Timeframe[] anObject = null;
        if (this.timeframes != null) {
            anObject = this.timeframes.toArray(new Timeframe[this.timeframes.size()]);
        }
        return anObject;
    }

    @Override
    public Date getStart()
    {
        Date aDate = null;
        return aDate;
    }

    @Override
    public Date getStop() {
        return null;
    }

    @Override
    public Event[] getEvents() {
        Event[] anObject = null;
        if (this.events != null) {
            anObject = this.events.toArray(new Event[this.events.size()]);
        }
        return anObject;
    }

    @Override
    public Series addEvent(Event event)
    {
        if (this.events == null) {
            this.events = new ArrayList<>();
        }
        this.events.add(event);
        return this;
    }



}
