package se.knowit.mobgame;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Steps {
    private GameRunner gameRunner;
    private List<Player> players = new ArrayList<>();
    private TicTacToeBoard ticTacToeBoard;

    @Given("^a board of size (\\d+) x (\\d+)$")
    public void aBoardOfSizeX(int width, int height)  {
        ticTacToeBoard = new TicTacToeBoard(BoardHeight.of(height), BoardWidth.of(width));
    }

    @Given("^a player that makes the following move on row 5$")
    public void aPlayerThatMakesTheFollowingMove(List<Integer> columnCoordinates) {
        Queue<Position> positions = columnCoordinates.stream()
                .map(columnCoordinate -> new Position(XCoordinate.of(columnCoordinate), YCoordinate.of(5)))
                .collect(Collectors.toCollection(LinkedList::new));
        players.add(new Player(new TestBot(positions), 1));
    }

    @When("^the game is played$")
    public void theGameIsPlayed() {
        gameRunner = new GameRunner(ticTacToeBoard, players);
        gameRunner.playOneTurn();
    }

    @Then("^the player wins$")
    public void thePlayerWins() {
        Assert.assertTrue("Game is not over", gameRunner.isGameWon());

    }
}
