package com.mygdx.game.drop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * 雨漏りゲームのメインとなるクラス
 * @author daisuke419
 *
 */
public class Drop extends Game {

	public SpriteBatch batch;
	public BitmapFont smallFont;
	public BitmapFont largeFont;	
	public AssetManager assets;
	public int score = 0;

	/** 日本語を使用するため、フィールドに保持しておく */
	private FreeTypeFontGenerator generator;
	
	/**
	 * ゲーム作成時の処理
	 */
	@Override
	public void create () {
		batch = new SpriteBatch();
		assets = new AssetManager();

		// 素材の読み込み
		assets.load(Gdx.files.internal("droplet.png").path(), Texture.class);
		assets.load(Gdx.files.internal("bucket.png").path(), Texture.class);
		assets.load(Gdx.files.internal("bgimage.png").path(), Texture.class);
		assets.load(Gdx.files.internal("drop.mp3").path(), Sound.class);
		assets.load(Gdx.files.internal("game_over.mp3").path(), Sound.class);
		assets.load(Gdx.files.internal("rain.mp3").path(), Music.class);
		
		generator = new FreeTypeFontGenerator(Gdx.files.internal("ipaexg.ttf"));
		FreeTypeFontParameter param1 = new FreeTypeFontParameter();
		param1.size = 16;
		param1.incremental = true;
		param1.borderColor = Color.BLACK;
		param1.borderWidth=3;
		smallFont = generator.generateFont(param1);
		
		FreeTypeFontParameter param2 = new FreeTypeFontParameter();
		param2.size = 32;
		param2.incremental = true;
		param2.borderColor = Color.BLACK;
		param2.borderWidth = 3;
		largeFont = generator.generateFont(param2);

		// 読み込み完了するまで待つ
		assets.finishLoading();
		
		this.setScreen(new MainMenuScreen(this));
	}
	
	/**
	 * ゲーム終了処理
	 */
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		smallFont.dispose();
		largeFont.dispose();
		assets.dispose();
		generator.dispose();
	}	
}