package io.github.milkdrinkers.settlers.trait.persistence.bukkit;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;
import org.bukkit.Material;

public class MaterialTypePersister implements Persister<Material> {
    @Override
    public Material create(DataKey dataKey) {
        final String materialName = dataKey.getString("", "AIR");
        return Material.valueOf(materialName.toUpperCase());
    }

    @Override
    public void save(Material material, DataKey dataKey) {
        dataKey.setString("", material.name());
    }
}