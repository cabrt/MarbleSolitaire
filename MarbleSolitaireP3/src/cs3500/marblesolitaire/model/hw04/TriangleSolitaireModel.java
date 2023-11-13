package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;
import java.util.Collections;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Represents the Triangle Solitaire Model.
 */
public class TriangleSolitaireModel implements MarbleSolitaireModel {

  ModelMethods m;

  int dimension;

  int sRow;
  int sCol;

  /**
   * Constructs an Triangle Solitaire Model with default dimension
   * of 5 and empty cell at (0,0).
   */
  public TriangleSolitaireModel() {
    dimension = 5;
    sRow = 0;
    sCol = 0;
    build();
    board.get(sCol).set(findLocation(sRow, board.get(sCol)), SlotState.Empty);
  }


  /**
   * Constructs an Triangle Solitaire Model with default dimension
   *
   * @param dimension the arm length of the board
   * @throws IllegalArgumentException if the arm length is invalid
   */
  public TriangleSolitaireModel(int dimension) {
    if (dimension >= 1) {
      this.dimension = dimension;
    } else {
      throw new IllegalArgumentException("Invalid values");
    }
    build();
    sRow = findLocation(sRow, board.get(sCol));
    sCol = 3;
    board.get(sCol).set(sRow, SlotState.Empty);
  }

  /**
   * Constructs an Triangle Solitaire Model with default dimension and empty cell at (sR, sC).
   *
   * @param dimension the arm length of the board
   * @param sR        the row of the empty cell
   * @param sC        the column of the empty cell
   * @throws IllegalArgumentException if the arm length is invalid
   */
  public TriangleSolitaireModel(int dimension, int sR, int sC) {
    if (dimension >= 1) {
      this.dimension = dimension;
    } else {
      throw new IllegalArgumentException("Invalid values");
    }
    build();
    sRow = findLocation(sRow, board.get(sCol));
    sCol = sC;
    board.get(sCol).set(sRow, SlotState.Empty);
  }

  /**
   * Constructs an Triangle Solitaire Model with default dimension and empty cell at (sR, sC).
   *
   * @param sR the row of the empty cell
   * @param sC the column of the empty cell
   * @throws IllegalArgumentException if the arm length is invalid
   */
  public TriangleSolitaireModel(int sR, int sC) {
    this.dimension = 5;
    build();
    sRow = findLocation(sRow, board.get(sCol));
    sCol = sC;
    if (getSlotAt(sR, sC) == SlotState.Invalid) {
      throw new IllegalArgumentException("Invalid values");
    }
    board.get(sCol).set(sRow, SlotState.Empty);
  }


  ArrayList<ArrayList<SlotState>> board = new ArrayList<ArrayList<SlotState>>();


  /**
   * Gets the board
   *
   * @return the board
   */
  public ArrayList<ArrayList<SlotState>> getBoard() {
    return board;
  }

  /**
   * @return the arm length of the board
   */
  @Override
  public int getArmLength() {
    return dimension;
  }


  /**
   * Determines the location of a given slot state
   * @param location the location of the slot state
   * @param list the list of slot states
   * @return the location of the slot state
   */
  public int findLocation(int location, ArrayList<SlotState> list) {
    int count = 0;
    int result = 404;
    for(int i = 0; i < list.size(); i++) {
      if(list.get(i) == SlotState.Marble || list.get(i) == SlotState.Empty) {
        if(count == location) {
          result = i;
          break;
        } else {
          count += 1;
        }

      }

    }
    return result;
  }

  /**
   * Builds the TriangleSolitaireModel
   */
  public void build() {
    int offSet = 0;
    for(int i = 0; i < dimension; i++) {
      ArrayList<SlotState> temp = new ArrayList<SlotState>();

      //adds invalid left edge
      for(int j = 0; j < offSet; j++) {
        temp.add(SlotState.Invalid);
      }

      //adds marbles
      for(int j = 1; j <= (2* dimension ) - (2 * offSet); j++) {
        if(j % 2 == 1) {
          temp.add(SlotState.Marble);
        } else {
          temp.add(SlotState.Invalid);
        }
      }

      //adds invalid right edge
      for(int j = 0; j < offSet; j++) {
        temp.add(SlotState.Invalid);
      }

      offSet += 1;

      board.add(temp);

    }
    Collections.reverse(board);

  }

