package bases.events;

/**
 * Created by huynq on 8/1/17.
 */
public interface EventListener {
    void onEvent(EventType type, Object message);
}
