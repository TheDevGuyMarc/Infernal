package de.traumastudios.infernal.core.event.events.application;

import de.traumastudios.infernal.core.event.Event;
import de.traumastudios.infernal.core.event.EventCategory;
import de.traumastudios.infernal.core.event.EventType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AppUpdateEvent extends Event {
    public AppUpdateEvent(UUID id, EventCategory category, EventType eventType, boolean isHandled) {
        super(id, category, eventType, isHandled);
    }
}
