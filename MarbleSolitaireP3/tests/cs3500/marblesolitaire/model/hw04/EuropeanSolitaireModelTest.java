package cs3500.marblesolitaire.model.hw04;

import org.junit.Test;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.*;


/**
 * Tests the EuropeanSolitaireModel class.
 */
public class EuropeanSolitaireModelTest {

  MarbleSolitaireModel e = new EuropeanSolitaireModel();
  MarbleSolitaireModel e11 = new EuropeanSolitaireModel(3, 3);



  ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>> Board = new ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>>();


  public void makeBoard() {
    ArrayList<MarbleSolitaireModelState.SlotState> temp = new ArrayList<>();
    temp.add(MarbleSolitaireModelState.SlotState.Invalid);
    temp.add(MarbleSolitaireModelState.SlotState.Invalid);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Invalid);
    temp.add(MarbleSolitaireModelState.SlotState.Invalid);
    Board.add(temp);
    temp = new ArrayList<>();
    temp.add(MarbleSolitaireModelState.SlotState.Invalid);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Invalid);
    Board.add(temp);
    temp = new ArrayList<>();
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    Board.add(temp);
    temp = new ArrayList<>();
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    Board.add(temp);
    temp = new ArrayList<>();
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    Board.add(temp);
    temp = new ArrayList<>();
    temp.add(MarbleSolitaireModelState.SlotState.Invalid);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Invalid);
    Board.add(temp);
    temp = new ArrayList<>();
    temp.add(MarbleSolitaireModelState.SlotState.Invalid);
    temp.add(MarbleSolitaireModelState.SlotState.Invalid);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Marble);
    temp.add(MarbleSolitaireModelState.SlotState.Invalid);
    temp.add(MarbleSolitaireModelState.SlotState.Invalid);
    Board.add(temp);
    Board.get(3).set(3, MarbleSolitaireModelState.SlotState.Empty);
    temp = new ArrayList<>();
  }



  @Test
  public void build() {
    makeBoard();
    assertEquals(Board, e.getBoard());
  }

  @org.junit.Test
  public void isValidMove() {
    e = new EuropeanSolitaireModel();
    assertEquals(e.isValidMove(3, 1, 3, 3), true);

    assertEquals(e.isValidMove(1, 3, 3, 3), true);

    assertEquals(e.isValidMove(5, 3, 3, 3), true);

    assertEquals(e.isValidMove(3, 5, 3, 3), true);

    assertEquals(e.isValidMove(3, 3, 3, 3), false);
  }

  @org.junit.Test
  public void move() {
    e.move(2,1,3,3);
    makeBoard();
    Board.get(1).set(3, MarbleSolitaireModelState.SlotState.Empty);
    Board.get(2).set(3, MarbleSolitaireModelState.SlotState.Empty);
    Board.get(3).set(3, MarbleSolitaireModelState.SlotState.Marble);

    assertEquals(e.getBoard(), Board);

    // Test Exception
    try {
      e.move(1, 1, 3, 3);
      fail("Expected an IllegalArgumentException to be thrown");
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Invalid Move");
    }

  }

  @org.junit.Test
  public void isGameOver() {
    e = new EuropeanSolitaireModel();

    assertEquals(e.isGameOver(), false);

    for(int i = 0; i < e.getBoard().size(); i++) {
      for(int j = 0; j < e.getBoard().get(i).size(); j++) {
        e.getBoard().get(i).set(j, MarbleSolitaireModelState.SlotState.Empty);
      }
    }
    e.getBoard().get(3).set(3, MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(e.isGameOver(), true);
  }

  @org.junit.Test
  public void getBoardSize() {
    e = new EuropeanSolitaireModel(3);
    assertEquals(e.getBoardSize(), 37);

  }

  @org.junit.Test
  public void getSlotAt() {
    e = new EuropeanSolitaireModel();
    assertEquals(e.getSlotAt(3,3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(e.getSlotAt(2,3), MarbleSolitaireModelState.SlotState.Marble);
  }

  @org.junit.Test
  public void getScore() {
    e = new EuropeanSolitaireModel(3);
    assertEquals(e.getScore(), 36);
  }

  @org.junit.Test
  public void testConstructor() {

    // Test Exception
    try {
      e = new EuropeanSolitaireModel(-1);
      fail("Expected an IllegalArgumentException to be thrown");
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Invalid values");
    }

    // Test Exception
    try {
      e = new EuropeanSolitaireModel(0);
      fail("Expected an IllegalArgumentException to be thrown");
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Invalid values");
    }

    // Test Exception
    try {
      e = new EuropeanSolitaireModel(2);
      fail("Expected an IllegalArgumentException to be thrown");
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Invalid values");
    }

    // Test Exception
    try {
      e = new EuropeanSolitaireModel(-1, -2);
      fail("Expected an IllegalArgumentException to be thrown");
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Out of bounds");
    }

    // Test Exception
    try {
      e = new EuropeanSolitaireModel(-1, -2, -4);
      fail("Expected an IllegalArgumentException to be thrown");
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Invalid values");
    }
  }
}