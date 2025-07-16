package io.github.milkdrinkers.settlers.trait.persistence.java.io;

import net.citizensnpcs.api.persistence.Persister;
import net.citizensnpcs.api.util.DataKey;

import java.io.File;

public class FileTypePersister implements Persister<File> {
    @Override
    public File create(DataKey dataKey) {
        return new File(dataKey.getString("", ""));
    }

    @Override
    public void save(File file, DataKey dataKey) {
        dataKey.setString("", file.getPath());
    }
}