package listeners

import AllRewards
import Core
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerAdvancementDoneEvent

class AdvancementEarned(private val core: Core,
                        private val awardFun: (Player, AllRewards) -> Unit) : Listener {
    @EventHandler
    fun onPlayerAdvancement(event: PlayerAdvancementDoneEvent) {
        val player = event.player
        val advancementKey = NamespacedKey(core,"nether/return_to_sender")

        if (event.advancement == Bukkit.getAdvancement(advancementKey)) {
            awardFun(player, AllRewards.GHAST_REFLECT)
        }
    }
}