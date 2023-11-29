package de.traumastudios.infernal.core.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventCategory {
    None(0),
    Application(1),
    Input(2),
    Keyboard(3),
    Mouse(4),
    MouseButton(5);

    private final int value;
}
