package de.traumastudios.infernal.assets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AssetMetaData {
    private String name;
    private String path;
    private AssetType type;
}
