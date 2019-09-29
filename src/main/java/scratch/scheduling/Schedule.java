package scratch.scheduling;

import scratch.Participant;
import scratch.Series;

import java.util.List;

public interface Schedule
{
    public Series series();
    public List<Participant> participants();
    public SchedulingConfiguration configuration();
    public ScheduleFactory factory();


}
