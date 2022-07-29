package zvuv.zavakh.game.jump.entity;

public enum PlayerState {

    WALKING,
    JUMPING,
    FALLING;

    public boolean isWalking() {
        return this == WALKING;
    }

    public boolean isJumping() {
        return this == JUMPING;
    }

    public boolean isFalling() {
        return this == FALLING;
    }
}
