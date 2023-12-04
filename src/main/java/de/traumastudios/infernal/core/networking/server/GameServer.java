package de.traumastudios.infernal.core.networking.server;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
public class GameServer extends Server {
    private int maxRealms;
    private int maxPlayersPerRealm;
    private Map<Integer, Realm> realms;

    public GameServer(int serverID, String name, String host, int port, ServerType type) {
        super(serverID, name, host, port, type);
    }

    public void onPlayerConnect(int realmID, Player player) {

    }

    public void onPlayerDisconnect(int realmID, Player player) {

    }

    public void simulate()  {

    }
}
