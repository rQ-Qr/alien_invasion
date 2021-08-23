package game;

import controller.GameController;
import model.GameModel;
import model.Sound;
import view.GameView;

/**
 * This is the file for initial the game.
 *
 * @author Shenquan Wang, Ran Qin
 * @version 1.0
 * @since 08/15/2021
 */
public class StartGame{
	/**
	 * This is the main method for this game.
	 * @param args argument for main function.
	 */
	public static void main(String[] args) {
		GameModel models = new GameModel();
		GameController controller = new GameController(models);
		GameView view = new GameView(models, controller);
		Sound.playSound(Sound.opening());
		view.startGame();
	}
}
