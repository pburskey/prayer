package com.burskey.prayer.schedule;

import com.burskey.prayer.participant.Participant;

import java.util.List;

public interface ScheduleFactory
{

    public Schedule buildFor(List<Participant> participants, SchedulingConfiguration configuration);
}
