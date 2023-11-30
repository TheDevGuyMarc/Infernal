package de.traumastudios.infernal.core.window;

import lombok.Getter;
import lombok.Setter;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.Configuration;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.system.MemoryUtil.memUTF8;

@Getter
@Setter
public class Window implements IWindow {
    private long windowHandle;
    private WindowConfig windowConfig;

    public Window(WindowConfig windowConfig) {
        this.windowConfig = windowConfig;
    }

    @Override
    public void init() {
        // Enable debug mode for OpenGL
        if (this.windowConfig.isDebugMode()) {
            Configuration.DEBUG.set(true); // Enables debug mode
            Configuration.DEBUG_LOADER.set(true); // Enables debug mode for shared libraries
            Configuration.DEBUG_MEMORY_ALLOCATOR.set(true); // Enables debug mode for memory allocator
            Configuration.DEBUG_STACK.set(true); // Enables debug mode for Memory stack
        }

        // Setup error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // Try to initialize GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Set GLFW window hints
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_SAMPLES, this.windowConfig.getAntiAliasingSamples());

        // Enables OpenGL debug mode
        if (this.windowConfig.isDebugMode()) {
            glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE);
        }

        // Create a new GLFW window
        this.windowHandle = glfwCreateWindow(
                this.windowConfig.getWidth(),
                this.windowConfig.getHeight(),
                this.windowConfig.getTitle(),
                NULL,
                NULL
        );

        if (this.windowHandle == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(this.windowHandle, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    this.windowHandle,
                    (vidMode.width() - pWidth.get(0)) / 2,
                    (vidMode.height() - pHeight.get(0)) / 2
            );
        }

        // Set the window icon
        if (this.windowConfig.getIconPath() != null) {
            setWindowIcon(this.windowConfig.getIconPath());
        }

        // Set the window cursor
        if (this.windowConfig.getCursorPath() != null) {
            setWindowCursor(this.windowConfig.getCursorPath());
        }

        // Enable window resizing
        glfwSetWindowSizeCallback(this.windowHandle, (win, width, height) -> resize(width, height));

        // Set context, swap interval to avoid screen tearing and make the window visible (render)
        glfwMakeContextCurrent(this.windowHandle);
        glfwSwapInterval(1);
        glfwShowWindow(this.windowHandle);
    }

    @Override
    public void update() {

    }

    @Override
    public void resize(int width, int height) {
        GL11.glViewport(0, 0, width, height);
    }

    /**
     * @TODO: Refactor this to use image / texture asset later because this will have the same image
     *        loading logic
     * @TODO: Add correct logging mechanisms here
     * @param path
     */
    @Override
    public void setWindowIcon(String path) {
        try (MemoryStack stack = stackPush()) {
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);

            ByteBuffer iconPathBuffer = memUTF8(path, true);
            ByteBuffer iconBuffer = stbi_load(iconPathBuffer, width, height, comp, 4);

            if (iconBuffer != null) {
                GLFWImage.Buffer icons = GLFWImage.malloc(1);
                icons.position(0)
                        .width(width.get(0))
                        .height(height.get(0))
                        .pixels(iconBuffer);

                glfwSetWindowIcon(windowHandle, icons);

                stbi_image_free(iconBuffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @TODO: Refactor this to use image / texture asset later because this will have the same image
     *        loading logic
     * @TODO: Add correct logging mechanisms here
     * @param path
     */
    @Override
    public void setWindowCursor(String path) {
        try (MemoryStack stack = stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);

            ByteBuffer cursorPathBuffer = memUTF8(path, true);
            ByteBuffer cursorImage = stbi_load(cursorPathBuffer, w, h, comp, 4);

            if (cursorImage != null) {
                GLFWImage cursorImg = GLFWImage.create();
                cursorImg.set(w.get(0), h.get(0), cursorImage);

                long cursor = glfwCreateCursor(cursorImg, 0, 0);
                glfwSetCursor(windowHandle, cursor);

                stbi_image_free(cursorImage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean shouldClose() {
        return glfwWindowShouldClose(this.windowHandle);
    }

    @Override
    public void destroy() {
        glfwFreeCallbacks(this.windowHandle);
        glfwDestroyWindow(this.windowHandle);

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
}
