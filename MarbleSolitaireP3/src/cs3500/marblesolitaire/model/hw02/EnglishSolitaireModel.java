package cs3500.marblesolitaire.model.hw02;
import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw04.ModelMethods;

// Zach Hixson and Conor Abramson-Tieu

//import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

/**
 * Represents the English Solitaire Model.
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel {
  public int getArmLength() {
    return armLength;
  }


  private ModelMethods m;
  private int armLength;
  private int sRow;
  private int sCol;

  /**
   * Constructs an English Solitaire Model with default arm
   * length of 3 and empty cell at (3,3).
   *
   */

  public EnglishSolitaireModel() {
    this.armLength = 3;
    this.sRow = 3;
    this.sCol = 3;
    build();

  }

  /**
   * Constructs an English Solitaire Model with default arm
   *
   * @param row the row of the empty cell
   * @param column the column of the empty cell
   * @throws IllegalArgumentException if the empty cell position is invalid
   */
  public EnglishSolitaireModel(int row, int column) {
    this.armLength = 3;

    if(column != 2 && column != 3 && column != 4 && row > -1 && row < 3 ) {
      this.sRow = row;
    } else {
      throw new IllegalArgumentException("Invalid empty cell position (" + row + "," + column + ")");
    }


    if(column > -1 && column < 6) {
      this.sCol = column;
    } else {
      throw new IllegalArgumentException("Invalid empty cell position (" + row + "," + column + ")");
    }


    build();
  }

  /**
   * Constructs an English Solitaire Model
   *
   * @param arm the length of the arm of the board
   * @throws IllegalArgumentException if the arm length is invalid
   */
  public EnglishSolitaireModel(int arm) {
    if(arm % 2 == 1 && arm > 0) {
      this.armLength = arm;
    } else {
      throw new IllegalArgumentException("Arm Length must be a positive odd number");
    }

    this.sRow = 3;
    this.sCol = 3;
    build();
  }

  /**
   * Constructs an English Solitaire Model
   *
   * @param arm the length of the arm of the board
   * @param row the row of the empty cell
   * @param column the column of the empty cell
   * @throws IllegalArgumentException if the arm length or empty cell position is invalid
   */
  public EnglishSolitaireModel(int arm, int row, int column) {
    if(arm % 2 == 1 && arm > 0) {
      this.armLength = arm;
    } else {
      throw new IllegalArgumentException("Arm Length must be a positive odd number");
    }
    if(column != 2 && column != 3 && column != 4 && row > -1 && row < 3 ) {
      this.sRow = row;
    } else {
      throw new IllegalArgumentException("Invalid empty cell position (" + row + "," + column + ")");
    }


    if(column < -1 && column > 6) {
      this.sCol = column;
    } else {
      throw new IllegalArgumentException("Invalid empty cell position (" + row + "," + column + ")");
    }
    build();
  }


  protected ArrayList<ArrayList<SlotState>> Board = new ArrayList<ArrayList<SlotState>>();
  /**
   * Builds the board.
   *
   * @return the built board
   */
  public void build() {
    ArrayList<SlotState> temp;
    for (int i = 0; i < (armLength -1 ); i++) {
      temp = new ArrayList<SlotState>();

      for (int j = 0; j < armLength - 1; j++) {
        temp.add(SlotState.Invalid);
      }

      for (int j = 0; j < armLength; j++) {
        temp.add(SlotState.Marble);
      }

      for (int j = 0; j < armLength - 1; j++) {
        temp.add(SlotState.Invalid);
      }

      this.Board.add(temp);
    }

    for (int i = 0; i < armLength; i++) {
      temp = new ArrayList<SlotState>();


      for (int j = 0; j < (armLength * 2 + 1); j++) {
        temp.add(SlotState.Marble);
      }


      this.Board.add(temp);
    }


    for (int i = 0; i < (armLength - 1); i++) {
      temp = new ArrayList<SlotState>();

      for (int j = 0; j < armLength - 1; j++) {
        temp.add(SlotState.Invalid);
      }


      for (int j = 0; j < armLength; j++) {
        temp.add(SlotState.Marble);
      }


      for (int j = 0; j < armLength - 1; j++) {
        temp.add(SlotState.Invalid);
      }

      this.Board.add(temp);
    }
    Board.get(sCol).set(sRow, SlotState.Empty);

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


  public ArrayList<ArrayList<SlotState>> getBoard() {
    return this.Board;
  }
}