

import java.io.IOException;
import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * The main class for the Marble Solitaire game.
 */
public final class MarbleSolitaireMain {
  private int index;
  public static void main(String[] args) throws IOException {
    int size = 404;
    int row = 404;
    int col = 404;

    MarbleSolitaireModel model;


    if(args[contains(args, "-size")] == "-size") {
      size = Integer.parseInt(args[1 + contains(args, "-size")]);
    }
    if(args[contains(args, "-hole")] == "-hole") {
      row = Integer.parseInt(args[1 + contains(args, "-hole")]);
      col = Integer.parseInt(args[2 + contains(args, "-hole")]);
    }

    switch(args[0]) {
      case "english" :

        if(size == 404 && row == 404 && col == 404) {
          model = new EnglishSolitaireModel();
        } else if(size != 404 && row == 404 && col == 404) {
          model = new EnglishSolitaireModel(size);
        } else if(size == 404 && row != 404 && col != 404) {
          model = new EnglishSolitaireModel(row, col);
        } else if(size != 404 && row != 404 && col != 404) {
          model = new EnglishSolitaireModel(size, row, col);
        } else {
          throw new IllegalArgumentException("you need to enter row AND col plz");
        }
        break;
      case "european" :
        if(size == 404 && row == 404 && col == 404) {
          model = new EuropeanSolitaireModel();
        } else if(size != 404 && row == 404 && col == 404) {
          model = new EuropeanSolitaireModel(size);
        } else if(size == 404 && row != 404 && col != 404) {
          model = new EuropeanSolitaireModel(row, col);
        } else if(size != 404 && row != 404 && col != 404) {
          model = new EuropeanSolitaireModel(size, row, col);
        } else {
          throw new IllegalArgumentException("you need to enter row AND col plz");
        }
        break;
      case "triangular" :
        if(size == 404 && row == 404 && col == 404) {
          model = new TriangleSolitaireModel();
        } else if(size != 404 && row == 404 && col == 404) {
          model = new TriangleSolitaireModel(size);
        } else if(size == 404 && row != 404 && col != 404) {
          model = new TriangleSolitaireModel(row, col);
        } else if(size != 404 && row != 404 && col != 404) {
          model = new TriangleSolitaireModel(size, row, col);
        } else {
          throw new IllegalArgumentException("you need to enter row AND col plz");
        }
        break;
      default:
        throw new IllegalArgumentException("Model not found");
    }
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(model, view, new InputStreamReader(System.in));
    controller.playGame();

  }


  /**
   * Returns the index of the element in the array.
   * @param array the array to be searched
   * @param element the element to be found
   * @return the index of the element in the array
   */
  public static int contains(String[] array, String element) {
    for (int i = 0; i < array.length; i++) {
      if (array[i] == element) {

        return i;
      }
    }
    return 0;
  }

}