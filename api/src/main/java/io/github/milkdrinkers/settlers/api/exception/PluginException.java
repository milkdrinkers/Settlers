package io.github.milkdrinkers.settlers.api.exception;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import java.io.Serial;

public class PluginException extends RuntimeException {
    private static final @Serial long serialVersionUID = 1L;

    private static final PlainTextComponentSerializer componentSerializer = PlainTextComponentSerializer.plainText();

    private static PlainTextComponentSerializer getComponentSerializer() {
        return componentSerializer;
    }

    public PluginException() {
        super();
    }

    public PluginException(String message) {
        super(message);
    }

    public PluginException(String message, Throwable cause) {
        super(message, cause);
    }

    public PluginException(Throwable cause) {
        super(cause);
    }

    protected PluginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public PluginException(Component message) {
        super(getComponentSerializer().serialize(message));
    }

    public PluginException(Component message, Throwable cause) {
        super(getComponentSerializer().serialize(message), cause);
    }

    protected PluginException(Component message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(getComponentSerializer().serialize(message), cause, enableSuppression, writableStackTrace);
    }
}
