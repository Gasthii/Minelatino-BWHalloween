package com.gasthiiml.bwhalloween.commands;

import com.gasthiiml.bwhalloween.BWHalloween;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

@SuppressWarnings("ALL")
public class ReloadCommand implements CommandExecutor {

    private BWHalloween plugin;

    public ReloadCommand(BWHalloween plg) {
        plugin = plg;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player p = (Player) commandSender;

            if(p.hasPermission("minelatino.halloween.reload")) {
                try {
                    plugin.config.reload();
                    p.sendMessage(ChatColor
                            .translateAlternateColorCodes('&',
                                    plugin.config.getString("Reload.Reloaded")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                p.sendMessage(ChatColor
                        .translateAlternateColorCodes('&',
                                plugin.config.getString("Reload.No-Permission")));
            }
        }
        if(commandSender instanceof ConsoleCommandSender) {
            try {
                plugin.config.reload();
                plugin.getLogger().info("Reloaded config!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return true;
    }
}
