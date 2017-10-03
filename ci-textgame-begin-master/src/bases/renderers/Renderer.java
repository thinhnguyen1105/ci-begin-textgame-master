package bases.renderers;

import bases.Vector2D;

import java.awt.*;

/**
 * Created by huynq on 7/28/17.
 */
public interface Renderer {
    void render(Graphics2D g2d, Vector2D position);
}
