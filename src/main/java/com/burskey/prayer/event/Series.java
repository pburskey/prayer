package com.burskey.prayer.event;

import com.burskey.prayer.Timeframe;

import java.util.Date;

public interface Series
{
    public Timeframe[] getTimeFrames();
    public Date getStart();
    public Date getStop();
    public Event[] getEvents();
    public Series addEvent(Event event);
}
