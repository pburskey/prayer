Feature: Evenly distribute participants in pairs


  Scenario: Odd number of participants
    Given a group of 5 participants
    When the system is asked to evenly pair the participants
    Then the system is unable to evenly pair the participants


  Scenario Outline: Even number of participants
    Given a group of <Participants> participants
    When the system is asked to evenly pair the participants
    Then the system is able to evenly pair the participants
    Then the participants have been distributed such that
      | Group Size   | Number of Groups   |
      | <Group Size> | <Number of Groups> |

    Examples:
      | Participants | Group Size | Number of Groups |
      | 4            | 2          | 2                |
      | 6            | 2          | 2                |


  Scenario: Participants are evenly distributed over time
    Given a group of <Participants> participants
    Given a schedule with the following characteristics
    |Duration in Days| Days between Change|
    |40              |4                   |
    When the system is asked to evenly pair the participants



    Then the participants have been distributed such that
      | Participants | Group Size | Number of Groups |
      | 4            | 2          | 2                |
      | 6            | 2          | 2                |
    When the system is asked to distribute the participants into groups



