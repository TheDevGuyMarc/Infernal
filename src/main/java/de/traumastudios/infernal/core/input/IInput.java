package de.traumastudios.infernal.core.input;

public interface IInput {
    void init(long windowHandle);
    void update();
    boolean isKeyDown(int key);
    boolean isKeyReleased(int key);
    boolean isKeyHeld(int key);
    boolean isMouseButtonDown(int button);
    boolean isMouseButtonReleased(int button);
    boolean isMouseButtonHeld(int button);
}
