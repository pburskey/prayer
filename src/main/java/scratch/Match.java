package scratch;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public interface Match extends Participant
{
    Set<Participant> getParticipants();
    Match addParticipant(Participant participant);

    boolean hasParticipant(Participant participant);
    boolean isTeam();


    public static boolean containsParticipantsFromList(Match aMatch, List<Participant> aListOfParticipants)
    {
        boolean participantsAvailable = true;
        for(Iterator iterator = aMatch.getParticipants().iterator(); participantsAvailable && iterator.hasNext();)
        {
            Participant aMatchParticipant = (Participant) iterator.next();
            participantsAvailable = Participant.doesListContain(aListOfParticipants, aMatchParticipant);
        }
        return participantsAvailable;
    }


}
