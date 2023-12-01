package de.traumastudios.infernal.core.input;

import de.traumastudios.infernal.core.debug.InfernalLogger;
import de.traumastudios.infernal.core.event.Event;
import de.traumastudios.infernal.core.event.EventCategory;
import de.traumastudios.infernal.core.event.EventType;
import de.traumastudios.infernal.core.event.events.key.KeyEvent;
import de.traumastudios.infernal.core.event.events.mouse.MouseButtonEvent;
import de.traumastudios.infernal.core.event.events.mouse.MouseScrolledEvent;
import lombok.Getter;
import lombok.Setter;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

import java.util.Arrays;
import java.util.UUID;
import java.util.function.Consumer;

@Getter
@Setter
public class Input implements IInput {
    private InfernalLogger logger = InfernalLogger.getInstance("input.log");
    private final int KEY_COUNT = 350; // Maximum GLFW key codes
    private final int MOUSE_BUTTON_COUNT = 8; // Maximum GLFW mouse button codes

    private final boolean[] keys = new boolean[KEY_COUNT];
    private final boolean[] keysProcessed = new boolean[KEY_COUNT];

    private final boolean[] mouseButtons = new boolean[MOUSE_BUTTON_COUNT];
    private double mouseX, mouseY;
    private double scrollX, scrollY;

    private final Consumer<KeyEvent> keyEventListener;
    private final Consumer<MouseButtonEvent> mouseButtonEventListener;
    private final Consumer<MouseScrolledEvent> mouseScrollEventListener;

    public Input(Consumer<KeyEvent> keyEventListener, Consumer<MouseButtonEvent> mouseButtonEventListener, Consumer<MouseScrolledEvent> mouseScrollEventListener) {
        this.keyEventListener = keyEventListener;
        this.mouseButtonEventListener = mouseButtonEventListener;
        this.mouseScrollEventListener = mouseScrollEventListener;
    }

    @Override
    public void init(long window) {
        GLFW.glfwSetKeyCallback(window, new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                handleKeyEvent(key, action);
            }
        });

        GLFW.glfwSetMouseButtonCallback(window, new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                handleMouseButtonEvent(button, action);
            }
        });

        GLFW.glfwSetCursorPosCallback(window, (windowHandle, xpos, ypos) -> {
            this.mouseX = xpos;
            this.mouseY = ypos;
        });

        GLFW.glfwSetScrollCallback(window, new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double xoffset, double yoffset) {
                handleMouseScrollEvent(xoffset, yoffset);
            }
        });
    }

    @Override
    public void update() {
        Arrays.fill(this.keysProcessed, false);
    }

    @Override
    public boolean isKeyDown(int key) {
        this.logger.debug("Key: " + key + "Pressed: " + this.keys[key]);
        return this.keys[key];
    }

    @Override
    public boolean isKeyReleased(int key) {
        this.logger.debug("Key: " + key + "Released: " + this.keys[key]);
        return isKeyDown(key) && !keysProcessed[key];
    }

    @Override
    public boolean isKeyHeld(int key) {
        this.logger.debug("Key: " + key + "Held: " + this.keys[key]);
        return false;
    }

    @Override
    public boolean isMouseButtonDown(int button) {
        this.logger.debug("Button: " + button + "Pressed: " + this.mouseButtons[button]);
        return mouseButtons[button];
    }

    @Override
    public boolean isMouseButtonReleased(int button) {
        this.logger.debug("Button: " + button + "Released: " + this.mouseButtons[button]);
        return false;
    }

    @Override
    public boolean isMouseButtonHeld(int button) {
        this.logger.debug("Button: " + button + "Held: " + this.mouseButtons[button]);
        return false;
    }

    private void handleKeyEvent(int key, int action) {
        if (key < 0 || key >= KEY_COUNT) {
            this.logger.error("The key you tried to use is not supported!");
            return;
        }

        if (action == GLFW.GLFW_PRESS) {
            if (!keys[key]) {
                keys[key] = true;
                fireEvent(new KeyEvent(UUID.randomUUID(), EventCategory.Input, EventType.KeyPressedEvent, false, key));
            }
        } else if (action == GLFW.GLFW_RELEASE) {
            keys[key] = false;
            keysProcessed[key] = false;
            fireEvent(new KeyEvent(UUID.randomUUID(), EventCategory.Input, EventType.KeyReleasedEvent, false, key));
        }
    }

    private void handleMouseButtonEvent(int button, int action) {
        if (button < 0 || button >= MOUSE_BUTTON_COUNT) {
            return;
        }

        if (action == GLFW.GLFW_PRESS) {
            if (!mouseButtons[button]) {
                mouseButtons[button] = true;
                fireEvent(new MouseButtonEvent(UUID.randomUUID(), EventCategory.Input, EventType.MouseButtonPressedEvent, false, button));
            }
        } else if (action == GLFW.GLFW_RELEASE) {
            mouseButtons[button] = false;
            fireEvent(new MouseButtonEvent(UUID.randomUUID(), EventCategory.Input, EventType.MouseButtonReleasedEvent, false, button));
        }
    }

    private void handleMouseScrollEvent(double xoffset, double yoffset) {
        scrollX = xoffset;
        scrollY = yoffset;
        fireEvent(new MouseScrolledEvent(UUID.randomUUID(), EventCategory.Input, EventType.MouseScrolledEvent, false, xoffset, yoffset));
    }

    private void fireEvent(Event event) {
        if (event instanceof KeyEvent && keyEventListener != null) {
            keyEventListener.accept((KeyEvent) event);
        } else if (event instanceof MouseButtonEvent && mouseButtonEventListener != null) {
            mouseButtonEventListener.accept((MouseButtonEvent) event);
        } else if (event instanceof MouseScrolledEvent && mouseScrollEventListener != null) {
            mouseScrollEventListener.accept((MouseScrolledEvent) event);
        }
    }
}
