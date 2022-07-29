package zvuv.zavakh.game.jump.screen;

import com.badlogic.gdx.ScreenAdapter;
import zvuv.zavakh.game.jump.App;
import zvuv.zavakh.game.jump.core.Controller;
import zvuv.zavakh.game.jump.core.Renderer;
import zvuv.zavakh.game.util.GdxUtils;

public class GameScreen extends ScreenAdapter {

    private final App app;

    private Controller controller;
    private Renderer renderer;

    public GameScreen(App app) {
        this.app = app;
    }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();
        controller.update(delta);
        renderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        renderer.update(width, height);
    }

    @Override
    public void show() {
        controller = new Controller();
        renderer = new Renderer(controller);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
