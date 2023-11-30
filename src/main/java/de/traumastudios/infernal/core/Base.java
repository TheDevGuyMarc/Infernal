package de.traumastudios.infernal.core;

import de.traumastudios.infernal.core.window.WindowConfig;

public interface Base {
    void init(ApplicationConfig appConfig, WindowConfig windowConfig);
    void resize(int width, int height);
    void loop();
    void update();
    void onEvent();
    void run();
    void stop();
}
