package io.github.milkdrinkers.settlers.api.event.settler;

import io.github.milkdrinkers.settlers.SettlersAPI;
import io.github.milkdrinkers.settlers.api.enums.RemoveReason;
import io.github.milkdrinkers.settlers.api.event.settler.lifecycle.SettlerRemoveEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.SettlerKnockbackEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.SettlerLinkToPlayerEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.command.SettlerCommandDispatchEvent;
import io.github.milkdrinkers.settlers.api.event.settler.lifetime.interact.damage.*;
import io.github.milkdrinkers.settlers.api.settler.Settler;
import net.citizensnpcs.api.event.*;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.ClickRedirectTrait;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;

public class CitizenListener implements Listener {
    @EventHandler
    public void onNPCRemove(NPCRemoveEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerRemoveEvent(SettlersAPI.getSettler(e.getNPC()), RemoveReason.UNKNOWN).callEvent();
    }

    @EventHandler
    public void onNPCRemoveByCommandSender(NPCRemoveByCommandSenderEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerRemoveEvent(SettlersAPI.getSettler(e.getNPC()), RemoveReason.COMMAND).callEvent();
    }

    @EventHandler
    public void onNPCCommandDispatch (NPCCommandDispatchEvent e) {
        if (e.isCancelled()) return;
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerCommandDispatchEvent(SettlersAPI.getSettler(e.getNPC()), e.getPlayer()).callEvent();
    }

    @EventHandler
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
    public void onNPCVehicleDamage(NPCVehicleDamageEvent e) {
        if (e.isCancelled()) return;
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        SettlerVehicleDamageEvent vehicleDamageEvent = new SettlerVehicleDamageEvent(SettlersAPI.getSettler(e.getNPC()), e.getEvent());
        vehicleDamageEvent.callEvent();
        if (vehicleDamageEvent.isCancelled())
            e.setCancelled(true);
    }

    @EventHandler
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
    public void onNPCLinkToPlayer(NPCLinkToPlayerEvent e) {
        if (!SettlersAPI.isSettler(e.getNPC())) return;

        new SettlerLinkToPlayerEvent(SettlersAPI.getSettler(e.getNPC()), e.getPlayer(), e.isAsynchronous());
    }
}
