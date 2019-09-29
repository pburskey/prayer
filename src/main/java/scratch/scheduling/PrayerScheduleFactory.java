package scratch.scheduling;

import scratch.*;

import java.util.*;

public class PrayerScheduleFactory implements ScheduleFactory {

    public ConfigurableItem<Integer> TEAM_SIZE = new ConfigurableItem<Integer>("TEAM_SIZE", 2);
    public ConfigurableItem<Boolean> HAS_LEAD = new ConfigurableItem<Boolean>("HAS_LEAD", true);
    public ConfigurableItem<Integer> NUMBER_SERIES = new ConfigurableItem<Integer>("SCHEDULE_DURATION", 10);
    public ConfigurableItem<Integer> SERIES_LENGTH_DAYS = new ConfigurableItem<Integer>("SCHEDULE_DURATION", 4);

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

        for (int i = 0; i < numberOfSeriesItems.value(); i++) {

            PrayerEvent prayerEvent = new PrayerEvent();
            series.addEvent(prayerEvent);
            List<Participant> randomList = new ArrayList();
            randomList.addAll(participants);
            Random random = new Random();
            while (!randomList.isEmpty())
            {
                Team team = new Team();
                for (int teamMemberCounter = 0; teamMemberCounter < groupSize.value(); teamMemberCounter++)
                {
                    int choice = random.nextInt(randomList.size());
                    Participant participant = randomList.remove(choice);
                    team.addParticipant(participant);
                }

                if (teamsHaveLead.value())
                {
                    Participant teamLead = team.getParticipants().iterator().next();
                    team.setLead(teamLead);
                }

                prayerEvent.addParticipant(team);
            }


        }


        return schedule;
    }
}
