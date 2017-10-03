package bases;

import bases.renderers.Renderer;

import java.awt.*;
import java.util.Vector;

/**
 * Created by huynq on 7/28/17.
 */
public class GameObject {

    protected Vector2D position;
    protected Renderer renderer;

    private static Vector<GameObject> gameObjects = new Vector<>();
    private static Vector<GameObject> newGameObjects = new Vector<>();

    public static void renderAll(Graphics2D g2d) {
        for (GameObject gameObject: gameObjects) {
            gameObject.render(g2d);
        }
    }

    public static void runAll() {
        for (GameObject gameObject : gameObjects) {
            gameObject.run();
        }

        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
    }

    public static void add(GameObject gameObject) {
        newGameObjects.add(gameObject);
    }

    public GameObject() {
        position = new Vector2D();
    }

    public Vector2D getPosition() {
        return position;
    }

    public void render(Graphics2D g2d) {
        if (renderer != null) {
            renderer.render(g2d, position);
        }
    }

    public void run() {

    }
}
