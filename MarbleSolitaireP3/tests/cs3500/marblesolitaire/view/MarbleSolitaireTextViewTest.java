package cs3500.marblesolitaire.view;

import org.junit.Test;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.*;

/**
 * Tests the MarbleSolitaireTextView class.
 */
public class MarbleSolitaireTextViewTest {


  MarbleSolitaireModel e;
  MarbleSolitaireTextView m;

  @Test
  public void testToStringEnglish() {
    e = new EnglishSolitaireModel();
    MarbleSolitaireTextView m = new MarbleSolitaireTextView(e);
    assertEquals(m.toString(), "  OOO  \n  OOO  \nOOOOOOO\nOOO_OOO\nOOOOOOO\n  OOO  \n  OOO  \n");
    m.Board.get(0).set(2, MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(m.toString(), "  _OO  \n  OOO  \nOOOOOOO\nOOO_OOO\nOOOOOOO\n  OOO  \n  OOO  \n");

  }

  @Test
  public void testToStringEuropean() {
    e = new EuropeanSolitaireModel();
    m = new MarbleSolitaireTextView(e);
    assertEquals(m.toString(), "  OOO  \n OOOOO \nOOOOOOO\nOOO_OOO\nOOOOOOO\n OOOOO \n  OOO  \n");
    m.Board.get(0).set(2, MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(m.toString(), "  _OO  \n OOOOO \nOOOOOOO\nOOO_OOO\nOOOOOOO\n OOOOO \n  OOO  \n");
  }

  @Test
  public void testToStringTriangular() {
    e = new TriangleSolitaireModel();
    m = new MarbleSolitaireTextView(e);
    assertEquals(m.toString(), "    _     \n   O O    \n  O O O   \n O O O O  \nO O O O O \n");
    m.Board.get(4).set(0, MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(m.toString(), "    _     \n   O O    \n  O O O   \n O O O O  \n_ O O O O \n");
  }




  @Test
  public void testRenderBoard() {
    e = new EnglishSolitaireModel();
    MarbleSolitaireTextView m = new MarbleSolitaireTextView(e);
    try {
      MarbleSolitaireTextView board = new MarbleSolitaireTextView(e);
      board.renderBoard();
    } catch (Exception e) {
      fail("No error should be thrown for correct transmission");
    }
  }

  @Test
  public void testRenderMessage() {
    e = new EnglishSolitaireModel();
    MarbleSolitaireTextView m = new MarbleSolitaireTextView(e);
    try {
      MarbleSolitaireTextView board = new MarbleSolitaireTextView(e);
      board.renderMessage("\nhi\n");
    } catch (Exception e) {
      fail("No error should be thrown for correct transmission");
    }
  }
}