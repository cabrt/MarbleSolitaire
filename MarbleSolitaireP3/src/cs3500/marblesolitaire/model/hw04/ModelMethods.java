package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class contains methods that are used by both the SolitaireModels
 */
public class ModelMethods {
  ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>> Board = new ArrayList<>();

  /**
   * Constructor for the ModelMethods class.
   *
   * @param b the board
   */
  public ModelMethods(ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>> b) {
    this.Board = b;

  }

  /**
   * Checks if the move made is valid.
   *
   * @param fromX start point on the x-axis of the move as an int
   * @param fromY start point on the y-axis of the move as an int
   * @param toX   end point on the x-axis of the move as an int
   * @param toY   end point on the y-axis of the move as an int
   * @return true if the move is valid, false otherwise
   */
  public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
    try {
      if (fromX == toX && 2 == Math.abs(fromY - toY)
              && Board.get(fromY).get(fromX) == MarbleSolitaireModelState.SlotState.Marble
              && Board.get(toY).get(toX) == MarbleSolitaireModelState.SlotState.Empty
              && Board.get((fromY + toY) / 2).get((fromX + toX) / 2) == MarbleSolitaireModelState.SlotState.Marble) {
        return true;
      } else if(fromY == toY && 2 == Math.abs( fromX - toX) && Board.get(fromY).get(fromX) == MarbleSolitaireModelState.SlotState.Marble
              && Board.get(toY).get(toX) == MarbleSolitaireModelState.SlotState.Empty
              && Board.get((fromY + toY) / 2).get((fromX + toX) / 2) == MarbleSolitaireModelState.SlotState.Marble) {
        return true;
      } else {
        return false;
      }
    } catch (IndexOutOfBoundsException e) {
      return false;
    }
  }





  /**
   * determines the offSet of a row, as in how many invalid spaces are in the left
   * half of the ArrayList
   * @param list The given row
   * @return the offSet of the row
   */
  public int countInvalidsInLeftHalf(ArrayList<MarbleSolitaireModelState.SlotState> list) {
    int size = list.size();
    int half = size / 2;
    int count = 0;

    for (int i = 0; i < half; i++) {
      if (list.get(i) == MarbleSolitaireModelState.SlotState.Invalid) {
        count++;
      }
    }

    return count;
  }


  /**
   * Moves the piece at the specified position to the specified position, if the move is valid.
   *
   * @param fromX the row number of the position to be moved from, starting at 0
   * @param fromY the column number of the position to be moved from, starting at 0
   * @param toX   the row number of the position to be moved to, starting at 0
   * @param toY   the column number of the position to be moved to, starting at 0
   * @throws IllegalArgumentException if the move is not valid
   */
  public ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>> move(int fromX, int fromY, int toX, int toY) {

    try {
      fromX = fromX + countInvalidsInLeftHalf(Board.get(fromY));
      toX = toX + countInvalidsInLeftHalf(Board.get(toY));
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid Index");
    }

    System.out.println( fromX + " " + fromY + " " + toX + " " + toY);
    if (isValidMove(fromX, fromY, toX, toY)) {
      Board.get(fromY).set(fromX, MarbleSolitaireModelState.SlotState.Empty);
      Board.get(toY).set(toX, MarbleSolitaireModelState.SlotState.Marble);
      if (fromX == toX) {
        Board.get((fromY + toY) / 2).set(((toX + fromX) / 2), MarbleSolitaireModelState.SlotState.Empty);
      }
      if (fromY == toY) {
        Board.get(fromY).set((fromX + toX) / 2, MarbleSolitaireModelState.SlotState.Empty);
      }
    } else {
      throw new IllegalArgumentException("Invalid Move");
    }
    return this.Board;

  }








  /**
   * Checks if the game is over.
   *
   * @return true if the game is over, false otherwise
   */
  public boolean isGameOver() {

    boolean result = true;
    for (int i = 0; i < Board.size(); i++) {
      for (int j = 0; j < Board.get(i).size(); j++) {
        //right
        if (isValidMove(j, i, (j + 2), i)) {
          result = false;
        }

        //left
        if (isValidMove(j, i, (j - 2), i)) {
          result = false;
        }

        //top
        if (isValidMove(j, i, j, (i - 2))) {
          result = false;
        }

        //bottom
        if (isValidMove(j, i, j, (i + 2))) {
          result = false;
        }
      }
    }
    return result;
  }

  /**
   * Gets size of the board.
   *
   * @return the size of the board as an int
   */
  public int getBoardSize() {
    int count = 0;
    for(int i = 0; i < Board.size(); i++) {
      for (int j = 0; j < Board.get(i).size(); j++) {
        if(Board.get(i).get(j) == MarbleSolitaireModelState.SlotState.Marble || Board.get(i).get(j) == MarbleSolitaireModelState.SlotState.Empty) {
          count += 1;
        }
      }
    }
    return count;
  }

  /**
   * Gets the state of the slot at the given position.
   *
   * @param row the row of the slot as an int
   * @param col the column of the slot as an int
   * @return the state of the slot
   * @throws IllegalArgumentException if the position is invalid
   */
  public MarbleSolitaireModelState.SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    MarbleSolitaireModelState.SlotState result;
    try {
      result = Board.get(col).get(row);

    } catch(Exception e) {
      throw new IllegalArgumentException("Out of bounds");
    }
    return result;
  }


  /**
   * Gets the score of the game.
   *
   * @return the score of the game as an int
   */
  public int getScore() {

    int count = 0;
    for (ArrayList<MarbleSolitaireModelState.SlotState> l : Board) {
      for (MarbleSolitaireModelState.SlotState s : l) {
        if (s.equals(MarbleSolitaireModelState.SlotState.Marble)) {
          count = count + 1;
        }
      }
    }
    return count;

  }


}