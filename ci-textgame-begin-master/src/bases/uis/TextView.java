package bases.uis;

import bases.Vector2D;
import bases.renderers.LineRenderer;
import bases.renderers.WordsRenderer;
import bases.uis.GamePanel;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynq on 7/28/17.
 */
public class TextView extends GamePanel {
    ArrayList<LineRenderer> lineRenderers;

    private final String SEPARATOR = "------------------------------------------------------------------------------------------------------------------------";
    private Color textColor;
    public static final int HEX_NUMBER_OF_CHAR = 7;

    private FontMetrics fontMetrics;
    private int linesMax = -1;
    Vector2D offsetText;
    private final Object renderLock;

    private StringBuilder textBuffer;

    TextView() {
        super();
        lineRenderers = new ArrayList<>();
        offsetText = new Vector2D();
        textColor = Color.WHITE;
        fontMetrics = null;
        linesMax = -1;
        textBuffer = new StringBuilder();
        renderLock = new Object();
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public Vector2D getOffsetText() {
        return offsetText;
    }

    @Override
    public void render(Graphics2D g2d) {
        super.render(g2d);
        g2d.setColor(textColor);

        if (fontMetrics == null) {
            setFontMetrics(g2d.getFontMetrics());
        }

        if (textBuffer.length() > 0)
            generateLineRenderers();

        drawVerticalLines(g2d);

        Vector2D drawPosition = drawPosition();

        g2d.drawString(SEPARATOR, drawPosition.x, drawPosition.y);

        Vector2D realPosition = drawPosition.add(offsetText);

        synchronized (renderLock) {
            for (LineRenderer lineRenderer : lineRenderers) {
                lineRenderer.render(g2d, realPosition);
                realPosition = realPosition.add(0, fontMetrics.getHeight());
            }
        }
    }

    private Vector2D drawPosition() {
        return position.subtract(getAnchor().x * getSize().x, getAnchor().y * getSize().y);
    }

    private void drawVerticalLines(Graphics2D g2d) {
        Vector2D drawPosition = drawPosition();
        for (int i = 0; i < (getSize().y / g2d.getFontMetrics().getHeight()) + 1; i++) {
            g2d.drawString("|", drawPosition.x, drawPosition.y);
            drawPosition.y += fontMetrics.getHeight();
        }
    }

    void clear() {
        synchronized (renderLock) {
            this.lineRenderers.clear();
            this.textBuffer.delete(0, textBuffer.length());
        }
    }

    void setFontMetrics(FontMetrics fontMetrics) {
        this.fontMetrics = fontMetrics;
        if (linesMax == -1) {
            linesMax = (int) ((getSize().y - offsetText.y) / fontMetrics.getHeight()) - 1;
        }
    }

    void generateLineRenderers() {

        // Separate lines
        for (String line : textBuffer.toString().split("[\n\r]")) {
            LineRenderer newLineRenderer = new LineRenderer();
            List<WordsRenderer> wordsRenderers = new ArrayList<>();

            for(String wordsGroup: line.split(";")) {
                wordsRenderers.addAll(WordsRenderer.parse(wordsGroup));
            }

            for (int wordIndex = 0; wordIndex < wordsRenderers.size(); wordIndex++) {
                newLineRenderer.add(wordsRenderers.get(wordIndex));
                boolean lineLengthExceeds = newLineRenderer.stringWidth(fontMetrics) - 20 > this.getSize().y;
                boolean isLastWord = (wordIndex == wordsRenderers.size() - 1);
                if (lineLengthExceeds || isLastWord) {
                    synchronized (renderLock) {
                        this.lineRenderers.add(newLineRenderer);
                        newLineRenderer = new LineRenderer();
                        if (lineRenderers.size() > linesMax) {
                            // Trim the begnining
                            for (int lineIndex = 0; lineIndex < (lineRenderers.size() - linesMax) && lineRenderers.size() > 0; lineIndex++) {
                                lineRenderers.remove(0);
                            }
                        }
                    }
                }
            }
        }

        textBuffer.delete(0, textBuffer.length());
    }

    void addText(String str) {
        textBuffer.append(str);
    }

    void addLine(String str) {
        textBuffer.append(str).append('\n');
    }
}
