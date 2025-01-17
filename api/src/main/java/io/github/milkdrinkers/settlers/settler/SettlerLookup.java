package io.github.milkdrinkers.settlers.settler;

import net.citizensnpcs.api.npc.NPC;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class SettlerLookup {
    private static final HashMap<Settler, NPC> settlerToNPCMap = new HashMap<>();
    private static final HashMap<NPC, Settler> npcToSettlerMap = new HashMap<>();

    public static @Nullable NPC lookup(Settler settler) {
        return settlerToNPCMap.get(settler);
    }

    public static @Nullable Settler lookup(NPC npc) {
        return npcToSettlerMap.get(npc);
    }

    public static void addSettlerToLookup(NPC npc, Settler settler) {
        settlerToNPCMap.put(settler, npc);
        npcToSettlerMap.put(npc, settler);
    }

    public static void clear() {
        settlerToNPCMap.clear();
        npcToSettlerMap.clear();
    }
}
