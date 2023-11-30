package de.traumastudios.infernal.core.event.events.mouse;

import de.traumastudios.infernal.core.event.EventCategory;
import de.traumastudios.infernal.core.event.EventType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MouseButtonPressedEvent extends MouseButtonEvent {
    public MouseButtonPressedEvent(UUID id, EventCategory category, EventType eventType, boolean isHandled, MouseCode mouseCode) {
        super(id, category, eventType, isHandled, mouseCode);
    }
}
