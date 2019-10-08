package com.burskey.prayer.print;

import com.burskey.prayer.PrayerCircle;
import com.burskey.prayer.event.Event;
import com.burskey.prayer.participant.Participant;
import com.burskey.prayer.participant.PrayerParticipant;
import com.burskey.prayer.participant.Team;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

public class SimplePrint
{
    public void print(PrayerCircle prayerCircle)
    {
        int i = 1;
        for(Event event : prayerCircle.getSchedule().series().getEvents())
        {
            System.out.println("Event " + i + " Start");
            for(Participant participant: event.getParticipants())
            {
                Team team = (Team) participant;
                List<Participant> aListOfParticipants = Participant.sortByIdentifier(team.getParticipantsAsList());
                Iterator<Participant> iterator = aListOfParticipants.iterator();
                PrayerParticipant first = (PrayerParticipant) iterator.next();
                PrayerParticipant second = (PrayerParticipant) iterator.next();
                PrayerParticipant lead = (PrayerParticipant) team.getLead();
                String pattern = "Team [ {0} {1} ] Lead: {2}";
                MessageFormat format = new MessageFormat(pattern);

                String[] strings = new String[]
                        {
                                first.getFirst()
                                , second.getFirst()
                                , lead.getFirst()
                        };
                System.out.println(format.format(strings));
            }
            System.out.println("");
            System.out.println("");
            i++;
        }
    }
}
