package stortor.com.pool;

import stortor.com.base.SpritesPool;
import stortor.com.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }

}
