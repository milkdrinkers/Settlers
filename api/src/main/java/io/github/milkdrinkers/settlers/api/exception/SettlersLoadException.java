package io.github.milkdrinkers.settlers.api.exception;

import net.kyori.adventure.text.Component;

public class SettlersLoadException extends PluginException {
    public SettlersLoadException() {
    }

    public SettlersLoadException(String message) {
        super(message);
    }

    public SettlersLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public SettlersLoadException(Throwable cause) {
        super(cause);
    }

    public SettlersLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SettlersLoadException(Component message) {
        super(message);
    }

    public SettlersLoadException(Component message, Throwable cause) {
        super(message, cause);
    }

    public SettlersLoadException(Component message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
