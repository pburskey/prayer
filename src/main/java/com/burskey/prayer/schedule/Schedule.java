package com.burskey.prayer.schedule;

import com.burskey.prayer.participant.Participant;
import com.burskey.prayer.event.Series;

import java.util.List;

public interface Schedule
{
    public Series series();
    public List<Participant> participants();
    public SchedulingConfiguration configuration();
    public ScheduleFactory factory();


}
