package commands.debug

import Dependencies
import LOG
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class LuckPermsForay(private val dependencies: Dependencies) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, commandLabel: String, args: Array<String>) : Boolean {
        if (sender is Player) {
            dependencies
                .luckPermsApi
                .userManager
                .getUser(sender.uniqueId)!!
                .allNodes
                .forEach { LOG.info("${it.groupName} | ${it.location}") }
//            when (dependencies.economy.getBalance(player)) {
//                2 ->
//            }
            return true
        }

        return false
    }
}