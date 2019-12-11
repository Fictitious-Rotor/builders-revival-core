package rewards

import org.bukkit.entity.Player

interface Reward {
    fun award(player: Player) : Boolean
}