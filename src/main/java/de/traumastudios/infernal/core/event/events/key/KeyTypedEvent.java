package de.traumastudios.infernal.core.event.events.key;

import de.traumastudios.infernal.core.event.EventCategory;
import de.traumastudios.infernal.core.event.EventType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class KeyTypedEvent extends KeyEvent {
    public KeyTypedEvent(UUID id, EventCategory category, EventType eventType, boolean isHandled, KeyCode keyCode) {
        super(id, category, eventType, isHandled, keyCode);
    }
}
