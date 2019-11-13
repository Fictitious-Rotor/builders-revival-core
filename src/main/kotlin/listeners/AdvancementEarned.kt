package listeners

import LOG
import awardOnce
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import rewards.AllRewards
import java.util.logging.Level

data class AdvancementTrigger(val advancementKey: String, val reward: AllRewards)

class AdvancementEarned : Listener {
    private val triggers = setOf(AdvancementTrigger("nether/return_to_sender", AllRewards.GHAST_REFLECT))

    @EventHandler
    fun onPlayerAdvancement(event: PlayerAdvancementDoneEvent) {
        LOG.log(Level.INFO, "Achievement {0} has been earned!", event.advancement.key.key)
        val player = event.player

        triggers.forEach {
            if (event.advancement.key.key == it.advancementKey) {
                LOG.info("Achievement is ghast reflect!")
                awardOnce(player, it.reward)
            }
        }
    }
}
