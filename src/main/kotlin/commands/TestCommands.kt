package commands

import LOG
import org.bukkit.entity.Player
import me.lucko.luckperms.api.LuckPermsApi
import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.permission.Permission

fun testEconomy(sender: Player, econ: Economy) {
    // Lets give the player 1.05 currency (note that SOME economic plugins require rounding!)
    sender.sendMessage(String.format("You have %s", econ.format(econ.getBalance(sender))))
    val deposit = econ.depositPlayer(sender, 1.05)

    sender.sendMessage(if (deposit.transactionSuccess())
        "You were given '${econ.format(deposit.amount)}' and now have '${econ.format(deposit.balance)}'"
    else
        "An error has occurred '${deposit.errorMessage}'")
}

fun testPermission(sender: Player, perms: Permission) {
    // Lets test if user has the node "example.plugin.awesome" to determine if they are awesome or just suck
    val permissionName = "example.plugin.awesome"
    val message = if (perms.has(sender, permissionName)) "You are awesome!" else "You suck!"

    sender.sendMessage(message)
}

fun testLuckPerms(luckPermsApi: LuckPermsApi) {
    luckPermsApi.trackManager.loadedTracks.forEach { t -> LOG.info("Found loaded track of: %s".format(t.name)) }
    luckPermsApi.trackManager.loadAllTracks()
    luckPermsApi.trackManager.loadedTracks.forEach { t -> LOG.info("Track: %s | Groups: %s".format(t.name, t.groups.reduce { acc, s -> "$acc:$s" })) }
}