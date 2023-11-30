package de.traumastudios.infernal.core.event.events.window;

import de.traumastudios.infernal.core.event.Event;
import de.traumastudios.infernal.core.event.EventCategory;
import de.traumastudios.infernal.core.event.EventType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class WindowResizeEvent extends Event {
    private int width;
    private int height;

    public WindowResizeEvent(UUID id, EventCategory category, EventType eventType, boolean isHandled, int width, int height) {
        super(id, category, eventType, isHandled);
        this.width = width;
        this.height = height;
    }
}
