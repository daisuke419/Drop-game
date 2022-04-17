package com.mygdx.game.drop;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {

	final Drop game;
	
	private Texture backgroundImage;
	private Texture dropImage;
	private Texture bucketImage;
	private Sound dropSound;
	private Music rainMusic;
	
	private OrthographicCamera camera;
	
	private Rectangle bucket;
	
	private Array<Rectangle> raindrops;
	
	private long lastDropTime;
	
	private int dropGathered;
	
	private int hp = 5;
	

	public GameScreen(Drop gam) {
		game = gam;

		// internal は assetsフォルダの参照
		dropImage = gam.assets.get(Gdx.files.internal("droplet.png").path(), Texture.class);
		bucketImage = gam.assets.get(Gdx.files.internal("bucket.png").path(), Texture.class);
		backgroundImage = gam.assets.get(Gdx.files.internal("bgimage.png").path(), Texture.class);
		
		dropSound = gam.assets.get(Gdx.files.internal("drop.mp3").path(), Sound.class);
		rainMusic = gam.assets.get(Gdx.files.internal("rain.mp3").path(), Music.class);

		raindrops = new Array<>();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		rainMusic.setLooping(true);
		rainMusic.play();
		
		bucket = new Rectangle();
		bucket.x = 800 /2 - 64 / 2;
		bucket.y = 20;
		bucket.width = 64;
		bucket.height = 64;
		
		spawnRaindrop();
		
	}
	
	@Override
	public void show() {
		rainMusic.play();
	}

	@Override
	public void render(float delta) {
		//ScreenUtils.clear(0, 0, 0, 1);
		Gdx.gl.glClearColor(0, 0, 0.5f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		// バケツの更新
		if(Gdx.input.isTouched()) {
			Vector3 vector = new Vector3();
			vector.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(vector);
			bucket.x = vector.x - 64 /2;
		}
		
		if (Gdx.input.isKeyPressed(Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();
		
		if (bucket.x < 0) bucket.x = 0;
		if (bucket.x > 800 - 64 ) bucket.x = 800 - 64;
		
		// 雨粒の更新
		 Iterator<Rectangle> iter = raindrops.iterator();
		 while(iter.hasNext()) {
			 Rectangle raindrop = iter.next();
			 raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			 
			 if (raindrop.y  < 0) {
				 iter.remove();
				 hp--;
			 }
			 
			 if (raindrop.overlaps(bucket)) {
				 dropGathered++;
				 dropSound.play();
				 iter.remove();
			 }
		 }

		
		if (TimeUtils.nanoTime() - lastDropTime > 1_000_000_000) spawnRaindrop();
		
		
		// 描画
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.batch.draw(backgroundImage, 0, 0);
		game.smallFont.draw(game.batch, "Drop Collected ：" + dropGathered, 0, 480);
		game.smallFont.draw(game.batch, "HP : " + hp, 0, 430);
		game.batch.draw(bucketImage, bucket.x, bucket.y);
		for (Rectangle raindrop : raindrops) {
			game.batch.draw(dropImage, raindrop.x, raindrop.y);
		}
		game.batch.end();
		
		
		if (hp < 1) {
			game.setScreen(new GameOverScreen(game));
			dispose();
		}

	}

	@Override
	public void resize(int width, int height) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void pause() {
		rainMusic.pause();
	}

	@Override
	public void resume() {
		rainMusic.play();
	}

	@Override
	public void hide() {
		rainMusic.stop();
		dispose();

	}

	@Override
	public void dispose() {
//		dropImage.dispose();
//		bucketImage.dispose();
//		dropSound.dispose();
//		rainMusic.dispose();

	}
	
	
	/**
	 * 雨粒の発生
	 */
	private void spawnRaindrop() {
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, 800 - 64);
		raindrop.y = 480;
		raindrop.width = 64;
		raindrop.height = 64;
		
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}

}
