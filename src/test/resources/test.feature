Feature: Test multi player tic tac toe

  Scenario: Single player wins after three turns
    Given a board of size 5 x 5
    And a player that makes the following move on row 5
      | 5 |
      | 4 |
      | 3 |
    When the game is played
    Then the player wins

