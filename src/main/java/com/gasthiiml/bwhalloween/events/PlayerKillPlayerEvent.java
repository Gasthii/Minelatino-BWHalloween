package com.gasthiiml.bwhalloween.events;

import com.gasthiiml.bwhalloween.BWHalloween;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

@SuppressWarnings("ALL")
public class PlayerKillPlayerEvent implements Listener {

    private BWHalloween plugin;

    public PlayerKillPlayerEvent(BWHalloween plg) {
        plugin = plg;
    }

    @EventHandler
    public void onKill(de.marcely.bedwars.api.event.player.PlayerKillPlayerEvent e) {
        Player p = e.getKiller();
        ArrayList<Sound> sounds = new ArrayList<>();
        ArrayList<String> lore = new ArrayList<>();
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        for(String s : plugin.config.getStringList("Sounds.On-Kill.List")) {
            sounds.add(Sound.valueOf(s));
        }
        for(String s : plugin.config.getStringList("Pumpkin.Lore")) {
            lore.add(ChatColor
                    .translateAlternateColorCodes('&', s));
        }

        Random random = new Random();
        int randomSound = random.nextInt(sounds.size());
        Sound sound = sounds.get(randomSound);

        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
        gameProfile.getProperties().put("textures", new Property("textures",
                plugin.config.getString("Pumpkin.Skull-Value")));

        try {
            Method mtd = meta.getClass().getDeclaredMethod("profile", GameProfile.class);
            mtd.setAccessible(true);
            mtd.invoke(meta, gameProfile);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            ex.printStackTrace();
        }

        meta.setDisplayName(ChatColor
                .translateAlternateColorCodes('&',
                        plugin.config.getString("Pumpkin.Display-Name")));
        meta.setLore(lore);
        item.setItemMeta(meta);

        if(plugin.config.getBoolean("Sounds.On-Kill.Enabled").equals(true)) {
            p.playSound(p.getLocation(), sound, 3.0F, 0.5F);
        }
        if(plugin.config.getBoolean("Pumpkin.Enabled").equals(true)) {
            p.getInventory().addItem(item);
        }
    }
}
