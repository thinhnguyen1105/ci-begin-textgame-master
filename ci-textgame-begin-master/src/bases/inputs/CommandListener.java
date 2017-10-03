package bases.inputs;

/**
 * Created by huynq on 7/28/17.
 */
public interface CommandListener {
    void onCommandFinished(String command);
    void commandChanged(String command);
}
