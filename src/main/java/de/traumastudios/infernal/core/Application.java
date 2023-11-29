package de.traumastudios.infernal.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Application implements Base {
    private boolean isRunning;
    private boolean isMinimized;
    private ApplicationConfig appConfig;
    /*private WindowConfig windowConfig;
    private Window window;
    private Input inputHandler;
    private AssetManager assetManager;
    private LocalizationManager localizationManager;
    private Renderer renderer;
    private AudioEngine audioManager;
    private PhysicsEngine physicsManager;
    private UIManager uiManager;*/


    @Override
    public void init(ApplicationConfig appConfig, WindowConfig windowConfig) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void loop() {

    }

    @Override
    public void update() {

    }

    @Override
    public void onEvent() {

    }

    @Override
    public void run() {

    }

    @Override
    public void stop() {

    }
}
