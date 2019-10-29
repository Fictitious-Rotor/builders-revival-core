package commands.depdemos

import Dependencies
import LOG
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TestLuckPerms(private val dependencies: Dependencies) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, commandLabel: String, args: Array<String>) : Boolean {
        if (sender is Player) {
            val luckPermsApi = dependencies.luckPermsApi

            luckPermsApi.trackManager.loadedTracks.forEach { t -> LOG.info("Found loaded track of: %s".format(t.name)) }
            luckPermsApi.trackManager.loadAllTracks()
            luckPermsApi.trackManager.loadedTracks.forEach { t -> LOG.info("Track: %s | Groups: %s".format(t.name, t.groups.reduce { acc, s -> "$acc:$s" })) }
            return true
        }

        return false
    }
}