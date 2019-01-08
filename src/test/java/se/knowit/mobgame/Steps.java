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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static se.knowit.mobgame.PlayerStatus.EXPELLED;
import static se.knowit.mobgame.PlayerStatus.WINNER;

public class Steps {
    private GameRunner gameRunner;
    private List<Player> players = new ArrayList<>();
    private TicTacToeBoard ticTacToeBoard;

    @Given("^a board of size (\\d+) x (\\d+)$")
    public void aBoardOfSizeX(int width, int height) {
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
        players.add(new Player(new TestBot(positionsQueue), 1));
    }

    @Then("^the player is expelled$")
    public void thePlayerIsExpelled() {
        assertEquals(EXPELLED, players.get(0).getStatus());
    }

    @Then("^the first player wins$")
    public void the_first_player_wins() {
        assertTrue("Game is not over", gameRunner.isGameWon());
        assertEquals(WINNER, players.get(0).getStatus());
        Player winner = players.get(0);
        players.stream()
                .filter(player -> !winner.equals(player))
                .map(Player::getStatus)
                .forEach(status -> assertNotEquals(WINNER, status));
    }
}
