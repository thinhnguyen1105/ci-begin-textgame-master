package bases.events;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huynq on 8/1/17.
 */
public class EventManager {
    private static List<EventListener> listeners = new ArrayList<>();

    public static void register(EventListener listener) {
        listeners.add(listener);
    }

    public static void unregister(EventListener listener) {
        listeners.remove(listener);
    }

    public static void push(EventType type, Object message) {
        for (EventListener listener : listeners) {
            listener.onEvent(type, message);
        }
    }

    public static void pushUIMessage(String message) {
        push(EventType.UI_MESSAGE, message);
    }

    public static void pushUIMessageNewLine(String message) {
        push(EventType.UI_MESSAGE, message + "\n");
    }

    public static void pushHelpMessage() {
        pushUIMessage("Command not found, type ;#FF0000help; to get support");
    }

    public static void pushClearUI() {
        push(EventType.UI_CLEAR, null);
    }
}
