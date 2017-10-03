//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import bases.GameObject;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;
import settings.Settings;

public class GameCanvas extends JPanel {
    private BufferedImage backBufferImage = new BufferedImage(1024, 768, 2);
    private Graphics2D backBufferGraphics;
    private final Object renderLock = new Object();

    public GameCanvas() {
        this.backBufferGraphics = (Graphics2D)this.backBufferImage.getGraphics();
        this.backBufferGraphics.setFont(Settings.DEFAULT_FONT);
        this.setBackground(Color.BLACK);
    }

    public void backRender() {
        synchronized(this.renderLock) {
            Graphics2D g2d = this.backBufferGraphics;
            GameObject.renderAll(g2d);
            repaint();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        synchronized(this.renderLock) {
            g.drawImage(this.backBufferImage, 0, 0, (ImageObserver)null);
        }
    }
}
