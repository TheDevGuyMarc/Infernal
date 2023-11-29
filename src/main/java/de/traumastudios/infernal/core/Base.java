package de.traumastudios.infernal.core;

public interface Base {
    void init(ApplicationConfig appConfig, WindowConfig windowConfig);
    void resize(int width, int height);
    void loop();
    void update();
    void onEvent();
    void run();
    void stop();
}
