package se.knowit.mobgame;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static se.knowit.mobgame.PlayerStatus.EXPELLED;
import static se.knowit.mobgame.PlayerStatus.WINNER;

public class Steps {
    private GameRunner gameRunner;
    private List<Player> players = new ArrayList<>();
    private int playerCounter = 1;
    private int winCondition = 3;
    private int height;
    private int width;

    @Given("^a board of size (\\d+) x (\\d+)$")
    public void aBoardOfSizeX(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @When("^the game is played$")
    public void theGameIsPlayed() {
        TicTacToeBoard ticTacToeBoard = new TicTacToeBoard(BoardHeight.of(height), BoardWidth.of(width));
        gameRunner = new GameRunner(ticTacToeBoard, players, winCondition);
        gameRunner.run();
    }

    @Then("^the player wins$")
    public void thePlayerWins() {
        assertTrue("Game is not over", gameRunner.isGameWon());
        assertEquals(WINNER,players.get(0).getStatus());
    }

    @And("^a player that makes the following moves$")
    public void aPlayerThatMakesTheFollowingMoves(List<TurnCoordinate> turnCoordinates) {
        Queue<Position> positionsQueue = turnCoordinates.stream()
                .map(coordinate -> new Position(XCoordinate.of(coordinate.getColumn()), YCoordinate.of(coordinate.getRow())))
                .collect(Collectors.toCollection(LinkedList::new));
        players.add(new Player(new TestBot(positionsQueue), playerCounter++));
    }

    @Then("^the player is expelled$")
    public void thePlayerIsExpelled() {
        assertEquals(EXPELLED, players.get(0).getStatus());
    }

    @Then("^player (\\d+) wins$")
    public void thePlayerWithNumberWins(int playerNumber) {
        assertTrue("Game is not over", gameRunner.isGameWon());
        assertEquals(WINNER, players.get(playerNumber - 1).getStatus());
        Player winner = players.get(playerNumber - 1);
        players.stream()
                .filter(player -> !winner.equals(player))
                .map(Player::getStatus)
                .forEach(status -> assertNotEquals(WINNER, status));
    }

    @And("^win condition is (\\d+) in a row$")
    public void winConditionIsInARow(int winCondition) {
        this.winCondition = winCondition;
    }
}
