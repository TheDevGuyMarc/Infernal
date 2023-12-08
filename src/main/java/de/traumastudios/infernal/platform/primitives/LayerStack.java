package de.traumastudios.infernal.platform.primitives;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class LayerStack {
    private final List<Layer> layers;

    public LayerStack() {
        this.layers = new ArrayList<>();
    }

    public void pushLayer(Layer layer) {
        this.layers.add(layer);
    }

    public void popLayer(Layer layer) {
        this.layers.remove(layer);
    }

    public void clear() {
        this.layers.clear();
    }

    public void onUpdate() {

    }

    public void onEvent() {

    }
}
