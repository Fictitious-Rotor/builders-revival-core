package commands.depdemos

import Dependencies
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TestPermissions(private val dependencies: Dependencies) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, commandLabel: String, args: Array<String>) : Boolean {
        if (sender is Player) {
            val permissions = dependencies.permissions

            // Lets test if user has the node "example.plugin.awesome" to determine if they are awesome or just suck
            val permissionName = "example.plugin.awesome"
            val message = if (permissions.has(sender, permissionName)) "You are awesome!" else "You suck!"

            sender.sendMessage(message)
            return true
        }

        return false
    }
}