package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;


/**
 * Represents the European Solitaire Model.
 */
public class EuropeanSolitaireModel implements MarbleSolitaireModel {
  private int armLength;
  private int sRow;
  private int sCol;
  private ModelMethods m;

  private ArrayList<ArrayList<SlotState>> Board = new ArrayList<ArrayList<SlotState>>();

  /**
   * Constructs an European Solitaire Model with default arm
   * length of 3 and empty cell at (3,3).
   */
  public EuropeanSolitaireModel() {
    armLength = 3;
    sRow = 3;
    sCol = 3;
    build();
    Board.get(sCol).set(sRow, SlotState.Empty);
  }


  /**
   * Constructs an European Solitaire Model with default arm
   *
   * @param armLength the arm length of the board
   * @throws IllegalArgumentException if the arm length is invalid
   */
  public EuropeanSolitaireModel(int armLength) {
    if(armLength % 2 == 1 && armLength > 0) {
      this.armLength = armLength;
    } else {
      throw new IllegalArgumentException("Invalid values");
    }
    sRow = 3;
    sCol = 3;
    build();
    Board.get(sCol).set(sRow, SlotState.Empty);
  }

  /**
   * Constructs an European Solitaire Model with default arm
   *
   * @param armLength the arm length of the board
   * @param sR        the row of the empty cell
   * @param sC        the column of the empty cell
   * @throws IllegalArgumentException if the arm length is invalid
   */
  public EuropeanSolitaireModel(int armLength, int sR, int sC) {
    if(armLength % 2 == 1 && armLength > 0) {
      this.armLength = armLength;
    } else {
      throw new IllegalArgumentException("Invalid values");
    }
    build();

    if(getSlotAt(sR, sC) == SlotState.Marble) {
      sRow = sR;
      sCol = sC;
    }


    Board.get(sCol).set(sRow, SlotState.Empty);
  }

  /**
   * Constructs an European Solitaire Model with default arm
   *
   * @param sR the row of the empty cell
   * @param sC the column of the empty cell
   * @throws IllegalArgumentException if the empty cell position is invalid
   */
  public EuropeanSolitaireModel(int sR, int sC) {
    this.armLength = 3;
    build();
    if (getSlotAt(sR, sC) == SlotState.Invalid) {
      throw new IllegalArgumentException("Invalid values");
    }

    if(getSlotAt(sR, sC) == SlotState.Marble) {
      sRow = sR;
      sCol = sC;
    }
    Board.get(sCol).set(sRow, SlotState.Empty);
  }

  /**
   * Constructs the European Solitaire Model
   */
  public void build() {
    int emptySpace = (3 * (armLength) - 2) - armLength;
    int offSet = 0;
    for (int i = 0; i < (3 * armLength - 2); i++) {
      ArrayList<SlotState> temp = new ArrayList<SlotState>();
      for (int j = 0; j < ((emptySpace / 2) - offSet); j++) {
        temp.add(SlotState.Invalid);
      }

      for (int j = 0; j < (armLength + (2 * offSet)); j++) {
        temp.add(SlotState.Marble);
      }

      for (int j = 0; j < ((emptySpace / 2) - offSet); j++) {
        temp.add(SlotState.Invalid);
      }


      if (i < (armLength - 1)) {

        offSet = offSet + 1;
      } else if (i >= (2 * armLength - 2)) {

        offSet = offSet - 1;
      }


      Board.add(temp);


    }

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
    m = new ModelMethods(this.Board);
    return m.isValidMove(fromX, fromY, toX, toY);
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
  public void move(int fromX, int fromY, int toX, int toY) {
    m = new ModelMethods(this.Board);
    this.Board = m.move(fromX, fromY, toX, toY);

  }


  /**
   * Checks if the game is over.
   *
   * @return true if the game is over, false otherwise
   */
  public boolean isGameOver() {
    m = new ModelMethods(this.Board);
    return m.isGameOver();
  }


  /**
   * Gets size of the board.
   *
   * @return the size of the board as an int
   */
  public int getBoardSize() {
    m = new ModelMethods(this.Board);
    return m.getBoardSize();
  }


  /**
   * Gets the state of the slot at the given position.
   *
   * @param row the row of the slot as an int
   * @param col the column of the slot as an int
   * @return the state of the slot
   * @throws IllegalArgumentException if the position is invalid
   */
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    m = new ModelMethods(this.Board);
    return m.getSlotAt(row, col);
  }

  /**
   * Gets the score of the game.
   *
   * @return the score of the game as an int
   */
  public int getScore() {
    m = new ModelMethods(this.Board);
    return m.getScore();
  }

  /**
   * Gets the board of the game.
   *
   * @return the board of the game as an ArrayList of ArrayList of SlotState
   */
  public ArrayList<ArrayList<SlotState>> getBoard() {
    return this.Board;
  }

  /**
   * Gets the arm length of the game.
   *
   * @return the arm length of the game as an int
   */
  public int getArmLength() {
    return this.armLength;
  }

}