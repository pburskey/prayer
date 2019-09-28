package scratch;

import java.util.HashSet;
import java.util.Set;

public class Team implements Match
{
    private Set<Participant> participants;
    private Participant lead = null;

    public Participant getLead() {
        return lead;
    }

    public void setLead(Participant lead) {
        this.lead = lead;
    }

    @Override
    public Set<Participant> getParticipants() {
        if (this.participants == null)
        {
            this.participants = new HashSet<>();
        }
        return this.participants;
    }

    @Override
    public Match addParticipant(Participant participant) {
        this.getParticipants().add(participant);
        return this;
    }
}
