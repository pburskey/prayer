package com.burskey.prayer.schedule;

import com.burskey.prayer.PrayerCircle;
import com.burskey.prayer.event.PrayerEvent;
import com.burskey.prayer.participant.Match;
import com.burskey.prayer.participant.Participant;
import com.burskey.prayer.participant.ParticipantMatchesBag;
import com.burskey.prayer.participant.Team;

import java.util.*;

public class PrayerScheduleFactory implements ScheduleFactory {





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

        ConfigurableItem<Integer> groupSize = schedule.configuration().getInteger(PrayerCircle.TEAM_SIZE);
        ConfigurableItem<Boolean> teamsHaveLead = schedule.configuration().getBoolean(PrayerCircle.HAS_LEAD);
        ConfigurableItem<Integer> eventLengthInDays = schedule.configuration().getInteger(PrayerCircle.EVENT_LENGTH_DAYS);
        ConfigurableItem<Integer> scheduleLengthInDays = schedule.configuration().getInteger(PrayerCircle.SCHEDULE_LENGTH_DAYS);



        ParticipantMatchesBag matchesBag = new ParticipantMatchesBag();
        matchesBag.createCombinations(participants.toArray(new Participant[participants.size()]), groupSize.value());
        List<Match> listOfAvailableMatches = null;

        PrayerEvent priorPrayerEvent = null;
        PrayerEvent currentPrayerEvent = null;

        int numberOfSeriesItems = scheduleLengthInDays.value() / eventLengthInDays.value();

        for (int i = 0; i < numberOfSeriesItems; i++)
        {
            priorPrayerEvent = currentPrayerEvent;
            currentPrayerEvent = new PrayerEvent();
            schedule.series().addEvent(currentPrayerEvent);

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

                List<Match> matchesContainingParticipant = Match.filterMatchesToOnlyThoseContaining(listOfAvailableMatches, firstParticipant);


                while(seriesMatch == null)
                {
                    for(Match aPotentialMatch : matchesContainingParticipant)
                    {
                        if (aPotentialMatch.hasParticipant(firstParticipant))
                        {
                            /*
                            cool lets roll with this if the matches other participants are available for us to choose yet...
                             */
                            boolean participantsAvailable = Match.containsParticipantsFromList(aPotentialMatch, seriesParticipants);

                            boolean matchFoundInPriorEvent = false;
                            /*
                            lets see if the prior prayer event contains this potential match.
                             */
                            if (priorPrayerEvent != null && !priorPrayerEvent.getParticipants().isEmpty())
                            {
                                matchFoundInPriorEvent = priorPrayerEvent.getParticipants().contains(aPotentialMatch);
                            }


                            if (participantsAvailable && !matchFoundInPriorEvent)
                            {
                                seriesMatch = aPotentialMatch;
                                break;
                            }
                        }
                    }

                    if (seriesMatch == null)
                    {
                        System.out.println("not able to find a match for participant: " + firstParticipant);
                        listOfAvailableMatches = matchesBag.combinationsAsList();
                        matchesContainingParticipant = Match.filterMatchesToOnlyThoseContaining(listOfAvailableMatches, firstParticipant);

                        if (groupSize.value() == seriesParticipants.size())
                        {
                            seriesMatch = Match.getMatchContaining(matchesContainingParticipant, seriesParticipants);

                        }

                    }



                }



                if (seriesMatch != null)
                {
                    for(Iterator iterator = seriesMatch.getParticipants().iterator(); iterator.hasNext();)
                    {
                        Participant aMatchParticipant = (Participant) iterator.next();
                        seriesParticipants.remove(aMatchParticipant);
                    }


                    listOfAvailableMatches.remove(seriesMatch);

                    if (teamsHaveLead.value() && seriesMatch.isTeam())
                    {
                        Team team = (Team) seriesMatch;
                        Participant teamLead = team.getParticipants().iterator().next();
                        team.setLead(teamLead);
                    }

                    currentPrayerEvent.addParticipant(seriesMatch);
                }
                else
                {
                    System.out.println("FATAL......not able to find a match for participant: " + firstParticipant);
                }


            }







        }
    }



}
