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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

@SuppressWarnings("ALL")
public class ArenaBedBreakEvent implements Listener {

    private BWHalloween plugin;

    public ArenaBedBreakEvent(BWHalloween plg) {
        plugin = plg;
    }

    @EventHandler
    public void onBedBreak(de.marcely.bedwars.api.event.arena.ArenaBedBreakEvent e) {
        Player p = e.getPlayer();
        ArrayList<Sound> sounds = new ArrayList<>();
        ArrayList<String> lore = new ArrayList<>();
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        for(String s : plugin.config.getStringList("Sounds.On-Bed-Break.List")) {
            sounds.add(Sound.valueOf(s));
        }
        for(String s : plugin.config.getStringList("Candy.Lore")) {
            lore.add(ChatColor
                    .translateAlternateColorCodes('&', s));
        }

        GameProfile profile = new GameProfile(UUID
                .fromString(plugin.config.getString("Candy.Skull-UUID")), null);
        profile.getProperties().put("textures", new Property("textures",
                plugin.config.getString("Candy.Skull-Value")));
        Field profileField;
        try {
            profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (NoSuchFieldException
                 | IllegalArgumentException
                 | IllegalAccessException ex) {
            ex.printStackTrace();
        }

        meta.setDisplayName(ChatColor
                .translateAlternateColorCodes('&',
                        plugin.config.getString("Candy.Display-Name")));
        meta.setLore(lore);
        item.setItemMeta(meta);

        Random random = new Random();
        int randomSound = random.nextInt(sounds.size());
        Sound sound = sounds.get(randomSound);

        if(plugin.config.getBoolean("Sounds.On-Bed-Break.Enabled").equals(true)) {
            p.playSound(p.getLocation(), sound, 3.0F, 0.5F);
        }
        if(plugin.config.getBoolean("Candy.Enabled").equals(true)) {
            p.getInventory().addItem(item);
        }
    }
}
