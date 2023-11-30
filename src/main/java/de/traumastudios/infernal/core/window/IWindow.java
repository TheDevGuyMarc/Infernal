package de.traumastudios.infernal.core.window;

public interface IWindow {
    void init();
    void update();
    void resize(int width, int height);
    void setWindowIcon(String path);
    void setWindowCursor(String path);
    boolean shouldClose();
    void destroy();
}
