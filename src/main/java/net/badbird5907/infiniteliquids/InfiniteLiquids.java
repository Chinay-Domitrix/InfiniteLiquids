package net.badbird5907.infiniteliquids;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class InfiniteLiquids extends JavaPlugin implements Listener {
    private static long cooldownMillis = 10;
    private static long lastChangedMillis = -1;
    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }
        cooldownMillis = getConfig().getLong("cooldown-millis",10);
        getServer().getPluginManager().registerEvents(this, this);
    }
    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent event) {
        if (event.getSourceBlock().isLiquid()) {
            if (System.currentTimeMillis() - lastChangedMillis > cooldownMillis) {
                lastChangedMillis = System.currentTimeMillis();
            }else return;
            event.setCancelled(true);
            event.getBlock().setType(event.getSourceBlock().getType());
        }
    }
}
