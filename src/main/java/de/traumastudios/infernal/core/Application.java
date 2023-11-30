package de.traumastudios.infernal.core;

import de.traumastudios.infernal.core.window.Window;
import de.traumastudios.infernal.core.window.WindowConfig;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lwjgl.Version;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLUtil;
import org.lwjgl.system.Callback;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;


@Getter
@Setter
@NoArgsConstructor
public class Application implements Base {
    private boolean isRunning;
    private boolean isMinimized;
    private ApplicationConfig appConfig;
    private WindowConfig windowConfig;
    private Window window;
    /*private Input inputHandler;
    private AssetManager assetManager;
    private LocalizationManager localizationManager;
    private Renderer renderer;
    private AudioEngine audioManager;
    private PhysicsEngine physicsManager;
    private UIManager uiManager;*/


    @Override
    public void init(ApplicationConfig appConfig, WindowConfig windowConfig) {
        // Set application config
        this.appConfig = appConfig;
        this.windowConfig = windowConfig;

        // Initialize application window
        this.window = new Window(this.windowConfig);
        this.window.init();

        // Initialize input

        // Initialize other modules

        this.isRunning = true;
    }

    @Override
    public void resize(int width, int height) {
        this.window.resize(width, height);
    }

    @Override
    public void loop() {
        if (this.appConfig.getRenderingEngine() == "openGL") {
            GL.createCapabilities();

            if (this.windowConfig.isDebugMode()) {
                Callback debugProcess = GLUtil.setupDebugMessageCallback();

                if (debugProcess != null) {
                    debugProcess.free();
                }
            }

            GL11.glClearColor(1.0f, 1.0f, 0.0f, 0.0f);
        }

        while (!glfwWindowShouldClose(this.window.getWindowHandle())) {
            // Handle input

            // Update every component

            // Render graphics
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glfwSwapBuffers(this.window.getWindowHandle());

            glfwPollEvents();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void onEvent() {

    }

    @Override
    public void run() {
        System.out.println("LWJGL version: " + Version.getVersion() + "!");

        init(this.appConfig, this.windowConfig);

        System.out.println("Initialized GLFW: " + glfwGetVersionString().toLowerCase());

        loop();

        System.out.println("Stopping the engine");

        stop();
    }

    @Override
    public void stop() {
        this.window.destroy();
    }
}
