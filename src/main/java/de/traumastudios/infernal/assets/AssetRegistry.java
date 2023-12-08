package de.traumastudios.infernal.assets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssetRegistry {
    private Map<String, Asset> assets;

    public void register(Asset asset) {
        this.assets.put(asset.getMetaData().getName(), asset);
    }

    public void delete(Asset asset) {
        this.assets.remove(asset.getMetaData().getName(), asset);
    }

    public void clear() {
        this.assets.clear();
    }
}
