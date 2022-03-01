package net.badbird5907.infiniteliquids

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPhysicsEvent
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.lang.System.currentTimeMillis

class InfiniteLiquids : JavaPlugin(), Listener {
	override fun onEnable() {
		if (!dataFolder.exists()) dataFolder.mkdir()
		if (!File(dataFolder, "config.yml").exists()) saveDefaultConfig()
		cooldownMillis = config.getLong("cooldown-millis", 10)
		server.pluginManager.registerEvents(this, this)
	}

	@EventHandler
	fun onBlockPhysics(event: BlockPhysicsEvent) {
		if (event.sourceBlock.isLiquid) {
			if (currentTimeMillis() - lastChangedMillis > cooldownMillis) lastChangedMillis =
				currentTimeMillis() else return
			event.isCancelled = true
			event.block.type = event.sourceBlock.type
		}
	}

	companion object {
		private var cooldownMillis: Long = 10
		private var lastChangedMillis: Long = -1
	}
}
