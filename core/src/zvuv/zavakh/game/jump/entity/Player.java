package zvuv.zavakh.game.jump.entity;

import com.badlogic.gdx.math.MathUtils;
import zvuv.zavakh.game.jump.config.GameConfig;

public class Player extends EntityBase {

    private float angleDeg = GameConfig.PLAYER_START_ANGLE;
    private float angleDegSpeed = GameConfig.PLAYER_START_ANG_SPEED;
    private float speed = 0f;
    private float acceleration = GameConfig.PLAYER_START_ACC;
    private PlayerState playerState = PlayerState.WALKING;

    public Player() {
        setSize(GameConfig.PLAYER_SIZE, GameConfig.PLAYER_SIZE);
    }

    public void update(float delta) {
        if (playerState.isJumping()) {
            speed += acceleration * delta;

            if (speed >= GameConfig.PLAYER_MAX_SPEED) {
                fall();
            }
        } else if (playerState.isFalling()) {
            speed -= acceleration * delta;

            if (speed <= 0f) {
                speed = 0f;
                walk();
            }
        }

        angleDeg += angleDegSpeed * delta;
        angleDeg = angleDeg % 360;

        float radius = GameConfig.PLANET_HALF_SIZE + speed;
        float originX = GameConfig.WORLD_CENTER_X;
        float originY = GameConfig.WORLD_CENTER_Y;
        float newX = originX + MathUtils.cosDeg(-angleDeg) * radius;
        float newY = originY + MathUtils.sinDeg(-angleDeg) * radius;

        setPosition(newX, newY);
    }

    private void walk() {
        playerState = PlayerState.WALKING;
    }

    public void jump() {
        playerState = PlayerState.JUMPING;
    }

    private void fall() {
        playerState = PlayerState.FALLING;
    }

    public float getAngleDeg() {
        return angleDeg;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }
}
