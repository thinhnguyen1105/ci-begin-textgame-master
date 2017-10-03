package bases;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.*;
import java.lang.reflect.Type;
import java.util.Random;
import java.util.List;

/**
 * Created by huynq on 7/28/17.
 */
public class Utils {
    public static Font loadFont(String fontPath, float size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new java.io.File(fontPath))
                    .deriveFont(size)
                    .deriveFont(Font.BOLD);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String loadFileContent(String url) {
        File file = new File(url);
        FileInputStream fis = null;
        String content = null;
        try {
            fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            content = new String(data, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    public static void saveFileContent(String url, String content) {
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(url), "UTF-8"));
            out.write(content);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean fileExists(String url) {
        File varTmpDir = new File(url);
        return varTmpDir.exists();
    }

    public static <T> T choice(List<T> choices) {
        if (choices.size() == 0) return null;
        return choices.get(random.nextInt(choices.size()));
    }

    private static Random random = new Random();

    public static int rollDice() {
        return random.nextInt(6) + 1;
    }

    public static Font vnFont = new Font(Font.MONOSPACED, Font.PLAIN, 18);
}
