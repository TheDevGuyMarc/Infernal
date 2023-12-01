package de.traumastudios.infernal.core.event;

import de.traumastudios.infernal.core.debug.Profiler;

import java.util.*;
import java.util.function.Consumer;

public class EventDispatcher {
    private Profiler profiler = new Profiler();
    private final Map<EventType, Map<EventCategory, List<Consumer<Event>>>> listeners = new HashMap<>();
    private final Queue<Event> eventQueue = new LinkedList<>();

    /**
     * Adds a new event listener to the list
     * @param eventType
     * @param eventCategory
     * @param listener
     */
    public void addListener(EventType eventType, EventCategory eventCategory, Consumer<Event> listener) {
        listeners
                .computeIfAbsent(eventType, key -> new HashMap<>())
                .computeIfAbsent(eventCategory, key -> new ArrayList<>())
                .add(listener);
    }

    /**
     * Adds a new event to the queue for later processing
     * @param event
     */
    public void enqueue(Event event) {
        eventQueue.offer(event);
    }

    /**
     * Processes the events in the queue one by one in insertion order
     */
    public void processEvents() {
        while (!eventQueue.isEmpty()) {
            Event event = eventQueue.poll();
            dispatch(event);
        }
    }

    /**
     * Dispatches an already processed event
     * @param event
     */
    private void dispatch(Event event) {
        EventType eventType = event.getEventType();
        EventCategory eventCategory = event.getCategory();

        this.profiler.start("EventDispatch");

        Map<EventCategory, List<Consumer<Event>>> categoryListeners = listeners.get(eventType);
        if (categoryListeners != null) {
            List<Consumer<Event>> listenersByCategory = categoryListeners.getOrDefault(eventCategory, new ArrayList<>());
            listenersByCategory.forEach(listener -> {
                if (!event.isHandled()) {
                    listener.accept(event);
                    event.setHandled(true);
                }
            });
        }

        this.profiler.stop("EventDispatch");
    }
}
