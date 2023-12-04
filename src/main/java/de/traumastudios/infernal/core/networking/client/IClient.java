package de.traumastudios.infernal.core.networking.client;

public interface IClient {
    void connect(String host, int port, String username, String password);
    void disconnect();
    void monitor();
}
