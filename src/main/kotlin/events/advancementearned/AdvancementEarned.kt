package events.advancementearned

import LOG
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import rewards.PointReward
import rewards.RewardBundle
import java.util.logging.Level

class AdvancementEarned : Listener {
    private val triggers = mapOf(AdvancementTrigger("nether/return_to_sender") to RewardBundle(setOf(PointReward(12.0)), "You got loads of points!"))

    @EventHandler
    fun onPlayerAdvancement(event: PlayerAdvancementDoneEvent) {
        LOG.log(Level.INFO, "Achievement {0} has been earned!", event.advancement.key.key)
        val player = event.player

        triggers.forEach {
            if (it.key.isApplicable(event.advancement.key.key)) {
                it.value.awardBundle(player)
            }
        }
    }
}
