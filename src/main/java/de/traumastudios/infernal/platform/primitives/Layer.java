package de.traumastudios.infernal.platform.primitives;

public interface Layer {
    void onAttach();
    void onDetach();
    void onUpdate();
    void onEvent();
    void onRender();
}
