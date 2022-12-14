package com.gasthiiml.bwhalloween;

import com.gasthiiml.bwhalloween.commands.GiveCommand;
import com.gasthiiml.bwhalloween.commands.ReloadCommand;
import com.gasthiiml.bwhalloween.events.ArenaBedBreakEvent;
import com.gasthiiml.bwhalloween.events.BlockPlaceEvent;
import com.gasthiiml.bwhalloween.events.PlayerInteractEvent;
import com.gasthiiml.bwhalloween.events.PlayerKillPlayerEvent;
import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("ALL")
public class BWHalloween extends JavaPlugin {

    public YamlDocument config;

    public void onEnable() {
        if(Bukkit.getPluginManager().getPlugin("MBedwars") != null) {
            final int supportedAPIVersion = 14;

            try {
                Class apiClass = Class.forName("de.marcely.bedwars.api.BedwarsAPI");
                int apiVersion = (int) apiClass.getMethod("getAPIVersion").invoke(null);

                if (apiVersion < supportedAPIVersion)
                    throw new IllegalStateException();
            } catch(Exception e) {
                this.getLogger().warning("Your MBedwars version is not supported. Supported version: 5.0.14 or higher!");
                Bukkit.getPluginManager().disablePlugin(this);
                return;
            }

            try {
                config = YamlDocument.create(new File(getDataFolder(), "config.yml"), getResource("config.yml"),
                        GeneralSettings.DEFAULT, LoaderSettings.builder().setAutoUpdate(true).build(),
                        DumperSettings.DEFAULT, UpdaterSettings.builder().setVersioning(new BasicVersioning("config-version")).build());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Bukkit.getPluginManager().registerEvents(new PlayerKillPlayerEvent(this), this);
            Bukkit.getPluginManager().registerEvents(new ArenaBedBreakEvent(this), this);
            Bukkit.getPluginManager().registerEvents(new PlayerInteractEvent(this), this);
            Bukkit.getPluginManager().registerEvents(new BlockPlaceEvent(this), this);
            this.getCommand("mlhr").setExecutor(new ReloadCommand(this));
            this.getCommand("mlhg").setExecutor(new GiveCommand(this));
        }
        else {
            this.getLogger().warning("MBedwars is not enabled!");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }
}
