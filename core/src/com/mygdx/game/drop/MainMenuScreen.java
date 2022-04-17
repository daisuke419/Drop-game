package com.mygdx.game.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {

	final Drop game;
	
	OrthographicCamera camera;
	
	public MainMenuScreen(Drop gam) {
		game = gam;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
	}
	
	@Override
	public void show() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void render(float delta) {
		
		ScreenUtils.clear(0, 0, 0.5f, 1);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.largeFont.draw(game.batch, "雨漏りしちゃってる！？", 200, 400);
		game.smallFont.draw(game.batch, "Tap anywhere or Z Key to begin!", 250, 150);
		game.batch.end();
		
		if(Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.Z)) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
		

	}

	@Override
	public void resize(int width, int height) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void pause() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void resume() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void hide() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void dispose() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
