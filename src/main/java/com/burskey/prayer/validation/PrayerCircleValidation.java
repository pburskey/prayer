package com.burskey.prayer.validation;

import com.burskey.prayer.PrayerCircle;
import com.burskey.prayer.schedule.PrayerSchedule;
import com.burskey.prayer.schedule.PrayerScheduleValidation;

public class PrayerCircleValidation
{

    public void validate(PrayerCircle prayerCircle)
    {

        (new EvenNumberOfParticipants()).validate(prayerCircle.getParticipants());

        (new PrayerScheduleValidation()).validate((PrayerSchedule) prayerCircle.getSchedule());

    }
}
