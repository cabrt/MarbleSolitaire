package cs3500.marblesolitaire.controller;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;

public class MarbleSolitaireControllerImplTests {
  MarbleSolitaireModel game = new EnglishSolitaireModel(3);
  MarbleSolitaireView board = new MarbleSolitaireTextView(game);

  @Test
  public void testValidMessage() {
    Readable readable = new StringReader("q");
    Appendable appendable = new StringBuilder();

    MarbleSolitaireView testView = new MarbleSolitaireTextView(game, appendable);
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(game, testView, readable);
    try {
      controller.playGame();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    assertEquals(
            "  OOO  \n" +
            "  OOO  \n" +
            "OOOOOOO\n" +
            "OOO_OOO\n" +
            "OOOOOOO\n" +
            "  OOO  \n" +
            "  OOO  \n" +
            "Score: 32\n" +
            "State of Game When Quit: \n" +
            "  OOO  \n" +
            "  OOO  \n" +
            "OOOOOOO\n" +
            "OOO_OOO\n" +
            "OOOOOOO\n" +
            "  OOO  \n" +
            "  OOO  \n" +
            "Score: 32", appendable.toString());

  }

  @Test
  public void testValidMove() {
    Readable readable = new StringReader("2\n2\n4\n4\nq");
    Appendable appendable = new StringBuilder();

    MarbleSolitaireView testView = new MarbleSolitaireTextView(game, appendable);
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(game, testView, readable);
    try {
      controller.playGame();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }


    assertEquals("  OOO  \n" +
            "  OOO  \n" +
            "OOOOOOO\n" +
            "OOO_OOO\n" +
            "OOOOOOO\n" +
            "  OOO  \n" +
            "  OOO  \n" +
            "Score: 32\n" +
            "  OOO  \n" +
            "  O_O  \n" +
            "OOO_OOO\n" +
            "OOOOOOO\n" +
            "OOOOOOO\n" +
            "  OOO  \n" +
            "  OOO  \n" +
            "Score: 31\n" +
            "State of Game When Quit: \n" +
            "  OOO  \n" +
            "  O_O  \n" +
            "OOO_OOO\n" +
            "OOOOOOO\n" +
            "OOOOOOO\n" +
            "  OOO  \n" +
            "  OOO  \n" +
            "Score: 31", appendable.toString());
  }

  @Test
  public void testInvalidMove() {
    Readable readable = new StringReader("3\n4\n4\n4\nq");
    Appendable appendable = new StringBuilder();

    MarbleSolitaireView testView = new MarbleSolitaireTextView(game, appendable);
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(game, testView, readable);
    try {
      controller.playGame();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    assertEquals("  OOO  \n" +
            "  OOO  \n" +
            "OOOOOOO\n" +
            "OOO_OOO\n" +
            "OOOOOOO\n" +
            "  OOO  \n" +
            "  OOO  \n" +
            "Score: 32\n" +
            "Invalid move, play again\n" +
            "  OOO  \n" +
            "  OOO  \n" +
            "OOOOOOO\n" +
            "OOO_OOO\n" +
            "OOOOOOO\n" +
            "  OOO  \n" +
            "  OOO  \n" +
            "Score: 32\n" +
            "State of Game When Quit: \n" +
            "  OOO  \n" +
            "  OOO  \n" +
            "OOOOOOO\n" +
            "OOO_OOO\n" +
            "OOOOOOO\n" +
            "  OOO  \n" +
            "  OOO  \n" +
            "Score: 32", appendable.toString());
  }
}
