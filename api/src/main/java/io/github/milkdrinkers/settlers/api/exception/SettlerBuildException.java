package io.github.milkdrinkers.settlers.api.exception;

import net.kyori.adventure.text.Component;

public class SettlerBuildException extends PluginException {
    public SettlerBuildException() {
    }

    public SettlerBuildException(String message) {
        super(message);
    }

    public SettlerBuildException(String message, Throwable cause) {
        super(message, cause);
    }

    public SettlerBuildException(Throwable cause) {
        super(cause);
    }

    public SettlerBuildException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SettlerBuildException(Component message) {
        super(message);
    }

    public SettlerBuildException(Component message, Throwable cause) {
        super(message, cause);
    }

    public SettlerBuildException(Component message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
