package rewards

import LOG
import dependencies
import org.bukkit.entity.Player

class PointReward(private val pointCount: Double) : Reward {
    override fun award(player: Player): Boolean {
        val deposit = dependencies.economy.depositPlayer(player, pointCount)

        if (!deposit.transactionSuccess()) {
            LOG.severe("Payment of $pointCount failed to reach ${player.name} (${player.uniqueId})\nThe economy returned the following error:${deposit.errorMessage}")
        }

        return deposit.transactionSuccess()
    }
}