package io.github.milkdrinkers.settlers.settler;

import net.citizensnpcs.api.npc.NPC;

public class Settler {

    private NPC npc;

    public Settler(NPC npc) {
        this.npc = npc;
        SettlerLookup.addSettlerToLookup(npc, this);
    }

    public NPC getNpc() {
        return npc;
    }

}
