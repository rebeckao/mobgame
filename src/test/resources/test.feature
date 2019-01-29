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

  Scenario: Single player wins sloping diagonally after three turns
    Given a board of size 5 x 5
    And a player that makes the following moves
      | column | row |
      | 2      | 0   |
      | 3      | 1   |
      | 4      | 2   |
    When the game is played
    Then the player wins

  Scenario: Single player wins rising diagonally after three turns
    Given a board of size 5 x 5
    And a player that makes the following moves
      | column | row |
      | 2      | 2   |
      | 3      | 1   |
      | 4      | 0   |
    When the game is played
    Then the player wins

  Scenario: Position outside board is invalid
    Given a board of size 5 x 5
    And a player that makes the following moves
      | column | row |
      | 5      | 5   |
    When the game is played
    Then the player is expelled

  Scenario: Taken position is invalid
    Given a board of size 5 x 5
    And a player that makes the following moves
      | column | row |
      | 4      | 4   |
      | 4      | 4   |
    When the game is played
    Then the player is expelled

  Scenario: First player to complete three in a row wins
    Given a board of size 5 x 5
    And a player that makes the following moves
      | column | row |
      | 2      | 2   |
      | 3      | 1   |
      | 4      | 0   |
    And a player that makes the following moves
      | column | row |
      | 1      | 2   |
      | 1      | 1   |
      | 1      | 0   |
    When the game is played
    Then player 1 wins

  Scenario: Co-op not allowed
    Given a board of size 5 x 5
    And a player that makes the following moves
      | column | row |
      | 2      | 2   |
      | 3      | 2   |
      | 1      | 1   |
    And a player that makes the following moves
      | column | row |
      | 1      | 2   |
      | 1      | 3   |
      | 1      | 4   |
    When the game is played
    Then player 2 wins

  Scenario: Small board
    Given a board of size 2 x 2
    And a player that makes the following moves
      | column | row |
      | 0      | 1   |
      | 0      | 0   |
      | 0      | 2   |
    When the game is played
    Then the player is expelled

  Scenario: Big board
    Given a board of size 10 x 10
    And a player that makes the following moves
      | column | row |
      | 7      | 9   |
      | 8      | 9   |
      | 9      | 9   |
    When the game is played
    Then player 1 wins
