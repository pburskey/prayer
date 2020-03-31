package com.burskey.prayer;

import com.burskey.prayer.event.Event;
import com.burskey.prayer.event.Series;
import com.burskey.prayer.participant.Match;
import com.burskey.prayer.participant.Participant;
import com.burskey.prayer.participant.PrayerParticipant;
import com.burskey.prayer.participant.Team;
import com.burskey.prayer.schedule.ConfigurableItem;
import com.burskey.prayer.schedule.PrayerSchedule;
import com.burskey.prayer.schedule.PrayerScheduleValidation;
import com.burskey.prayer.schedule.SchedulingConfiguration;
import cucumber.api.PendingException;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StepDefinitions {

    List<Participant> participants = null;
    PrayerCircle prayerCircle = null;
    SchedulingConfiguration configuration = null;

    @Before
    public void setup()
    {
        this.participants = new ArrayList<>();
        this.prayerCircle = null;
        this.configuration = null;
    }



    @Given("^that I want to prepare to start a prayer circle$")
    public void that_I_want_to_prepare_to_start_a_prayer_circle() {
        this.prayerCircle = new PrayerCircle();
        this.configuration = new SchedulingConfiguration();
    }

    @Given("^that the circle will have a scheduled duration of (\\d+) days$")
    public void that_the_circle_will_have_a_scheduled_duration_of_days(int arg1) {
        this.configuration.add(new ConfigurableItem<Integer>(PrayerCircle.SCHEDULE_LENGTH_DAYS, arg1));
    }

    @Given("^that the schedule will be partitioned so that teams are together for (\\d+) days$")
    public void that_the_schedule_will_be_partitioned_so_that_teams_are_together_for_days(int arg1) {
        this.configuration.add(new ConfigurableItem<Integer>(PrayerCircle.EVENT_LENGTH_DAYS, arg1));
    }

    @Given("^a group of participants$")
    public void a_group_of_participants(List<ParticipantDTO> dtos)
    {
        if (dtos != null)
        {
            for(ParticipantDTO dto: dtos)
            {
                PrayerParticipant participant = new PrayerParticipant();
                participant.setFirst(dto.first);
                participant.setLast(dto.last);
                this.participants.add(participant);
            }
        }
    }

    @Given("^that a new prayer circle is started$")
    public void that_a_new_prayer_circle_is_started() {
        this.prayerCircle = new PrayerCircle();
        for(Participant participant : this.participants)
        {
            this.prayerCircle.addParticipant(participant);
        }
        this.prayerCircle.configure(this.configuration);
    }

    @When("^the participants are validated$")
    public void the_participants_are_validated() {

    }

    @Then("^the participants are valid$")
    public void the_participants_are_valid() {

    }

    @Then("^the number of participants is even$")
    public void the_number_of_participants_is_even() {
        assertNotNull(this.prayerCircle.getParticipants());
        assertTrue(this.prayerCircle.getParticipants().size() > 0);
        assertTrue(this.prayerCircle.getParticipants().size() % 2 == 0);
    }

    @Then("^the prayer schedule configuration matches$")
    public void the_prayer_schedule_configuration_matches(List<ConfigurationDTO> list)
    {
        assertNotNull(list);
        for(ConfigurationDTO dto : list)
        {
            int durationOfScheduleInDays = Integer.valueOf(dto.durationOfScheduleInDays );
            int configured = this.configuration.getInteger(PrayerCircle.SCHEDULE_LENGTH_DAYS).value();
            assertEquals(durationOfScheduleInDays, configured);

            int durationOfIndividualTeamsInDays = Integer.valueOf(dto.durationOfIndividualTeamsInDays );
            configured = this.configuration.getInteger(PrayerCircle.EVENT_LENGTH_DAYS).value();
            assertEquals(durationOfIndividualTeamsInDays, configured);


        }

    }

    @When("^the prayer circle generates a new schedule$")
    public void the_prayer_circle_generates_a_new_schedule() {
        this.prayerCircle.getSchedule();
    }

    @Then("^a schedule has been created$")
    public void a_schedule_has_been_created() {
        assertNotNull(this.prayerCircle.getSchedule());
    }

    @Then("^the schedule consists has a series of events$")
    public void the_schedule_consists_has_a_series_of_events() {
        Series series = this.prayerCircle.getSchedule().series();
        assertNotNull(series);
        assertNotNull(series.getEvents());
        assertTrue(series.getEvents().length > 0);
    }

    @Then("^the series has (\\d+) events$")
    public void the_series_has_events(int arg1) {
        Series series = this.prayerCircle.getSchedule().series();
        assertNotNull(series);
        assertNotNull(series.getEvents());
        assertEquals(series.getEvents().length, arg1);
    }

    @Then("^each series event has (\\d+) participants$")
    public void each_series_event_has_participants(int arg1) {
        Series series = this.prayerCircle.getSchedule().series();
        assertNotNull(series);
        for(Event event : series.getEvents())
        {
            assertNotNull(event.getParticipants());
            assertEquals(event.getParticipants().size(), arg1);
        }
    }

    @Then("^each series participant is a team$")
    public void each_series_participant_is_a_team() {
        Series series = this.prayerCircle.getSchedule().series();
        assertNotNull(series);
        for(Event event : series.getEvents()) {
            assertNotNull(event.getParticipants());
            for (Participant participant : event.getParticipants()) {
                assertNotNull(participant);
                assertTrue(participant.isMatch());
                Match match = (Match) participant;
                assertTrue(match.isTeam());

            }
        }
    }

    @Then("^each series team has (\\d+) participants$")
    public void each_series_team_has_participants(int arg1) {
        Series series = this.prayerCircle.getSchedule().series();
        assertNotNull(series);
        for(Event event : series.getEvents()) {
            assertNotNull(event.getParticipants());
            for (Participant participant : event.getParticipants()) {
                assertNotNull(participant);
                assertTrue(participant.isMatch());
                Match match = (Match) participant;
                assertTrue(match.isTeam());

                assertTrue(match.hasParticipants());
                assertEquals(match.getParticipants().size(), arg1);
            }
        }
    }

    @Then("^each series team has a leader$")
    public void each_series_team_has_a_leader() {
        Series series = this.prayerCircle.getSchedule().series();
        assertNotNull(series);
        for(Event event : series.getEvents()) {
            assertNotNull(event.getParticipants());
            for (Participant participant : event.getParticipants()) {
                assertNotNull(participant);
                assertTrue(participant.isMatch());
                Team match = (Team) participant;

                assertNotNull(match.getLead());


            }
        }
    }

    @Then("^a team does not repeat in consecutive series events$")
    public void a_team_does_not_repeat_in_consecutive_series_events()
    {
        PrayerScheduleValidation validation = new PrayerScheduleValidation();
        assertNotNull(this.prayerCircle);
        assertNotNull(this.prayerCircle.getSchedule());
        try
        {
            validation.validateNonRepeatingPrayerMatches((PrayerSchedule) this.prayerCircle.getSchedule());
        }
        catch (Exception e)
        {
            fail(e.getMessage());
        }

    }


}
