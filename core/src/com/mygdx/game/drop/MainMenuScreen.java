package com.mygdx.game.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * タイトル画面
 * @author daisuke419
 *
 */
public class MainMenuScreen implements Screen {

	private final Drop owner;
	
	private OrthographicCamera camera;
	
	public MainMenuScreen(Drop gam) {
		owner = gam;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
	}
	
	@Override
	public void show() {
		owner.score = 0;
	}

	@Override
	public void render(float delta) {
		
		ScreenUtils.clear(0, 0, 0, 1);
		
		camera.update();
		owner.batch.setProjectionMatrix(camera.combined);
		
		owner.batch.begin();
		owner.largeFont.draw(owner.batch, "雨漏りしちゃってる！？", 200, 400);
		owner.smallFont.draw(owner.batch, "Tap anywhere or Z Key to begin!", 250, 150);
		owner.batch.end();
		
		if(Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.Z)) {
			owner.setScreen(new GameScreen(owner));
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
