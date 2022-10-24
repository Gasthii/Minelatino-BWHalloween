package com.gasthiiml.bwhalloween.commands;

import com.gasthiiml.bwhalloween.BWHalloween;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.UUID;

@SuppressWarnings("ALL")
public class GiveCommand implements CommandExecutor {

    private BWHalloween plugin;

    public GiveCommand(BWHalloween plg) {
        plugin = plg;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;

            if(p.hasPermission("minelatino.halloween.give")) {
                if(strings.length == 0) {
                    p.sendMessage(ChatColor
                            .translateAlternateColorCodes('&',
                                    plugin.config.getString("Messages.Give.Usage")));
                }
                if(strings.length == 1) {
                    p.sendMessage(ChatColor
                            .translateAlternateColorCodes('&',
                                    plugin.config.getString("Messages.Give.Usage")));
                }
                if(strings.length == 2) {
                    p.sendMessage(ChatColor
                            .translateAlternateColorCodes('&',
                                    plugin.config.getString("Messages.Give.Usage")));
                }
                if(strings.length >= 3) {
                    Player target = Bukkit.getPlayer(strings[0]);
                    String skull = strings[1];
                    int amount = Integer.parseInt(strings[2]);

                    if(target != null) {
                        if(skull.equalsIgnoreCase("candy")
                                | skull.equalsIgnoreCase("pumpkin")) {
                            if(skull.equalsIgnoreCase("candy")) {
                                ItemStack item = new ItemStack(Material.SKULL_ITEM, amount, (byte) 3);
                                SkullMeta meta = (SkullMeta) item.getItemMeta();
                                ArrayList<String> lore = new ArrayList<>();

                                for(String str : plugin.config.getStringList("Candy.Lore")) {
                                    lore.add(ChatColor
                                            .translateAlternateColorCodes('&', str));
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

                                target.getInventory().addItem(item);
                                p.sendMessage(ChatColor
                                        .translateAlternateColorCodes('&',
                                                plugin.config.getString("Messages.Give.Candies"))
                                        .replaceAll("%player%", target.getName())
                                        .replaceAll("%amount%",
                                                String.valueOf(amount)));
                            }
                            if(skull.equalsIgnoreCase("pumpkin")) {
                                ItemStack item = new ItemStack(Material.SKULL_ITEM, amount, (byte) 3);
                                SkullMeta meta = (SkullMeta) item.getItemMeta();
                                ArrayList<String> lore = new ArrayList<>();

                                for(String str : plugin.config.getStringList("Pumpkin.Lore")) {
                                    lore.add(ChatColor
                                            .translateAlternateColorCodes('&', str));
                                }

                                GameProfile profile = new GameProfile(UUID
                                        .fromString(plugin.config.getString("Pumpkin.Skull-UUID")), null);
                                profile.getProperties().put("textures", new Property("textures",
                                        plugin.config.getString("Pumpkin.Skull-Value")));
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
                                                plugin.config.getString("Pumpkin.Display-Name")));
                                meta.setLore(lore);
                                item.setItemMeta(meta);

                                target.getInventory().addItem(item);
                                p.sendMessage(ChatColor
                                        .translateAlternateColorCodes('&',
                                                plugin.config.getString("Messages.Give.Pumpkins"))
                                        .replaceAll("%player%", target.getName())
                                        .replaceAll("%amount%",
                                                String.valueOf(amount)));
                            }
                        }
                    }
                }
            }
            else {
                p.sendMessage(ChatColor
                        .translateAlternateColorCodes('&',
                                plugin.config.getString("Messages.No-Permission")));
            }
        }

        if(commandSender instanceof ConsoleCommandSender) {
            if(strings.length == 0) {
                plugin.getLogger().info("Usage: /mlhg <player> <candy/pumpkin> <amount>");
            }
            if(strings.length == 1) {
                plugin.getLogger().info("Usage: /mlhg <player> <candy/pumpkin> <amount>");
            }
            if(strings.length == 2) {
                plugin.getLogger().info("Usage: /mlhg <player> <candy/pumpkin> <amount>");
            }
            if(strings.length >= 3) {
                Player target = Bukkit.getPlayer(strings[0]);
                String skull = strings[1];
                int amount = Integer.parseInt(strings[2]);

                if(target != null) {
                    if(skull.equalsIgnoreCase("candy")
                            | skull.equalsIgnoreCase("pumpkin")) {
                        if (skull.equalsIgnoreCase("candy")) {
                            ItemStack item = new ItemStack(Material.SKULL_ITEM, amount, (byte) 3);
                            SkullMeta meta = (SkullMeta) item.getItemMeta();
                            ArrayList<String> lore = new ArrayList<>();

                            for (String str : plugin.config.getStringList("Candy.Lore")) {
                                lore.add(ChatColor
                                        .translateAlternateColorCodes('&', str));
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

                            target.getInventory().addItem(item);
                            plugin.getLogger().info("Given " + amount + " candies for " + target.getName());
                        }
                        if (skull.equalsIgnoreCase("pumpkin")) {
                            ItemStack item = new ItemStack(Material.SKULL_ITEM, amount, (byte) 3);
                            SkullMeta meta = (SkullMeta) item.getItemMeta();
                            ArrayList<String> lore = new ArrayList<>();

                            for (String str : plugin.config.getStringList("Pumpkin.Lore")) {
                                lore.add(ChatColor
                                        .translateAlternateColorCodes('&', str));
                            }

                            GameProfile profile = new GameProfile(UUID
                                    .fromString(plugin.config.getString("Pumpkin.Skull-UUID")), null);
                            profile.getProperties().put("textures", new Property("textures",
                                    plugin.config.getString("Pumpkin.Skull-Value")));
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
                                            plugin.config.getString("Pumpkin.Display-Name")));
                            meta.setLore(lore);
                            item.setItemMeta(meta);

                            target.getInventory().addItem(item);
                            plugin.getLogger().info("Given " + amount + " pumpkins for " + target.getName());
                        }
                    }
                }
            }
        }
        return true;
    }
}
