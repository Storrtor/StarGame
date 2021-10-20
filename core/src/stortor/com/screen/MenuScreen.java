package stortor.com.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import stortor.com.base.BaseScreen;
import stortor.com.math.Rect;
import stortor.com.sprite.Background;
import stortor.com.sprite.Logo;

public class MenuScreen extends BaseScreen {

    private Texture bg;
    private Texture lg;

    private Background background;
    private Logo logo;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background  = new Background(bg);
        lg = new Texture("badlogic.jpg");
        logo = new Logo(lg);

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch);
        logo.draw(batch);
        batch.end();

    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        lg.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        logo.touchDown(touch,pointer,button);
        return super.touchDown(touch, pointer, button);
    }



}
