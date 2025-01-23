package io.github.milkdrinkers.settlers.api.event.settler.lifetime.trait;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import net.citizensnpcs.api.trait.Trait;
import org.bukkit.command.CommandSender;

public class SettlerTraitCommandAttachEvent extends AbstractSettlerEvent {
    private final Class<? extends Trait> traitClass;
    private final CommandSender sender;

    public SettlerTraitCommandAttachEvent(Settler settler, Class<? extends Trait> traitClass, CommandSender sender) {
        super(settler);
        this.traitClass = traitClass;
        this.sender = sender;
    }

    public Class<? extends Trait> getTraitClass() {
        return traitClass;
    }

    public CommandSender getSender() {
        return sender;
    }
}
