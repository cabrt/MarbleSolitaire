package cs3500.marblesolitaire.model.hw02;

import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * Tests the EnglishSolitaireModel class.
 */
public class EnglishSolitaireModelTest {

  MarbleSolitaireModel e = new EnglishSolitaireModel();
  MarbleSolitaireModel e11 = new EnglishSolitaireModel(1, 1);



  ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>> Board = new ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>>();


  void makeBoard() {

    ArrayList<MarbleSolitaireModelState.SlotState> temp = new ArrayList<MarbleSolitaireModelState.SlotState>();
    for (int i = 0; i < 2; i++) {
      temp = new ArrayList<MarbleSolitaireModelState.SlotState>();
      for(int j = 0; j < 2; j++) {
        temp.add(MarbleSolitaireModelState.SlotState.Invalid);
      }


      for (int j = 0; j < 3; j++) {
        temp.add(MarbleSolitaireModelState.SlotState.Marble);
      }
      for(int j = 0; j < 2; j++) {
        temp.add(MarbleSolitaireModelState.SlotState.Invalid);
      }

      this.Board.add(temp);
    }

    for (int k = 0; k < 3; k++) {
     temp = new ArrayList<MarbleSolitaireModelState.SlotState>();


      for (int j = 0; j < (3 * 2 + 1); j++) {
        temp.add(MarbleSolitaireModelState.SlotState.Marble);
      }


      this.Board.add(temp);
    }


    for (int l = 0; l < (3 - 1); l++) {
      temp = new ArrayList<MarbleSolitaireModelState.SlotState>();


      for(int j = 0; j < 2; j++) {
        temp.add(MarbleSolitaireModelState.SlotState.Invalid);
      }


      for (int j = 0; j < 3; j++) {
        temp.add(MarbleSolitaireModelState.SlotState.Marble);
      }
      for(int j = 0; j < 2; j++) {
        temp.add(MarbleSolitaireModelState.SlotState.Invalid);
      }


      this.Board.add(temp);
    }

  }



  @org.junit.Test
  public void build() {
    EnglishSolitaireModel e = new EnglishSolitaireModel();
    makeBoard();
 Board.get(3).set(3, MarbleSolitaireModelState.SlotState.Empty);
    System.out.println(Board.size() + " == " + e.getBoard().size());
  assertEquals(Board, e.getBoard());
}

  @org.junit.Test
  public void isValidMove() {
    e = new EnglishSolitaireModel();
    assertEquals(e.isValidMove(3, 1, 3, 3), true);

    assertEquals(e.isValidMove(1, 3, 3, 3), true);

    assertEquals(e.isValidMove(5, 3, 3, 3), true);

    assertEquals(e.isValidMove(3, 5, 3, 3), true);

    assertEquals(e.isValidMove(3, 3, 3, 3), false);
  }

  @org.junit.Test
  public void move() {
    e.move(1,1,3,3);
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
    e = new EnglishSolitaireModel();

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
    e = new EnglishSolitaireModel();
    assertEquals(e.getBoardSize(), 33);

  }

  @org.junit.Test
  public void getSlotAt() {
    e = new EnglishSolitaireModel();
    assertEquals(e.getSlotAt(3,3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(e.getSlotAt(2,3), MarbleSolitaireModelState.SlotState.Marble);
  }

  @org.junit.Test
  public void getScore() {
    e = new EnglishSolitaireModel();
    assertEquals(e.getScore(), 32);
  }

  @org.junit.Test
  public void testConstructor() {

    // Test Exception
    try {
      e = new EnglishSolitaireModel(-1);
      fail("Expected an IllegalArgumentException to be thrown");
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Arm Length must be a positive odd number");
    }

    // Test Exception
    try {
      e = new EnglishSolitaireModel(0);
      fail("Expected an IllegalArgumentException to be thrown");
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Arm Length must be a positive odd number");
    }

    // Test Exception
    try {
      e = new EnglishSolitaireModel(2);
      fail("Expected an IllegalArgumentException to be thrown");
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Arm Length must be a positive odd number");
    }

    // Test Exception
    try {
      e = new EnglishSolitaireModel(-1, -2);
      fail("Expected an IllegalArgumentException to be thrown");
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Invalid empty cell position (-1,-2)");
    }

    // Test Exception
    try {
      e = new EnglishSolitaireModel(-1, -2, -4);
      fail("Expected an IllegalArgumentException to be thrown");
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Arm Length must be a positive odd number");
    }
  }
}