package cs3500.marblesolitaire.model.hw04;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.*;


/**
 * Tests the EuropeanSolitaireModel class.
 */
public class TriangleSolitaireModelTest {

  MarbleSolitaireModel t = new TriangleSolitaireModel();
  MarbleSolitaireModel e22 = new TriangleSolitaireModel(4, 0);



  ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>> Board = new ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>>();


  public void makeBoard() {
    int offSet = 0;
    for(int i = 0; i < 5; i++) {
      ArrayList<MarbleSolitaireModelState.SlotState> temp = new ArrayList<MarbleSolitaireModelState.SlotState>();

      //adds invalid left edge
      for(int j = 0; j < offSet; j++) {
        temp.add(MarbleSolitaireModelState.SlotState.Invalid);
      }

      //adds marbles
      for(int j = 1; j <= (2* 5 ) - (2 * offSet); j++) {
        if(j % 2 == 1) {
          temp.add(MarbleSolitaireModelState.SlotState.Marble);
        } else {
          temp.add(MarbleSolitaireModelState.SlotState.Invalid);
        }
      }

      //adds invalid right edge
      for(int j = 0; j < offSet; j++) {
        temp.add(MarbleSolitaireModelState.SlotState.Invalid);
      }

      offSet += 1;

      Board.add(temp);

    }
    Collections.reverse(Board);
  }



  @Test
  public void build() {
    makeBoard();
    Board.get(0).set(4, MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(t.getBoard(), Board);
  }

  @Test
  public void testIsValidMove() {
    assertEquals(t.isValidMove(2,2,4,0), true);
    assertEquals(t.isValidMove(6,2,4,0), true);
    assertEquals(t.isValidMove(4,2,4,0),false);
  }

  @Test
  public void move() {
    makeBoard();
    t.move(0,2,0,0);
    Board.get(0).set(4, MarbleSolitaireModelState.SlotState.Marble);
    Board.get(1).set(3, MarbleSolitaireModelState.SlotState.Empty);
    Board.get(2).set(2, MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(t.getBoard(), Board);
    Board = new ArrayList<>();
    makeBoard();
    t = new TriangleSolitaireModel();
    t.move(2,2,0,0);
    Board.get(0).set(4, MarbleSolitaireModelState.SlotState.Marble);
    Board.get(1).set(5, MarbleSolitaireModelState.SlotState.Empty);
    Board.get(2).set(6, MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(t.getBoard(), Board);
  }

  @Test
  public void testIsGameOver() {
    t = new TriangleSolitaireModel();
    assertEquals(t.isGameOver(), false);


  }

  @Test
  public void testGetBoardSize() {
    t = new TriangleSolitaireModel();
    assertEquals(t.getBoardSize(), 15);

  }

  @Test
  public void testGetSlotAt() {
    t = new TriangleSolitaireModel();
    assertEquals(t.getSlotAt(3,4), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(t.getSlotAt(2,2), MarbleSolitaireModelState.SlotState.Marble);
  }

  @Test
  public void testGetScore() {
    t = new TriangleSolitaireModel();
    assertEquals(t.getScore(), 14);
    t.move(0,2,0,0);
    assertEquals(t.getScore(), 13);
  }

  @org.junit.Test
  public void testConstructor() {

    // Test Exception
    try {
      t = new TriangleSolitaireModel(-1);
      fail("Expected an IllegalArgumentException to be thrown");
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Invalid values");
    }

    // Test Exception
    try {
      t = new TriangleSolitaireModel(0);
      fail("Expected an IllegalArgumentException to be thrown");
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Invalid values");
    }

    // Test Exception
    try {
      t = new TriangleSolitaireModel(-1, -2);
      fail("Expected an IllegalArgumentException to be thrown");
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Out of bounds");
    }

    // Test Exception
    try {
      t = new TriangleSolitaireModel(-1, -2, -4);
      fail("Expected an IllegalArgumentException to be thrown");
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Invalid values");
    }
  }
}