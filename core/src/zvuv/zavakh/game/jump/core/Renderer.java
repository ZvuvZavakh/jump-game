package zvuv.zavakh.game.jump.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import zvuv.zavakh.game.jump.config.GameConfig;
import zvuv.zavakh.game.util.ViewportUtils;
import zvuv.zavakh.game.util.debug.DebugCameraController;

public class Renderer implements Disposable {

    private final Controller controller;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final ShapeRenderer shapeRenderer;
    private final DebugCameraController debugCameraController;

    public Renderer(Controller controller) {
        this.controller = controller;
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        this.shapeRenderer = new ShapeRenderer();

        this.debugCameraController = new DebugCameraController();
        debugCameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);
    }

    public void render(float delta) {
        debugCameraController.handleDebugInput(delta);
        debugCameraController.applyTo(camera);

        renderDebug();
    }

    private void renderDebug() {
        ViewportUtils.drawGrid(viewport, shapeRenderer, GameConfig.CELL_SIZE);
        viewport.apply();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        drawDebug();
        shapeRenderer.end();
    }

    private void drawDebug() {
        shapeRenderer.setColor(Color.CHARTREUSE);
        shapeRenderer.circle(
                controller.getPlanet().getBounds().x,
                controller.getPlanet().getBounds().y,
                controller.getPlanet().getBounds().radius,
                30
        );

        shapeRenderer.setColor(Color.VIOLET);
        shapeRenderer.rect(
                controller.getPlayer().getBounds().x,
                controller.getPlayer().getBounds().y,
                0,
                0,
                controller.getPlayer().getBounds().getWidth(),
                controller.getPlayer().getBounds().getHeight(),
                1,
                1,
                GameConfig.PLAYER_START_ANGLE - controller.getPlayer().getAngleDeg()
        );
    }

    public void update(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
