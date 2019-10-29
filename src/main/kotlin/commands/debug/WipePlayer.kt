package commands.debug

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import removeMetadataTag

class WipePlayer: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, commandLabel: String, args: Array<String>) : Boolean {
        if (sender is Player) {
            removeMetadataTag(sender)
            sender.sendMessage("Your data has been wiped")
            return true
        }

        return false
    }
}