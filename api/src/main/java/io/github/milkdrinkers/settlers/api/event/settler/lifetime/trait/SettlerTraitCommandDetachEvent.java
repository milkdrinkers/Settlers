package io.github.milkdrinkers.settlers.api.event.settler.lifetime.trait;

import io.github.milkdrinkers.settlers.api.event.settler.AbstractSettlerEvent;
import io.github.milkdrinkers.settlers.api.settler.AbstractSettler;
import net.citizensnpcs.api.trait.Trait;
import org.bukkit.command.CommandSender;

public class SettlerTraitCommandDetachEvent extends AbstractSettlerEvent {
    private final Class<? extends Trait> traitClass;
    private final CommandSender sender;

    public SettlerTraitCommandDetachEvent(AbstractSettler settler, Class<? extends Trait> traitClass, CommandSender sender) {
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
