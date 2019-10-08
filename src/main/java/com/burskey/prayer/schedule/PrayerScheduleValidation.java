package com.burskey.prayer.schedule;

import com.burskey.prayer.event.Event;
import com.burskey.prayer.participant.Match;
import com.burskey.prayer.participant.Participant;

import java.util.ArrayList;
import java.util.List;

public class PrayerScheduleValidation {


    public void validate(PrayerSchedule schedule)
    {
        this.validateApproximatelyEvenDistributionOfParticipants(schedule);
        this.validateNonRepeatingPrayerMatches(schedule);
    }

    public void validateNonRepeatingPrayerMatches(PrayerSchedule schedule)
    {
//        List<Participant> participantList = schedule.participants();
        /*
        iterate through and ensure that there is an approximately even distribution of the participants.
         */

        List<Match> lastMatches = null;
        List<Match> currentMatches = null;
        for (Event event : schedule.series().getEvents())
        {
            lastMatches = currentMatches;
            currentMatches = new ArrayList<>();


            for(Participant eventParticipant: event.getParticipants())
            {
                if (eventParticipant.isMatch())
                {
                    Match match = (Match) eventParticipant;
                    currentMatches.add(match);
                }
            }

            if (lastMatches != null && !lastMatches.isEmpty())
            {
                for(Match lastMatch: lastMatches)
                {
                    if (currentMatches.contains(lastMatch))
                    {
                        System.out.println("Found match: " + lastMatch + " to be repeating in schedule event: ");
                    }
                }
            }
        }





    }



    public void validateApproximatelyEvenDistributionOfParticipants(PrayerSchedule schedule)
    {
//        List<Participant> participantList = schedule.participants();
        /*
        iterate through and ensure that there is an approximately even distribution of the participants.
         */

        List<Match> lastMatches = new ArrayList<>();
        List<Match> currentMatches = new ArrayList<>();
        for (Event event : schedule.series().getEvents())
        {
            for(Participant eventParticipant: event.getParticipants())
            {
                if (eventParticipant.isMatch())
                {
                    Match match = (Match) eventParticipant;
                    match.getParticipants();


                }
            }
        }

    }





}
