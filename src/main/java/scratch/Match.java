package scratch;

import java.util.Set;

public interface Match extends Participant
{
    Set<Participant> getParticipants();
    Match addParticipant(Participant participant);
}
