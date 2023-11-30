package de.traumastudios.infernal.core.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Event {
    private UUID id;
    private EventCategory category;
    private EventType eventType;
    private boolean isHandled;

    public String toString() {
        return "Event{" +
                "id=" + this.id.toString() + "," +
                "category=" + this.category.toString() + "," +
                "type=" + this.eventType.toString() + "," +
                "state=" + this.isHandled +
                "}";
    }
}
