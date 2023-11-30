package de.traumastudios.infernal.core.event.events.key;

import de.traumastudios.infernal.core.event.Event;
import de.traumastudios.infernal.core.event.EventCategory;
import de.traumastudios.infernal.core.event.EventType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class KeyEvent extends Event {
    private KeyCode keyCode;
    public KeyEvent(UUID id, EventCategory category, EventType eventType, boolean isHandled, KeyCode keyCode) {
        super(id, category, eventType, isHandled);
        this.keyCode = keyCode;
    }
}
