package rewards

import org.bukkit.entity.Player

class CommandReward(private val commandToRun: String) : Reward {
    override fun award(player: Player) : Boolean {
        return player.performCommand(commandToRun)
    }
}