package de.traumastudios.infernal.core.networking.server;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ServerManager {
    private Map<Integer, Server> servers;

    public void add(int serverID, ServerType serverType, int maxRealms, int playerLimit, int port) {

    }

    public void remove(int serverID) {

    }

    public void monitor() {

    }
}
