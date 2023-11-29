package de.traumastudios.infernal.core.event.events.mouse;

import de.traumastudios.infernal.core.event.Event;
import de.traumastudios.infernal.core.event.EventCategory;
import de.traumastudios.infernal.core.event.EventType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MouseButtonEvent extends Event {
    private MouseCode mouseCode;

    public MouseButtonEvent(UUID id, EventCategory category, EventType eventType, boolean isHandled, MouseCode mouseCode) {
        super(id, category, eventType, isHandled);
        this.mouseCode = mouseCode;
    }
}
