package io.github.milkdrinkers.settlers.api.event.settler;

import io.github.milkdrinkers.settlers.SettlersAPI;
import io.github.milkdrinkers.settlers.api.enums.RemoveReason;
import io.github.milkdrinkers.settlers.api.event.settler.lifecycle.SettlerRemoveEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.SettlerCloneEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.SettlerRenameEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.SettlerSeenByPlayerEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.SettlerKnockbackEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.selection.SettlerLinkToPlayerEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.SettlerLookCloseChangeTargetEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.SettlerOpenEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.command.SettlerCommandDispatchEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.damage.*;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.movement.*;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.selection.SettlerSelectEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.selection.SettlerUnlinkFromPlayerEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.spawning.SettlerDeathEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.spawning.SettlerDespawnEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.spawning.SettlerNeedsRespawnEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.spawning.SettlerSpawnEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.trait.SettlerAddTraitEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.trait.SettlerRemoveTraitEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.trait.SettlerTraitCommandAttachEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.trait.SettlerTraitCommandDetachEvent;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import io.github.milkdrinkers.settlers.api.settler.SettlerBuilder;
import net.citizensnpcs.NPCNeedsRespawnEvent;
import net.citizensnpcs.api.event.*;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;

public class CitizenListener implements Listener {
    @EventHandler
    @SuppressWarnings("unused")
    public void onNPCRemove(NPCRemoveEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerRemoveEvent(SettlersAPI.getSettler(e.getNPC()), RemoveReason.UNKNOWN).callEvent();
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onNPCRemoveByCommandSender(NPCRemoveByCommandSenderEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerRemoveEvent(SettlersAPI.getSettler(e.getNPC()), RemoveReason.COMMAND).callEvent();
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onNPCCommandDispatch (NPCCommandDispatchEvent e) {
        if (e.isCancelled()) return;
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerCommandDispatchEvent(SettlersAPI.getSettler(e.getNPC()), e.getPlayer()).callEvent();
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onEntityCombust(EntityCombustEvent e) {
        if (e.isCancelled()) return;
        if (!SettlersAPI.isSettler(e.getEntity())) return;

        SettlerCombustEvent settlerCombustEvent;

        if (e instanceof EntityCombustByBlockEvent)
            settlerCombustEvent = new SettlerCombustByBlockEvent(SettlersAPI.getSettler(e.getEntity()), (EntityCombustByBlockEvent) e);
        else if (e instanceof EntityCombustByEntityEvent)
            settlerCombustEvent = new SettlerCombustByEntityEvent(SettlersAPI.getSettler(e.getEntity()), (EntityCombustByEntityEvent) e);
        else
            settlerCombustEvent = new SettlerCombustEvent(SettlersAPI.getSettler(e.getEntity()), e);

        settlerCombustEvent.callEvent();
        if (settlerCombustEvent.isCancelled())
            e.setCancelled(true);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onEntityDamaged(EntityDamageEvent e) {
        if (e.isCancelled()) return;

        Settler settler = SettlersAPI.getSettler(e.getEntity());

        if (settler == null) {
            if (e instanceof EntityDamageByEntityEvent) {
                if (SettlersAPI.isSettler(((EntityDamageByEntityEvent) e).getDamager())) {
                    settler = SettlersAPI.getSettler(((EntityDamageByEntityEvent) e).getDamager());
                    if (settler != null) {
                        e.setCancelled(settler.getNpc().data().get(NPC.Metadata.DAMAGE_OTHERS, true));

                        SettlerDamageEntityEvent damageEvent = new SettlerDamageEntityEvent(settler, (EntityDamageByEntityEvent) e);
                        damageEvent.callEvent();
                        if (damageEvent.isCancelled())
                            e.setCancelled(true);
                    }
                }
            }
            return;
        }

        if (settler.getNpc().isProtected()) return;

        if (e instanceof EntityDamageByEntityEvent) {
            SettlerDamagedByEntityEvent damageEvent = new SettlerDamagedByEntityEvent(settler, (EntityDamageByEntityEvent) e);
            damageEvent.callEvent();
            if (!damageEvent.isCancelled() || !(damageEvent.getDamager() instanceof Player)) return;
        } else {
            SettlerDamagedEvent damagedEvent;
            if (e instanceof EntityDamageByBlockEvent)
                damagedEvent = new SettlerDamagedByBlockEvent(settler, (EntityDamageByBlockEvent) e);
            else
                damagedEvent = new SettlerDamagedEvent(settler, e);

            damagedEvent.callEvent();
            if (damagedEvent.isCancelled())
                e.setCancelled(true);
        }
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onNPCVehicleDamage(NPCVehicleDamageEvent e) {
        if (e.isCancelled()) return;
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        SettlerVehicleDamageEvent vehicleDamageEvent = new SettlerVehicleDamageEvent(SettlersAPI.getSettler(e.getNPC()), e.getEvent());
        vehicleDamageEvent.callEvent();
        if (vehicleDamageEvent.isCancelled())
            e.setCancelled(true);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onNPCKnockback(NPCKnockbackEvent e) {
        if (e.isCancelled()) return;
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        SettlerKnockbackEvent settlerKnockbackEvent = new SettlerKnockbackEvent(SettlersAPI.getSettler(e.getNPC()),
            e.getStrength(),
            e.getKnockbackVector(),
            e.getKnockingBackEntity());

        settlerKnockbackEvent.callEvent();
        if (settlerKnockbackEvent.isCancelled())
            e.setCancelled(true);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onNPCLinkToPlayer(NPCLinkToPlayerEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerLinkToPlayerEvent(SettlersAPI.getSettler(e.getNPC()), e.getPlayer(), e.isAsynchronous());
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onSeen(NPCSeenByPlayerEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        final boolean cancelled = new SettlerSeenByPlayerEvent(SettlersAPI.getSettler(e.getNPC()), e.getPlayer()).callEvent();
        e.setCancelled(cancelled);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onRename(NPCRenameEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        final boolean cancelled = new SettlerRenameEvent(SettlersAPI.getSettler(e.getNPC()), e).callEvent();
        e.setNewName(e.getOldName());
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onClone(NPCCloneEvent e) {
        final Settler settler = switch (SettlersAPI.getSettlerType(e.getClone())) {
            case COMPANION -> new SettlerBuilder()
                .setNpc(e.getNPC())
                .createCompanion();
            case GUARD -> new SettlerBuilder()
                .setNpc(e.getNPC())
                .createGuard();
            case NATION -> new SettlerBuilder()
                .setNpc(e.getNPC())
                .createNationfolk();
            case TOWN -> new SettlerBuilder()
                .setNpc(e.getNPC())
                .createTownfolk();
            case null -> null;
        };

        if (settler == null)
            return;

        new SettlerCloneEvent(SettlersAPI.getSettler(e.getNPC()), settler).callEvent();
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onTrait(NPCAddTraitEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerAddTraitEvent(SettlersAPI.getSettler(e.getNPC()), e.getTrait()).callEvent();
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onTrait(NPCRemoveTraitEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerRemoveTraitEvent(SettlersAPI.getSettler(e.getNPC()), e.getTrait()).callEvent();
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onTrait(NPCTraitCommandAttachEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerTraitCommandAttachEvent(SettlersAPI.getSettler(e.getNPC()), e.getTraitClass(), e.getCommandSender()).callEvent();
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onTrait(NPCTraitCommandDetachEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerTraitCommandDetachEvent(SettlersAPI.getSettler(e.getNPC()), e.getTraitClass(), e.getCommandSender()).callEvent();
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onSpawn(NPCSpawnEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        final boolean cancelled = new SettlerSpawnEvent(SettlersAPI.getSettler(e.getNPC()), e.getLocation(), e.getReason()).callEvent();
        e.setCancelled(cancelled);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onDespawn(NPCDespawnEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        final boolean cancelled = new SettlerDespawnEvent(SettlersAPI.getSettler(e.getNPC()), e.getReason()).callEvent();
        e.setCancelled(cancelled);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onRespawn(NPCNeedsRespawnEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerNeedsRespawnEvent(SettlersAPI.getSettler(e.getNPC()), e.getSpawnLocation()).callEvent();
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onDeath(NPCDeathEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerDeathEvent(SettlersAPI.getSettler(e.getNPC()), e).callEvent();
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onUnlink(NPCUnlinkFromPlayerEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerUnlinkFromPlayerEvent(SettlersAPI.getSettler(e.getNPC()), e.getPlayer()).callEvent();
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onSelect(NPCSelectEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerSelectEvent(SettlersAPI.getSettler(e.getNPC()), e.getSelector()).callEvent();
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onTeleport(NPCTeleportEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        final boolean cancelled = new SettlerTeleportEvent(SettlersAPI.getSettler(e.getNPC()), e.getFrom(), e.getTo()).callEvent();
        e.setCancelled(cancelled);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onPush(NPCPushEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        final boolean cancelled = new SettlerPushEvent(SettlersAPI.getSettler(e.getNPC()), e.getCollisionVector(), e.getPushedBy()).callEvent();
        e.setCancelled(cancelled);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onPistonPush(NPCPistonPushEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        final boolean cancelled = new SettlerPistonPushEvent(SettlersAPI.getSettler(e.getNPC())).callEvent();
        e.setCancelled(cancelled);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onMove(NPCMoveEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        final boolean cancelled = new SettlerMoveEvent(SettlersAPI.getSettler(e.getNPC()), e).callEvent();
        e.setCancelled(cancelled);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onCollision(NPCCollisionEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerCollisionEvent(SettlersAPI.getSettler(e.getNPC()), e.getCollidedWith()).callEvent();
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onLookCloseChangeTarget(NPCLookCloseChangeTargetEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerLookCloseChangeTargetEvent(SettlersAPI.getSettler(e.getNPC()), e.getPreviousTarget(), e.getNewTarget());
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onNPCOpenDoor(NPCOpenDoorEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        final boolean cancelled = new SettlerOpenEvent(SettlersAPI.getSettler(e.getNPC()), e.getDoor()).callEvent();
        e.setCancelled(cancelled);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onNPCOpenGate(NPCOpenGateEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        final boolean cancelled = new SettlerOpenEvent(SettlersAPI.getSettler(e.getNPC()), e.getGate()).callEvent();
        e.setCancelled(cancelled);
    }
}
