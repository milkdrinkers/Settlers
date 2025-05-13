package io.github.milkdrinkers.settlers.api.settler;

import io.github.milkdrinkers.settlers.api.SettlersAPI;
import io.github.milkdrinkers.settlers.api.enums.CreateReason;
import io.github.milkdrinkers.settlers.api.enums.SettlerType;
import io.github.milkdrinkers.settlers.api.event.settler.lifecycle.SettlerCreateEvent;
import io.github.milkdrinkers.settlers.api.exception.SettlerBuildException;
import io.github.milkdrinkers.settlers.api.trait.*;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

import static org.bukkit.entity.EntityType.PLAYER;

/**
 * A builder for creating new settlers.
 */
public class SettlerBuilder {
    private NPC npc = null;
    private SettlerType type = null;

    // Creation fields
    private String name = null;
    private Location location = null;
    private UUID uuid = null;
    private Integer id = null;
    private boolean ephemeral = false;

    public SettlerBuilder setNpc(@NotNull NPC npc) {
        this.npc = npc;
        return this;
    }

    public SettlerBuilder setName(@NotNull String name) {
        this.name = name;
        return this;
    }

    public SettlerBuilder setType(@NotNull SettlerType type) {
        this.type = type;
        return this;
    }

    public SettlerBuilder setUuid(@NotNull UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public SettlerBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public SettlerBuilder setLocation(@NotNull Location location) {
        this.location = location;
        return this;
    }

    public SettlerBuilder setEphemeral(boolean ephemeral) {
        this.ephemeral = ephemeral;
        return this;
    }

    private void checkFields() throws SettlerBuildException {
//        throw new SettlerBuildException(Component.text("Failed to build new settler!"));
    }

    private NPC createNPC(NPCRegistry registry, @Nullable String name, @Nullable Location location, @Nullable Integer id, @Nullable UUID uuid) throws SettlerBuildException {
        if (name == null)
            throw new SettlerBuildException(Component.text("Failed to create new settler as name is null!"));

        if (uuid != null && id != null)
            return registry.createNPC(PLAYER, uuid, id, name);

        if (location != null)
            return registry.createNPC(PLAYER, name, location);

        return registry.createNPC(PLAYER, name);
    }

    private void register(SettlerType type) throws SettlerBuildException {
        if (npc != null)
            return;

        if (type == null)
            throw new SettlerBuildException(Component.text("Failed to create new settler, missing field type!"));

        switch (type) {
            case COMPANION -> {
                if (!ephemeral) {
                    setNpc(createNPC(SettlersAPI.getRegistryCompanion(), name, location, id, uuid));
                } else {
                    setNpc(createNPC(SettlersAPI.getRegistryEphemeralCompanion(), name, location, id, uuid));
                }
                npc.addTrait(CompanionTrait.class);
            }
            case GUARD -> {
                if (!ephemeral) {
                    setNpc(createNPC(SettlersAPI.getRegistryGuard(), name, location, id, uuid));
                } else {
                    setNpc(createNPC(SettlersAPI.getRegistryEphemeralGuard(), name, location, id, uuid));
                }
                npc.addTrait(GuardTrait.class);
            }
            case NATION -> {
                if (!ephemeral) {
                    setNpc(createNPC(SettlersAPI.getRegistryNation(), name, location, id, uuid));
                } else {
                    setNpc(createNPC(SettlersAPI.getRegistryEphemeralTown(), name, location, id, uuid));
                }
                npc.addTrait(NationFolkTrait.class);
            }
            case TOWN -> {
                if (!ephemeral) {
                    setNpc(createNPC(SettlersAPI.getRegistryTown(), name, location, id, uuid));
                } else {
                    setNpc(createNPC(SettlersAPI.getRegistryEphemeralNation(), name, location, id, uuid));
                }
                npc.addTrait(TownFolkTrait.class);
            }
        }
        npc.addTrait(SettlerTrait.class);
    }

    public AbstractSettler create() throws SettlerBuildException {
        checkFields();

        final AbstractSettler settler = switch (type) {
            case COMPANION -> new Companion(npc);
            case GUARD -> new Guard(npc);
            case NATION -> new Nationfolk(npc);
            case TOWN -> new Townfolk(npc);
        };

        SettlersAPI.getImplementation().getLookupHandler().getHolder().addToLookupTables(settler); // Add to lookup tables
        new SettlerCreateEvent(settler, CreateReason.UNKNOWN).callEvent();

        return settler;
    }

    public Companion createCompanion() throws SettlerBuildException {
        type = SettlerType.COMPANION;
        checkFields();
        register(type);

        final Companion settler = new Companion(npc);

        SettlersAPI.getImplementation().getLookupHandler().getHolder().addToLookupTables(settler); // Add to lookup tables
        new SettlerCreateEvent(settler, CreateReason.UNKNOWN).callEvent();

        return settler;
    }

    public Guard createGuard() throws SettlerBuildException {
        type = SettlerType.GUARD;
        checkFields();
        register(type);

        final Guard settler = new Guard(npc);

        SettlersAPI.getImplementation().getLookupHandler().getHolder().addToLookupTables(settler); // Add to lookup tables
        new SettlerCreateEvent(settler, CreateReason.UNKNOWN).callEvent();

        return settler;
    }

    public Townfolk createTownfolk() throws SettlerBuildException {
        type = SettlerType.TOWN;
        checkFields();
        register(type);

        final Townfolk settler = new Townfolk(npc);

        SettlersAPI.getImplementation().getLookupHandler().getHolder().addToLookupTables(settler); // Add to lookup tables
        new SettlerCreateEvent(settler, CreateReason.UNKNOWN).callEvent();

        return settler;
    }

    public Nationfolk createNationfolk() throws SettlerBuildException {
        type = SettlerType.NATION;
        checkFields();
        register(type);

        final Nationfolk settler = new Nationfolk(npc);

        SettlersAPI.getImplementation().getLookupHandler().getHolder().addToLookupTables(settler); // Add to lookup tables
        new SettlerCreateEvent(settler, CreateReason.UNKNOWN).callEvent();

        return settler;
    }
}