package scratch;

public interface ScheduleFactory
{
    public Schedule buildFor(Participant[] participants, SchedulingConfiguration configuration);
}
