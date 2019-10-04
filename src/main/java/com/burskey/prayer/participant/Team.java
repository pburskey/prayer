package com.burskey.prayer.participant;

import java.util.*;

public class Team implements Match
{
    private Set<Participant> participants;
    private Participant lead = null;
    private int teamSize = 0;

    public Team(int teamSize) {
        this.teamSize = teamSize;
    }

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

    public List<Participant> getParticipantsAsList()
    {
        List<Participant> list = new ArrayList<>();
        list.addAll(this.getParticipants());
        return list;
    }



    @Override
    public Match addParticipant(Participant participant) {
        this.getParticipants().add(participant);
        return this;
    }


    @Override
    public String toString() {
        return "Team{" +
                "participants=" + participants +
                ", lead=" + lead +
                '}';
    }

    public int getTeamSize() {
        return teamSize;
    }

    public boolean isFull()
    {
        return this.getParticipants().size() < this.getTeamSize();
    }


    @Override
    public String identifier() {

        StringBuffer buffer = new StringBuffer();
        for(Iterator<Participant> iterator = this.getParticipants().iterator(); iterator.hasNext();)
        {
            Participant participant = (Participant) iterator.next();
            buffer.append(participant.identifier());
            if (iterator. hasNext())
            {
                buffer.append(" and ");
            }
        }
        return buffer.toString();

    }

    @Override
    public boolean isTeam() {
        return true;
    }


    @Override
    public boolean hasParticipant(Participant participantToFind) {
        boolean itDoes = false;
        for(Iterator<Participant> iterator = this.getParticipants().iterator(); !itDoes && iterator.hasNext();)
        {
            Participant participant = (Participant) iterator.next();
            itDoes = (participant.equals(participantToFind));
        }
        return itDoes;

    }
}
