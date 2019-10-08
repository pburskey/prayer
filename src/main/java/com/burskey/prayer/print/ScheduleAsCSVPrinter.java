package com.burskey.prayer.print;

import com.burskey.prayer.PrayerCircle;
import com.burskey.prayer.event.Event;
import com.burskey.prayer.participant.Participant;
import com.burskey.prayer.participant.PrayerParticipant;
import com.burskey.prayer.participant.Team;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

public class ScheduleAsCSVPrinter
{
    public void print(PrayerCircle prayerCircle)
    {
        String eventS = "Event";
        String teamS = "Team";
        String participantAS = "Participant A";
        String participantBS = "Participant B";
        String leadS = "Lead";
        String[] strings = new String [] { eventS, teamS, participantAS, participantBS, leadS};


        String pattern = "{0},{1},{2},{3},{4}";
        MessageFormat format = new MessageFormat(pattern);
        System.out.println(format.format(strings));


        int i = 1;
        for(Event anEvent : prayerCircle.getSchedule().series().getEvents())
        {
            eventS = i + "";
            for(Participant participant: anEvent.getParticipants())
            {
                Team team = (Team) participant;
                List<Participant> aListOfParticipants = Participant.sortByIdentifier(team.getParticipantsAsList());
                Iterator<Participant> iterator = aListOfParticipants.iterator();
                PrayerParticipant first = (PrayerParticipant) iterator.next();
                PrayerParticipant second = (PrayerParticipant) iterator.next();
                PrayerParticipant lead = (PrayerParticipant) team.getLead();


                teamS = team.identifier();
                participantAS = first.identifier();
                participantBS = second.identifier();
                leadS = lead.identifier();
                strings = new String [] { eventS, teamS, participantAS, participantBS, leadS};
                System.out.println(format.format(strings));
            }
            i++;
        }
    }
}
