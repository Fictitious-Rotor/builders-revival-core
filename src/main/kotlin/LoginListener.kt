import net.milkbowl.vault.economy.Economy
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import org.bukkit.event.player.PlayerJoinEvent

class LoginListener(private val econ: Economy): Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        event.player.sendMessage(econ.getBalance(event.player).toString())
    }

    @EventHandler
    fun onAdvancement(event: PlayerAdvancementDoneEvent) {
        event.player.sendMessage(String.format("You got '%s'", event.advancement.toString()))
    }
}