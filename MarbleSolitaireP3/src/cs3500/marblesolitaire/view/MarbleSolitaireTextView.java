package cs3500.marblesolitaire.view;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents the English Solitaire Model.
 */
public class MarbleSolitaireTextView  implements MarbleSolitaireView{

  private int armLength;
  protected ArrayList<ArrayList<MarbleSolitaireModelState.SlotState>> Board;
  protected Appendable destination;


  /**
   * Constructs an English Solitaire Model
   *
   * @param m the model
   * @param destination the destination
   * @throws IllegalArgumentException if the model or destination is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModel m, Appendable destination) {
    if(m == null) {
      throw new IllegalArgumentException("English solitaire model cant be null :)");
    } else {
      this.Board = m.getBoard();
      this.armLength = m.getArmLength();
    }

    if(destination == null) {
      throw new IllegalArgumentException("Hi, next time can we please make sure to use a non null value? \n " +
              "Thanks a BUNCH");
    } else {
      this.destination = destination;
    }


  }

  /**
   * Constructs an English Solitaire Model
   *
   * @param m the model
   */
  public MarbleSolitaireTextView(MarbleSolitaireModel m) {
    this.Board = m.getBoard();
    this.armLength = m.getArmLength();
    this.destination = new PrintStream(System.out);
  }








  /**
   * Returns the board as a string.
   * The string should be formatted as follows:
   * One line per row of the gameboard.
   * Each slot is either the character 'O' (capital O for the marble) or _ (underscore for empty),
   * or a space if the slot is invalid.
   * Slots are separated by a single space.
   *
   * @return the board as a string
   */
  public String toString() {
    String result = "";
    for(int i = 0; i < Board.size(); i++) {
      for(int j = 0; j < Board.get(i).size(); j++) {
        MarbleSolitaireModelState.SlotState currentSpace = Board.get(i).get(j);
        switch(currentSpace) {
          case Invalid:
            result = result + ' ';
            break;
          case Marble:
            result = result + 'O';
            break;
          case Empty:
            result = result + '_';
            break;
        }
      }
      result = result + "\n";
    }
    return result;
  }

  /**
   * Render the board to the provided data destination. The board should be rendered exactly
   * in the format produced by the toString method above
   *
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderBoard() throws IOException {
    try {
      destination.append(toString());
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    try {
      destination.append(message);
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}