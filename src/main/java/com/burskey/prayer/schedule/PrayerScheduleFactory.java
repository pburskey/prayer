package com.burskey.prayer.schedule;

import com.burskey.prayer.event.PrayerEvent;
import com.burskey.prayer.event.Series;
import com.burskey.prayer.participant.Match;
import com.burskey.prayer.participant.Participant;
import com.burskey.prayer.participant.ParticipantMatchesBag;
import com.burskey.prayer.participant.Team;

import java.util.*;

public class PrayerScheduleFactory implements ScheduleFactory {




    public static String TEAM_SIZE = "TEAM_SIZE";
    public static String HAS_LEAD = "HAS_LEAD";
    public static String NUMBER_SERIES = "NUMBER_SERIES";
    public static String SERIES_LENGTH_DAYS = "SERIES_LENGTH_DAYS";


    private SchedulingConfiguration defaultConfiguration()
    {
        SchedulingConfiguration defaultConfiguration = new SchedulingConfiguration();


        ConfigurableItem[] items = new ConfigurableItem[] {
                new ConfigurableItem<Integer>(TEAM_SIZE, 2)
                , new ConfigurableItem<Boolean>(HAS_LEAD, true)
                , new ConfigurableItem<Integer>(NUMBER_SERIES, 10)
                , new ConfigurableItem<Integer>(SERIES_LENGTH_DAYS, 4)};


        for (ConfigurableItem item : items) {
            defaultConfiguration.configuration().put(item.name(), item);
        }

        return defaultConfiguration;

    }


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

        this.fillSchedule(schedule, participants);




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


    private void fillSchedule(Schedule schedule, List<Participant> participants)
    {

        ConfigurableItem<Integer> groupSize = schedule.configuration().getInteger(TEAM_SIZE);
        ConfigurableItem<Boolean> teamsHaveLead = schedule.configuration().getBoolean(HAS_LEAD);
        ConfigurableItem<Integer> numberOfSeriesItems = schedule.configuration().getInteger(NUMBER_SERIES);
        ConfigurableItem<Integer> seriesLengthInDays = schedule.configuration().getInteger(SERIES_LENGTH_DAYS);



        ParticipantMatchesBag matchesBag = new ParticipantMatchesBag();
        matchesBag.createCombinations(participants.toArray(new Participant[participants.size()]), groupSize.value());
        List<Match> listOfAvailableMatches = null;

        for (int i = 0; i < numberOfSeriesItems.value(); i++)
        {

            PrayerEvent prayerEvent = new PrayerEvent();
            schedule.series().addEvent(prayerEvent);

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
    }



}
