package stortor.com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import stortor.com.base.BaseScreen;
import stortor.com.math.Rect;
import stortor.com.pool.BulletPool;
import stortor.com.pool.EnemyPool;
import stortor.com.pool.ExplosionPool;
import stortor.com.sprite.Background;
import stortor.com.sprite.Bullet;
import stortor.com.sprite.EnemyShip;
import stortor.com.sprite.Explosion;
import stortor.com.sprite.MainShip;
import stortor.com.sprite.Star;
import stortor.com.util.EnemyEmitter;

public class GameScreen extends BaseScreen {

    private static final int STAR_COUNT = 64;
    private Vector2 v;

    private Music music;
    private Sound laserSound;
    private Sound bulletSound;
    private Sound explosionSound;

    private Texture bg;
    private Background background;
    private TextureAtlas atlas;

    private Star stars[];
    private BulletPool bulletPool;
    private EnemyPool enemyPool;
    private ExplosionPool explosionPool;

    private MainShip mainShip;

    private EnemyEmitter enemyEmitter;

    @Override
    public void show() {
        super.show();
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.setVolume(0.2f);
        music.setLooping(true);
        laserSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        bg = new Texture("textures/bg.png");
        background = new Background(bg);
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        explosionPool = new ExplosionPool(atlas, explosionSound);
        enemyPool = new EnemyPool(bulletPool, explosionPool, worldBounds, bulletSound);
        mainShip = new MainShip(atlas, bulletPool, explosionPool, laserSound);
        v = new Vector2(0.1f, 0f);
        enemyEmitter = new EnemyEmitter(enemyPool, worldBounds, atlas);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions();
        freeAllDestroyed();
        draw();
    }

    private void checkCollisions() {
        if (mainShip.isDestroyed()) {
            return;
        }
        List<EnemyShip> enemyShipList = enemyPool.getActiveObjects();
        for (EnemyShip enemyShip : enemyShipList) {
            float minDist = mainShip.getWidth();
            if (!enemyShip.isDestroyed()
                    && mainShip.pos.dst(enemyShip.pos) < minDist) {
                enemyShip.destroy();
                mainShip.damage(enemyShip.getDamage() * 2);
            }
        }
        List<Bullet> bulletList = bulletPool.getActiveObjects();
        for (Bullet bullet : bulletList) {
            if (bullet.isDestroyed()) {
                continue;
            }
            if (bullet.getOwner() != mainShip) {
                if (mainShip.isBulletCollision(bullet)) {
                    mainShip.damage(bullet.getDamage());
                    bullet.destroy();
                }
                continue;
            }
            for (EnemyShip enemyShip : enemyShipList) {
                if (enemyShip.isBulletCollision(bullet)) {
                    enemyShip.damage(bullet.getDamage());
                    bullet.destroy();
                }
            }
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        mainShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        atlas.dispose();
        bg.dispose();
        bulletPool.dispose();
        music.dispose();
        laserSound.dispose();
        enemyPool.dispose();
        bulletSound.dispose();
        explosionPool.dispose();
        explosionSound.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        mainShip.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        mainShip.touchUp(touch, pointer, button);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        if (!mainShip.isDestroyed()) {
            bulletPool.updateActiveObjects(delta);
            mainShip.update(delta);
            enemyPool.updateActiveObjects(delta);
            enemyEmitter.generate(delta);
        }
        music.play();
        explosionPool.updateActiveObjects(delta);
    }

    private void freeAllDestroyed() {
        bulletPool.freeAllDestroyed();
        enemyPool.freeAllDestroyed();
        explosionPool.freeAllDestroyed();
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        if (!mainShip.isDestroyed()) {
            bulletPool.drawActiveObjects(batch);
            enemyPool.drawActiveObjects(batch);
            mainShip.draw(batch);
        }
        explosionPool.drawActiveObjects(batch);
        batch.end();
    }

}