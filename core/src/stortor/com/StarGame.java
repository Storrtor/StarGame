package stortor.com;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class StarGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture imgBackGround;
	TextureRegion region;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		imgBackGround = new Texture("green2.jpg");
		region = new TextureRegion(imgBackGround);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1f,1f,1f,1);
		batch.begin();
		batch.draw(region,0,0);
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		imgBackGround.dispose();
		img.dispose();
	}
}
