package cs3500.marblesolitaire.controller;

import java.io.IOException;

/**
 * This interface represents operations that should be offered by
 * a controller for the Marble solitaire game.
 */
public interface MarbleSolitaireController {

  /**
   * Play a new game of Marble Solitaire using the provided model.
   *
   * @throws IOException if the controller is unable to successfully receive input or transmit
   *                    output
   */
  void playGame() throws IOException;
}
