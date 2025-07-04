package io.github.milkdrinkers.settlers.api.exception;

import net.kyori.adventure.text.Component;

import java.io.Serial;

public class SettlerSpawnException extends PluginException {
    private static final @Serial long serialVersionUID = 1L;

    public SettlerSpawnException() {
    }

    public SettlerSpawnException(String message) {
        super(message);
    }

    public SettlerSpawnException(String message, Throwable cause) {
        super(message, cause);
    }

    public SettlerSpawnException(Throwable cause) {
        super(cause);
    }

    public SettlerSpawnException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SettlerSpawnException(Component message) {
        super(message);
    }

    public SettlerSpawnException(Component message, Throwable cause) {
        super(message, cause);
    }

    public SettlerSpawnException(Component message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
