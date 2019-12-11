package rewards

import org.bukkit.entity.Player

class RewardBundle(private val rewards: Set<Reward>, private val message: String? = null) {
    fun awardBundle(player: Player) {
        // it.award is hiding side effects
        if (rewards.any { it.award(player) } && message != null) {
            player.sendMessage(message)
        }
    }
}