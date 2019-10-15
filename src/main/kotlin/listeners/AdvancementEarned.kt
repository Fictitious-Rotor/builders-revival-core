package listeners

import AllRewards
import LOG
import awardOnce
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import java.util.logging.Level

class AdvancementEarned : Listener {
    @EventHandler
    fun onPlayerAdvancement(event: PlayerAdvancementDoneEvent) {
        LOG.log(Level.INFO, "Achievement {0} has been earned!", event.advancement.key.key)
        val player = event.player

        if (event.advancement.key.key == "nether/return_to_sender") {
            LOG.info("Achievement is ghast reflect!")
            awardOnce(player, AllRewards.GHAST_REFLECT)
        }
    }
}