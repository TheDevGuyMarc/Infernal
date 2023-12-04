package de.traumastudios.infernal.core.networking.server;

public class AuthServer extends Server {
    private final String salt;

    public AuthServer(int serverID, String name, String host, int port, ServerType type, String salt) {
        super(serverID, name, host, port, type);
        this.salt = salt;
    }

    public void login() {

    }

    public void register() {

    }

    public void logout() {

    }

    public void verifyEmail() {

    }

    public void forgotPassword() {

    }

    private void sendEmail() {

    }

    private void hashData() {

    }
}
