package bases.renderers;

import bases.Vector2D;

import java.awt.*;

/**
 * Created by huynq on 7/28/17.
 */
public class SolidRectRenderer implements Renderer {

    private Vector2D size;
    private Vector2D anchor;
    private Color color;


    public SolidRectRenderer() {
        size = new Vector2D();
        anchor = new Vector2D();
        color = Color.BLUE;
    }

    public Vector2D getSize() {
        return size;
    }

    public Vector2D getAnchor() {
        return anchor;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void render(Graphics2D g2d, Vector2D position) {
        g2d.setColor(color);
        g2d.fillRect(
                (int) (position.x - size.x * anchor.x),
                (int) (position.y - size.y * anchor.y),
                (int) size.x,
                (int) size.y);
    }
}
