package zvuv.zavakh.game.jump.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import zvuv.zavakh.game.jump.config.GameConfig;
import zvuv.zavakh.game.jump.entity.Planet;
import zvuv.zavakh.game.jump.entity.Player;

public class Controller {

    private Planet planet;
    private Player player;

    public Controller() {
        planet = new Planet();
        planet.setPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);

        float playerStartX = GameConfig.WORLD_CENTER_X - GameConfig.PLAYER_HALF_SIZE;
        float playerStartY = GameConfig.WORLD_CENTER_Y + GameConfig.PLANET_HALF_SIZE;
        player = new Player();
        player.setPosition(playerStartX, playerStartY);
    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player.getPlayerState().isWalking()) {
            player.jump();
        }
        
        player.update(delta);
    }

    public Planet getPlanet() {
        return planet;
    }

    public Player getPlayer() {
        return player;
    }
}
