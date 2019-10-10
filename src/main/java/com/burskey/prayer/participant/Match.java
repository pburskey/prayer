package com.burskey.prayer.participant;

import java.util.ArrayList;
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




    public default List<Participant> getParticipantsAsList()
    {
        List<Participant> list = new ArrayList<>();
        if (this.hasParticipants())
        {
            list.addAll(this.getParticipants());
        }

        return list;
    }

    public default boolean hasParticipants()
    {
        return !this.getParticipants().isEmpty();
    }




    @Override
    default boolean isMatch() {
        return true;
    }


    public static List<Match> filterMatchesToOnlyThoseContaining(List<Match> aList, Participant participant)
    {
        List<Match> filteredList = new ArrayList<Match>();
        for(Match aMatch: aList)
        {
            if (aMatch != null && Participant.doesListContain(aMatch.getParticipantsAsList(), participant) )
            {
                filteredList.add(aMatch);
            }
        }
        return filteredList;
    }


    public static Match getMatchContaining(List<Match> listOfMatches, List<Participant> listOfParticipants)
    {
        Match foundMatch = null;
        for(Match match : listOfMatches)
        {
            if (containsParticipantsFromList(match, listOfParticipants))
            {
                foundMatch = match;
                break;
            }
        }
        return foundMatch;
    }


}
