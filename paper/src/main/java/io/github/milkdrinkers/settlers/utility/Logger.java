package io.github.milkdrinkers.settlers.utility;

import io.github.milkdrinkers.settlers.Settlers;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.jetbrains.annotations.NotNull;

public class Logger {
    /**
     * Get component logger. Shorthand for:
     *
     * @return the component logger {@link Settlers#getComponentLogger}.
     */
    @NotNull
    public static ComponentLogger get() {
        return Settlers.getInstance().getComponentLogger();
    }
}
