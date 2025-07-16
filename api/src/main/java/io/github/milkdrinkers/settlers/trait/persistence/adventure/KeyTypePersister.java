package io.github.milkdrinkers.settlers.trait.persistence.adventure;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;
import net.kyori.adventure.key.Key;
import org.intellij.lang.annotations.Subst;

public class KeyTypePersister implements Persister<Key> {
    @Override
    public Key create(DataKey dataKey) {
        final @Subst("minecraft") String namespace = dataKey.getString("namespace", "minecraft");
        final @Subst("not_empty") String value = dataKey.getString("value", "");
        return Key.key(namespace, value);
    }

    @Override
    public void save(Key key, DataKey dataKey) {
        dataKey.setString("namespace", key.namespace());
        dataKey.setString("value", key.value());
    }
}