  /**
   * Move a single marble from a given position to another given position.
   * A move is valid only if the from and to positions are valid. Specific
   * implementations may place additional constraints on the validity of a move.
   *
   * @param fromX the row number of the position to be moved from
   *                (starts at 0)
   * @param fromY the column number of the position to be moved from
   *                (starts at 0)
   * @param toX   the row number of the position to be moved to
   *                (starts at 0)
   * @param toY   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  @Override
  public void move(int fromX, int fromY, int toX, int toY) throws IllegalArgumentException {

    try {
      fromX = findLocation(fromX,board.get(fromY));
      toX = findLocation(toX,board.get(toY));
    } catch(Exception e) {
      throw new IllegalArgumentException("Invalid Move");
    }



    if(isValidMove(fromX, fromY, toX, toY)) {
      board.get(fromY).set(fromX, SlotState.Empty);
      board.get(toY).set(toX, SlotState.Marble);
      if (fromX != toX && fromY != toY) {
        board.get((fromY + toY) / 2).set(((toX + fromX) / 2), SlotState.Empty);
      }
      if (fromY == toY) {
        board.get(fromY).set(((toX + fromX) / 2), SlotState.Empty);
      }
    } else {
      throw new IllegalArgumentException("Invalid Move");
    }

  }

  /**
   * Determines if a move is valid
   *
   * @param fromX the row number of the position to be moved from
   * @param fromY the column number of the position to be moved from
   * @param toX the row number of the position to be moved to
   * @param toY the column number of the position to be moved to
   * @return true if the move is valid, false otherwise
   */
  public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
    boolean marbleAndEmpty;
    try {
      marbleAndEmpty = board.get(fromY).get(fromX) == SlotState.Marble &&
              board.get(toY).get(toX) == SlotState.Empty;
    } catch(Exception e) {
      return false;
    }

    if(fromY == toY) {
      return marbleAndEmpty && board.get(fromY).get((toX + fromX) / 2) == SlotState.Marble &&
              Math.abs(toX - fromX) == 4;
    } else {
      return marbleAndEmpty &&  Math.abs(toX - fromX) == 2 && Math.abs(toY - fromY) == 2 &&
              board.get((fromY + toY) / 2).get((fromX + toX) / 2) == SlotState.Marble;
    }
  }

  /**
   * Determine and return if the game is over or not. A game is over if no
   * more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    boolean result = true;
    for(int i = 0; i < board.size(); i++) {
      for(int j = 0; j < board.get(i).size(); j++) {
        if(board.get(i).get(j) == SlotState.Marble) {



          if(isValidMove(j,i,j + 2, i + 2)) {
            result = false;
          }


          if(isValidMove(j,i,j - 2, i - 2)) {
            result = false;
          }

          if(isValidMove(j,i,j - 2, i + 2)) {
            result = false;
          }


          if(isValidMove(j,i,j + 2, i - 2)) {
            result = false;
          }


          if(isValidMove(j,i,j + 4, i)) {
            result = false;
          }


          if(isValidMove(j,i,j - 4, i)) {
            result = false;
          }

        }

      }
    }
    return result;
  }

  /**
   * Return the size of this board. The size is roughly the longest dimension of a board
   *
   * @return the size as an integer
   */
  public int getBoardSize() {
    m = new ModelMethods(this.board);
    return m.getBoardSize();
  }

  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the slot at the given row and column
   * @throws IllegalArgumentException if the row or the column are beyond
   *                                  the dimensions of the board
   */

  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    SlotState result;
    try {
      result = board.get(col).get(row);

    } catch(Exception e) {
      throw new IllegalArgumentException("Out of bounds");
    }
    return result;
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  @Override
  public int getScore() {
    m = new ModelMethods(this.board);
    return m.getScore();
  }
}