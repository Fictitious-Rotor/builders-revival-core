package listeners

import net.milkbowl.vault.economy.Economy
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class LoginListener(private val econ: Economy): Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        event.player.sendMessage("Builder's Revival Core: ${econ.getBalance(event.player)}")
    }
}