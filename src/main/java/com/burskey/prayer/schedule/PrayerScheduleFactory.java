package com.burskey.prayer.schedule;

import com.burskey.prayer.event.PrayerEvent;
import com.burskey.prayer.event.Series;
import com.burskey.prayer.participant.Match;
import com.burskey.prayer.participant.Participant;
import com.burskey.prayer.participant.ParticipantMatchesBag;
import com.burskey.prayer.participant.Team;

import java.util.*;

public class PrayerScheduleFactory implements ScheduleFactory {

    public ConfigurableItem<Integer> TEAM_SIZE = new ConfigurableItem<Integer>("TEAM_SIZE", 2);
    public ConfigurableItem<Boolean> HAS_LEAD = new ConfigurableItem<Boolean>("HAS_LEAD", true);
    public ConfigurableItem<Integer> NUMBER_SERIES = new ConfigurableItem<Integer>("SCHEDULE_DURATION_SERIES", 10);
    public ConfigurableItem<Integer> SERIES_LENGTH_DAYS = new ConfigurableItem<Integer>("SCHEDULE_DURATION_DAYS", 4);

    @Override
    public Schedule buildFor(List<Participant> participants, ConfigurableItem ... items) {
        Schedule schedule = new PrayerSchedule(participants, this);


        if (items != null)
        {
            Map configurationMap = (Map) schedule.configuration().configuration();
            for(Configurable item : items)
            {
                configurationMap.put(item.name(), item);
            }
        }

        ConfigurableItem<Integer> groupSize = (ConfigurableItem<Integer>) schedule.configuration().configuration().get(TEAM_SIZE.name());
        ConfigurableItem<Boolean> teamsHaveLead = (ConfigurableItem<Boolean>) schedule.configuration().configuration().get(HAS_LEAD.name());
        ConfigurableItem<Integer> numberOfSeriesItems = (ConfigurableItem<Integer>) schedule.configuration().configuration().get(NUMBER_SERIES.name());
        ConfigurableItem<Integer> seriesLengthInDays = (ConfigurableItem<Integer>) schedule.configuration().configuration().get(SERIES_LENGTH_DAYS.name());


        Series series = schedule.series();


        ParticipantMatchesBag matchesBag = new ParticipantMatchesBag();
        matchesBag.createCombinations(participants.toArray(new Participant[participants.size()]), groupSize.value());

        List<Match> listOfAvailableMatches = null;


        for (int i = 0; i < numberOfSeriesItems.value(); i++)
        {

            PrayerEvent prayerEvent = new PrayerEvent();
            series.addEvent(prayerEvent);

            List<Participant> seriesParticipants = this.mixUpList(new ArrayList<Participant>(participants));


            while(!seriesParticipants.isEmpty())
            {
                Participant firstParticipant = seriesParticipants.iterator().next();

                Match seriesMatch = null;
                /*
                reach into the list of available matches and find a match containing this particpant...
                 */

                if (listOfAvailableMatches == null || listOfAvailableMatches.isEmpty())
                {
                    listOfAvailableMatches = matchesBag.combinationsAsList();
                }

                while(seriesMatch == null)
                {
                    for(Match aPotentialMatch : listOfAvailableMatches)
                    {
                        if (aPotentialMatch.hasParticipant(firstParticipant))
                        {
                            /*
                            cool lets roll with this if the matches other participants are available for us to choose yet...
                             */
                            boolean participantsAvailable = Match.containsParticipantsFromList(aPotentialMatch, seriesParticipants);

                            if (participantsAvailable)
                            {
                                seriesMatch = aPotentialMatch;
                                for(Iterator iterator = aPotentialMatch.getParticipants().iterator(); iterator.hasNext();)
                                {
                                    Participant aMatchParticipant = (Participant) iterator.next();
                                    seriesParticipants.remove(aMatchParticipant);
                                }
                                break;
                            }
                        }
                    }

                    if (seriesMatch == null)
                    {
                        System.out.println("not able to find a match for participant: " + firstParticipant);
                        listOfAvailableMatches = matchesBag.combinationsAsList();
                    }



                }



                if (seriesMatch != null)
                {
                    listOfAvailableMatches.remove(seriesMatch);

                    if (teamsHaveLead.value() && seriesMatch.isTeam())
                    {
                        Team team = (Team) seriesMatch;
                        Participant teamLead = team.getParticipants().iterator().next();
                        team.setLead(teamLead);
                    }

                    prayerEvent.addParticipant(seriesMatch);
                }
                else
                {
                    System.out.println("FATAL......not able to find a match for participant: " + firstParticipant);
                }


            }







        }


        return schedule;
    }

    private List mixUpList(List originalList)
    {
        List newList = new ArrayList<>();

        Random random = new Random();
        while (!originalList.isEmpty())
        {

            int choice = random.nextInt(originalList.size());
            Object anObject = originalList.remove(choice);
            newList.add(anObject);

        }
        return newList;

    }


}
