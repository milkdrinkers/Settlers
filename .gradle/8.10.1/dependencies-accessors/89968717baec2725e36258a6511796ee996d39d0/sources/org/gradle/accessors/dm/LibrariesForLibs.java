package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the {@code libs} extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final BstatsLibraryAccessors laccForBstatsLibraryAccessors = new BstatsLibraryAccessors(owner);
    private final CommandLibraryAccessors laccForCommandLibraryAccessors = new CommandLibraryAccessors(owner);
    private final CrateLibraryAccessors laccForCrateLibraryAccessors = new CrateLibraryAccessors(owner);
    private final PaperLibraryAccessors laccForPaperLibraryAccessors = new PaperLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

    /**
     * Dependency provider for <b>annotations</b> with <b>org.jetbrains:annotations</b> coordinates and
     * with version <b>26.0.0</b>
     * <p>
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getAnnotations() {
        return create("annotations");
    }

    /**
     * Dependency provider for <b>citizens</b> with <b>net.citizensnpcs:citizens-main</b> coordinates and
     * with version <b>2.0.35-SNAPSHOT</b>
     * <p>
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getCitizens() {
        return create("citizens");
    }

    /**
     * Dependency provider for <b>colorparser</b> with <b>com.github.milkdrinkers:colorparser</b> coordinates and
     * with version <b>2.0.3</b>
     * <p>
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getColorparser() {
        return create("colorparser");
    }

    /**
     * Group of libraries at <b>bstats</b>
     */
    public BstatsLibraryAccessors getBstats() {
        return laccForBstatsLibraryAccessors;
    }

    /**
     * Group of libraries at <b>command</b>
     */
    public CommandLibraryAccessors getCommand() {
        return laccForCommandLibraryAccessors;
    }

    /**
     * Group of libraries at <b>crate</b>
     */
    public CrateLibraryAccessors getCrate() {
        return laccForCrateLibraryAccessors;
    }

    /**
     * Group of libraries at <b>paper</b>
     */
    public PaperLibraryAccessors getPaper() {
        return laccForPaperLibraryAccessors;
    }

    /**
     * Group of versions at <b>versions</b>
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Group of bundles at <b>bundles</b>
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Group of plugins at <b>plugins</b>
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class BstatsLibraryAccessors extends SubDependencyFactory {

        public BstatsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>bukkit</b> with <b>org.bstats:bstats-bukkit</b> coordinates and
         * with version <b>3.1.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getBukkit() {
            return create("bstats.bukkit");
        }

    }

    public static class CommandLibraryAccessors extends SubDependencyFactory {

        public CommandLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>dev.jorel:commandapi-bukkit-shade</b> coordinates and
         * with version <b>9.5.3</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getApi() {
            return create("command.api");
        }

    }

    public static class CrateLibraryAccessors extends SubDependencyFactory {

        public CrateLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>com.github.milkdrinkers:crate-api</b> coordinates and
         * with version reference <b>crate</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getApi() {
            return create("crate.api");
        }

        /**
         * Dependency provider for <b>yaml</b> with <b>com.github.milkdrinkers:crate-yaml</b> coordinates and
         * with version reference <b>crate</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getYaml() {
            return create("crate.yaml");
        }

    }

    public static class PaperLibraryAccessors extends SubDependencyFactory {

        public PaperLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>io.papermc.paper:paper-api</b> coordinates and
         * with version <b>1.21.1-R0.1-SNAPSHOT</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getApi() {
            return create("paper.api");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>crate</b> with value <b>2.1.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getCrate() { return getVersion("crate"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {
        private final PluginPluginAccessors paccForPluginPluginAccessors = new PluginPluginAccessors(providers, config);
        private final RunPluginAccessors paccForRunPluginAccessors = new RunPluginAccessors(providers, config);

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Plugin provider for <b>shadow</b> with plugin id <b>io.github.goooler.shadow</b> and
         * with version <b>8.1.8</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getShadow() { return createPlugin("shadow"); }

        /**
         * Group of plugins at <b>plugins.plugin</b>
         */
        public PluginPluginAccessors getPlugin() {
            return paccForPluginPluginAccessors;
        }

        /**
         * Group of plugins at <b>plugins.run</b>
         */
        public RunPluginAccessors getRun() {
            return paccForRunPluginAccessors;
        }

    }

    public static class PluginPluginAccessors extends PluginFactory {

        public PluginPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Plugin provider for <b>plugin.yml</b> with plugin id <b>net.minecrell.plugin-yml.bukkit</b> and
         * with version <b>0.6.0</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getYml() { return createPlugin("plugin.yml"); }

    }

    public static class RunPluginAccessors extends PluginFactory {

        public RunPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Plugin provider for <b>run.paper</b> with plugin id <b>xyz.jpenilla.run-paper</b> and
         * with version <b>2.3.1</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getPaper() { return createPlugin("run.paper"); }

    }

}
