package com.mygdx.game.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * ゲームオーバー画面
 * @author daisuke419
 *
 */
public class GameOverScreen implements Screen {

	private final Drop owner;
	
	private Sound gameOverSound;
	private OrthographicCamera camera;
	
	public GameOverScreen(Drop game) {
		owner = game;
		
		gameOverSound = owner.assets.get(Gdx.files.internal("game_over.mp3").path(), Sound.class);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
	}
	
	@Override
	public void show() {
		gameOverSound.play();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		owner.batch.setProjectionMatrix(camera.combined);
		owner.batch.begin();
		owner.largeFont.draw(owner.batch, "Game Over", 250, 300);
		owner.largeFont.draw(owner.batch, "Score : " + owner.score, 250, 250);
		owner.smallFont.draw(owner.batch, "Tap anywhere or X key to TitleScreen.", 200, 150);
		owner.batch.end();
		
		if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.X)) {
			
			owner.setScreen(new MainMenuScreen(owner));
			dispose();
		}

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}

}
