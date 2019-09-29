package scratch.scheduling;

import scratch.Participant;

import java.util.List;

public interface ScheduleFactory
{

    public Schedule buildFor(List<Participant> participants, ConfigurableItem ... items);
}
