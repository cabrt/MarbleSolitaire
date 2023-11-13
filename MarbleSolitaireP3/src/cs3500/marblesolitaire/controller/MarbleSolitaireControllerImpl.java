package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Represents the controller for the Marble Solitaire game.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController{
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private Readable read;

  private int toRow;
  private int toCol;
  private int fromRow;
  private int fromCol;

  private boolean quit = false;

  /**
   * Constructs a Marble Solitaire Controller.
   *
   * @param m the model
   * @param v the view
   * @param read the readable
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel m, MarbleSolitaireView v, Readable read) {
    if(m == null || v == null || read == null) {
      throw new IllegalArgumentException("Please no null values");
    } else {
      this.model = m;
      this.view = v;
      this.read = read;
    }
  }


  /**
   * Constructs and runs the game
   * @throws IOException if the controller is unable to successfully receive input or transmit
   */
  @Override
  public void playGame() throws IOException {
    Scanner sc = new Scanner(read);
    while(!model.isGameOver() && !quit ) {
      view.renderBoard();
      view.renderMessage("Score: " + model.getScore());
      view.renderMessage("\n");

      try {
        fromRow = (sc.nextInt() - 1);
      } catch(Exception e) {
        String quit = sc.next();
        if(quit.charAt(0) == 'q' || quit.charAt(0) == 'Q') {
          this.quit = true;

          break;

        } else {
          view.renderMessage("\n" + "Please re-enter this value");
          fromRow = (sc.nextInt() - 1);
        }
      }


      try {
        fromCol = (sc.nextInt() - 1);
      } catch(Exception e) {
        String quit = sc.next();
        if(quit.charAt(0) == 'q' || quit.charAt(0) == 'Q') {
          this.quit = true;

          break;

        } else {
          view.renderMessage("\n" + "Please re-enter this value");
          fromCol = (sc.nextInt() - 1);
        }
      }

      try {
        toRow = (sc.nextInt() - 1);
      } catch(Exception e) {
        String quit = sc.next();
        if(quit.charAt(0) == 'q' || quit.charAt(0) == 'Q') {
          this.quit = true;

          break;

        } else {
          view.renderMessage("\n" + "Please re-enter this value");
          toRow = (sc.nextInt() - 1);
        }
      }


      try {
        toCol = (sc.nextInt() - 1);
      } catch(Exception e) {
        String quit = sc.next();
        if(quit.charAt(0) == 'q' || quit.charAt(0) == 'Q') {
          this.quit = true;

          break;

        } else {
          view.renderMessage("\n" + "Please re-enter this value");
          toCol = (sc.nextInt() - 1);
        }
      }


      try {
        System.out.println(fromRow + " " + fromCol + " " + toRow + " " + toCol + " " );
        model.move(fromRow, fromCol, toRow, toCol);
      } catch (Exception e) {
        System.out.println(e.getMessage());

        view.renderMessage("Invalid move, play again" + "\n");
      }
    }
    if (!quit) {
      view.renderMessage("Game Over!" + "\n");
    } else {
      view.renderMessage("State of Game When Quit: " + "\n");
    }
    view.renderBoard();
    view.renderMessage("Score: " + model.getScore());


  }


  /**
   * Main method for the Marble Solitaire game.
   *
   * @param args the arguments
   * @throws IOException if the input or output is invalid
   */
  public static void main(String args[]) throws IOException {
    //change this later
    MarbleSolitaireModel m = new EnglishSolitaireModel();
    Readable r = new InputStreamReader(System.in);
    MarbleSolitaireView v = new MarbleSolitaireTextView(m);

    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(m,v,r);
    c.playGame();
  }

}




