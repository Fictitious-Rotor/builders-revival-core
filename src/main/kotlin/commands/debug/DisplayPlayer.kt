package commands.debug

import metadata.displayAllRewardsMetadataTag
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class DisplayPlayer: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, commandLabel: String, args: Array<String>) : Boolean {
        if (sender is Player) {
            displayAllRewardsMetadataTag(sender)
            return true
        }

        return false
    }
}