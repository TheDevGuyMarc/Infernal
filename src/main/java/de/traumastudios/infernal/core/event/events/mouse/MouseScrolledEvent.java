package de.traumastudios.infernal.core.event.events.mouse;

import de.traumastudios.infernal.core.event.Event;
import de.traumastudios.infernal.core.event.EventCategory;
import de.traumastudios.infernal.core.event.EventType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MouseScrolledEvent extends Event {
    private double x;
    private double y;

    public MouseScrolledEvent(UUID id, EventCategory category, EventType eventType, boolean isHandled, double x, double y) {
        super(id, category, eventType, isHandled);
        this.x = x;
        this.y = y;
    }
}
