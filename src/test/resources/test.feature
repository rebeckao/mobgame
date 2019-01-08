Feature: Test multi player tic tac toe

  Scenario: Single player wins horizontal after three turns
    Given a board of size 5 x 5
    And a player that makes the following moves
      | column | row |
      | 4      | 4   |
      | 3      | 4   |
      | 2      | 4   |
    When the game is played
    Then the player wins

  Scenario: Single player wins vertical after three turns
    Given a board of size 5 x 5
    And a player that makes the following moves
      | column | row |
      | 4      | 0   |
      | 4      | 1   |
      | 4      | 2   |
    When the game is played
    Then the player wins