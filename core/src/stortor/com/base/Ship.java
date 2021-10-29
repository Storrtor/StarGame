package stortor.com.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import stortor.com.math.Rect;
import stortor.com.pool.BulletPool;
import stortor.com.sprite.Bullet;

public class Ship extends Sprite {

    protected Sound bulletSound;
    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletV;
    protected Vector2 bulletPos;
    protected float bulletHeight;
    protected int damage;
    protected int hp;

    protected Vector2 v;
    protected Vector2 v0;

    protected float reloadTimer;
    protected float reloadInterval;

    protected Rect worldBounds;

    private boolean isShot;

    public Ship() {
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bulletSound.play(0.1f);
        bullet.set(this, bulletRegion, bulletPos, bulletV, worldBounds, bulletHeight, damage);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        reloadTimer += delta;
        if (getTop() < worldBounds.getTop() && !isShot) {
            reloadTimer = reloadInterval;
            this.isShot = true;
        }
        if (reloadTimer >= reloadInterval) {
            shoot();
            reloadTimer = 0f;
        }
        bulletPos.set(pos);
    }

}
