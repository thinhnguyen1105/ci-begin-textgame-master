package bases.uis;


import settings.Settings;

import java.awt.*;

/**
 * Created by huynq on 8/2/17.
 */
public class StatScreen extends TextView {
    public StatScreen() {
        super();
        offsetText.set(20, Settings.STATS_SCREEN_HEIGHT / 2);
    }

    @Override
    public void render(Graphics2D g2d) {
        clear();
        super.render(g2d);
    }
}
