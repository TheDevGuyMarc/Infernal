package de.traumastudios.infernal.core.event.events.key;

import de.traumastudios.infernal.core.event.EventCategory;
import de.traumastudios.infernal.core.event.EventType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class KeyPressedEvent extends KeyEvent {
    public KeyPressedEvent(UUID id, EventCategory category, EventType eventType, boolean isHandled, int keyCode) {
        super(id, category, eventType, isHandled, keyCode);
    }
}
