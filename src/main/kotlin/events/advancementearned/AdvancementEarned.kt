package events.advancementearned

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerAdvancementDoneEvent

class AdvancementEarned(advancementEarnedRewardFlow: List<AdvancementEarnedRewardFlow>) : Listener {
    //private val triggers = mapOf(AdvancementCondition("nether/return_to_sender") to RewardBundle(setOf(PointReward(12.0)), "You got loads of points!"))
    private val rewards: Map<String, AdvancementEarnedRewardFlow> = advancementEarnedRewardFlow
        .associateBy { it.condition.advancementKey }

    @EventHandler
    fun onPlayerAdvancement(event: PlayerAdvancementDoneEvent) {
        val player = event.player
        val achievement = event.advancement.key.key

        rewards[achievement]?.rewards?.awardBundle(player)
   }
}
