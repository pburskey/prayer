Feature: Sunny Day note

  Background:
    Given that I want to prepare to start a prayer circle
#    Given that the participants will be partitioned into groups of 2
    Given that the circle will have a scheduled duration of 40 days
    Given that the schedule will be partitioned so that teams are together for 4 days
    Given a group of participants
      | First | Last |
      | a     | a    |
      | b     | b    |
      | c     | c    |
      | d     | d    |
      | e     | e    |
      | f     | f    |
      | g     | g    |
      | h     | h    |

  Scenario: Sunny day prayer circle
    Given that a new prayer circle is started
    When the participants are validated
    Then the participants are valid
    Then the number of participants is even
    Then the prayer schedule configuration matches
      | Duration of schedule in Days | Duration of individual teams in days |
      | 40                           | 4                                    |
    When the prayer circle generates a new schedule
    Then a schedule has been created
    Then the schedule consists has a series of events
    Then the series has 10 events
    Then each series event has 4 participants
    Then each series participant is a team
    Then each series team has 2 participants
    Then each series team has a leader
    Then a team does not repeat in consecutive series events



