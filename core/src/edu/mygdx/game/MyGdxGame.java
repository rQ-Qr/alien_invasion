package edu.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import edu.mygdx.controller.GameController;
import edu.mygdx.model.GameModel;
import edu.mygdx.view.GameView;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	public static void main(String[] args) {
		GameModel models = new GameModel();
		GameController controller = new GameController(models);
		GameView view = new GameView(models, controller);
		view.startGame();
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
