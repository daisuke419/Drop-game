package com.mygdx.game.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class GameOverScreen implements Screen {

	final Drop owner;
	
	public GameOverScreen(Drop game) {
		owner = game;
	}
	
	
	@Override
	public void show() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.5f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		owner.batch.begin();
		owner.largeFont.draw(owner.batch, "Game Over", 250, 300);
		owner.smallFont.draw(owner.batch, "Tap anywhere or X key to TitleScreen.", 200, 150);
		owner.batch.end();
		
		if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.X)) {
			
			owner.setScreen(new MainMenuScreen(owner));
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
