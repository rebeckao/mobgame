Feature: Test multi player tic tac toe

  Scenario: Single player wins horizontal after three turns
    Given a board of size 5 x 5
    And a player that makes the following move on row 5
      | 5 |
      | 4 |
      | 3 |
    When the game is played
    Then the player wins

  Scenario: Single player wins vertical after three turns
    Given a board of size 5 x 5
    And a player that makes the following moves
      | column | row |
      | 5      | 0   |
      | 5      | 1   |
      | 5      | 2   |
    When the game is played
    Then the player wins