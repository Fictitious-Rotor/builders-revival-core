package rewards

import org.bukkit.entity.Player

class ExpReward(private val amount: Int) : Reward {
    override fun award(player: Player): Boolean {
        player.giveExp(amount)
        return true
    }
}