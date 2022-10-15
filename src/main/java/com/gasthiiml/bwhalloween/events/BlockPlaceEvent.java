package com.gasthiiml.bwhalloween.events;

import com.gasthiiml.bwhalloween.BWHalloween;
import de.marcely.bedwars.api.BedwarsAPI;
import de.marcely.bedwars.api.arena.Arena;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@SuppressWarnings("ALL")
public class BlockPlaceEvent implements Listener {

    private BWHalloween plugin;

    public BlockPlaceEvent(BWHalloween plg) {
        plugin = plg;
    }

    @EventHandler
    public void onBlockPlace(org.bukkit.event.block.BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Block block = e.getBlockPlaced();

        try {
            if(block.getType().equals(Material.SKULL_ITEM)) {
                BedwarsAPI.onReady(() -> {
                    Arena arena = BedwarsAPI.getGameAPI().getArenaByPlayer(p);

                    if(arena != null) e.setCancelled(true);
                    else return;
                });
            }
            else return;
        }
        catch(Exception ex) {
            return;
        }
    }
}
