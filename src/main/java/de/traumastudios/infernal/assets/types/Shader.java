package de.traumastudios.infernal.assets.types;

import de.traumastudios.infernal.assets.Asset;
import de.traumastudios.infernal.assets.AssetMetaData;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Shader extends Asset {
    private String data;

    public Shader(UUID id, AssetMetaData metaData, String data) {
        super(id, metaData);
        this.data = data;
    }
}
