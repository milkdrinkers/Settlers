package io.github.milkdrinkers.settlers.settler;

import net.citizensnpcs.api.npc.NPC;

import java.util.HashMap;

public class SettlerLookup {

    private static HashMap<Settler, NPC> settlerToNPCMap = new HashMap<>();
    private static HashMap<NPC, Settler> npcToSettlerMap = new HashMap<>();

    public static NPC lookupNPC(Settler settler) {
        return settlerToNPCMap.get(settler);
    }

    public static Settler lookupSettler(NPC npc) {
        return npcToSettlerMap.get(npc);
    }

    public static void addSettlerToLookup(NPC npc, Settler settler) {
        settlerToNPCMap.put(settler, npc);
        npcToSettlerMap.put(npc, settler);
    }

}
