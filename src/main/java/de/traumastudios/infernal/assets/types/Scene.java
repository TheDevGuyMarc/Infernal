package de.traumastudios.infernal.assets.types;

import de.traumastudios.infernal.assets.Asset;
import de.traumastudios.infernal.assets.AssetMetaData;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Scene extends Asset {
    private List<String[]> data;

    public Scene(UUID id, AssetMetaData metaData, List<String[]> data) {
        super(id, metaData);
        this.data = data;
    }
}
