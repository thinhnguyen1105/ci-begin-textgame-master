package bases.renderers;

import bases.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynq on 7/30/17.
 */
public class LineRenderer {
    private List<WordsRenderer> wordsRendererList;

    public LineRenderer() {
        wordsRendererList = new ArrayList<>();
    }

    public int stringWidth(FontMetrics fontMetrics) {
        int width = 0;
        for(WordsRenderer w: wordsRendererList) {
            width += w.stringWidth(fontMetrics);
        }
        return width;
    }

    public LineRenderer add(WordsRenderer wordsRenderer) {
        wordsRendererList.add(wordsRenderer);
        return this;
    }
    

    public void render(Graphics2D g2d, final Vector2D position) {
        Vector2D wordPosition = position.clone();
        FontMetrics fontMetrics = g2d.getFontMetrics();
        for (WordsRenderer wordsRenderer: wordsRendererList) {
            wordsRenderer.render(g2d, wordPosition);
            wordPosition.addUp(wordsRenderer.stringWidth(fontMetrics), 0);
        }
    }

    @Override
    public String toString() {
        return "LineRenderer{" +
                "wordsRendererList=" + wordsRendererList +
                '}';
    }
}
