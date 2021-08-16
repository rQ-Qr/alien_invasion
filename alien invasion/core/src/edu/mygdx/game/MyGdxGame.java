package edu.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import edu.mygdx.controller.GameController;
import edu.mygdx.model.GameModel;
import edu.mygdx.model.Sound;
import edu.mygdx.view.GameView;
import edu.mygdx.view.RegFrame;

import javax.swing.*;

/**
 * This is the file for initial the game.
 *
 * @author Shenquan Wang, Ran Qin
 * @version 1.0
 * @since 08/15/2021
 */
public class MyGdxGame extends ApplicationAdapter {
	/**
	 * This is the filed for this file.
	 */
	SpriteBatch batch;
	Texture img;

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

	/**
	 * This method is for creating.
	 */
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	/**
	 * This method is the render function.
	 */
	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}

	/**
	 * This method is to dispose the creating.
	 */
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
