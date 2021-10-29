package stortor.com.pool;

import com.badlogic.gdx.audio.Sound;

import stortor.com.base.SpritesPool;
import stortor.com.math.Rect;
import stortor.com.sprite.EnemyShip;

public class EnemyPool extends SpritesPool<EnemyShip> {

    private final BulletPool bulletPool;
    private final Rect worldBounds;
    private final Sound bulletSound;

    public EnemyPool(BulletPool bulletPool, Rect worldBounds, Sound bulletSound) {
        this.bulletPool = bulletPool;
        this.bulletSound = bulletSound;
        this.worldBounds = worldBounds;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(bulletPool, worldBounds, bulletSound);
    }
}
