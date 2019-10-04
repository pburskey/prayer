package com.burskey.prayer.event;

import com.burskey.prayer.participant.Participant;
import com.burskey.prayer.Timeframe;

import java.util.List;

public interface Event
{
    public Timeframe getTimeFrame();
    public List<Participant> getParticipants();
}
