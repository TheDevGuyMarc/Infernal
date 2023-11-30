package de.traumastudios.infernal.core.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventType {
    None(0),
    KeyPressedEvent(1),
    KeyReleasedEvent(2),
    KeyTypedEvent(3),
    MouseButtonPressedEvent(4),
    MouseButtonReleasedEvent(5),
    MouseMovedEvent(6),
    MouseScrolledEvent(7),
    WindowMovedEvent(8),
    WindowResizeEvent(9),
    WindowFocusEvent(10),
    WindowLostFocusEvent(11),
    WindowCloseEvent(12),
    WindowDropEvent(13),
    AppUpdateEvent(14),
    AppRenderEvent(15),
    AppTickEvent(16);

    private final int value;
}
