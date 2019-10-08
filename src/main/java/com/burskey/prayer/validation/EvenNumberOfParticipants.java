package com.burskey.prayer.validation;

import com.burskey.prayer.participant.Participant;

import java.util.List;

import static com.burskey.validation.IntegerValidationHelpers.intEqualTo;

public class EvenNumberOfParticipants
{
    public void validate(List<Participant> participants)
    {
        if (participants != null)
        {
            intEqualTo(0).test(participants.size() % 2).throwIfInvalid("Participants must be evenly dividable by 2");
        }

    }
}
