import me.lucko.luckperms.api.Log
import me.lucko.luckperms.api.LuckPermsApi
import net.milkbowl.vault.chat.Chat
import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.permission.Permission
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import java.util.logging.Logger

class Core : JavaPlugin() {
    private val log = Logger.getLogger("Minecraft")
    private lateinit var econ: Economy
    private lateinit var perms: Permission
    private lateinit var chat: Chat
    private lateinit var luckPermsApi: LuckPermsApi

    override fun onDisable() {
        log.info(String.format("[%s] Disabled Version %s", description.name, description.version))
    }

    override fun onEnable() {
        val foundEconomy = setupEconomy()
        val foundPermissions = setupPermissions()
        val foundChat = setupChat()
        val foundLuckPermsApi = setupLuckPermsApi()

        if (foundEconomy == null) { return logError("Vault") }
        if (foundPermissions == null) { return logError("Vault Permissions") }
        if (foundChat == null) { return logError("Vault Chat") }
        if (foundLuckPermsApi == null) { return logError("Luck Perms API") }

        econ = foundEconomy
        perms = foundPermissions
        chat = foundChat
        luckPermsApi = foundLuckPermsApi

        this.server.pluginManager.registerEvents(LoginListener(econ), this)
    }

    private fun logError(dependencyName: String) {
        log.severe(String.format("[%s] - Disabled due to no %s dependency found!", description.name, dependencyName))

        server.pluginManager.disablePlugin(this)
    }

    fun herewego(uuid: UUID) {
        val userManager = luckPermsApi.userManager
        val userFuture = userManager.loadUser(uuid)

        userFuture.thenAcceptAsync{ user -> user.name }


    }

    private fun setupEconomy(): Economy? {
        return server.pluginManager
                     .getPlugin("Vault")
                     ?.server
                     ?.servicesManager
                     ?.getRegistration(Economy::class.java)
                     ?.provider
    }

    private fun setupChat(): Chat? {
        return server.servicesManager
                     .getRegistration(Chat::class.java)
                     ?.provider
    }

    private fun setupPermissions(): Permission? {
        return server.servicesManager
                     .getRegistration(Permission::class.java)
                     ?.provider
    }

    private fun setupLuckPermsApi(): LuckPermsApi? {
        return Bukkit.getServicesManager()
                     .getRegistration(LuckPermsApi::class.java)
                     ?.provider
    }

    override fun onCommand(sender: CommandSender, command: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            log.info("Only players are supported for this Example Plugin, but you should not do this!!!")
            return true
        }

        return when (command.label) {
            "test-economy" -> { testEconomy(sender); true }
            "test-permission" -> { testPermission(sender); true }
            "/wand" -> { sender.sendMessage("Successfully intercepted '/wand'!"); true }
            "say" -> { sender.sendMessage("Successfully intercepted 'send'!"); true }
            "test-luck-perms" -> { testLuckPerms(); true }
            else -> false
        }
    }

    private fun testEconomy(sender: Player) {
        // Lets give the player 1.05 currency (note that SOME economic plugins require rounding!)
        sender.sendMessage(String.format("You have %s", econ.format(econ.getBalance(sender))))
        val deposit = econ.depositPlayer(sender, 1.05)

        sender.sendMessage(if (deposit.transactionSuccess()) {
            String.format("You were given '%s' and now have '%s'", econ.format(deposit.amount), econ.format(deposit.balance))
        } else {
            String.format("An error has occurred: '%s'", deposit.errorMessage)
        })
    }

    private fun testPermission(sender: Player) {
        // Lets test if user has the node "example.plugin.awesome" to determine if they are awesome or just suck
        val permissionName = "example.plugin.awesome"
        val message = if (perms.has(sender, permissionName)) "You are awesome!" else "You suck!"

        sender.sendMessage(message)
    }

    private fun testLuckPerms() {
        luckPermsApi.trackManager.loadedTracks.forEach { t -> log.info("Found loaded track of: %s".format(t.name)) }
        luckPermsApi.trackManager.loadAllTracks()
        luckPermsApi.trackManager.loadedTracks.forEach { t -> log.info("Track: %s | Groups: %s".format(t.name, t.groups.reduce { acc, s -> "$acc:$s" })) }
    }
}

/*
* Dingy Dave has a set of ranks on his server, which he's implemented using the plugin "LuckPerms"
* LuckPerms appears to have an excellent API that we're able to use to interact with what he's created, but each rank lacks potency.
* LuckPerms does not implement any trigger system to grant rankup, nor does it provide any rewards system upon successful rankup.
*
* This program will implement everything that revolves around the ranks (except permissions for other commands, which are handled by luckperms)
*/


/*
 * So we want to capture somebody doing something
 * Say they've killed something, crafted something, achieved something, mined a number of blocks
 * If the action is tracked by this plugin, we want to pass it through and grant one of the following rewards:
 *      * You get a point
 *      * The server runs a command for you
 * Considering how there is a server command to grant points, I should expect all rewards to take the form of a command,
 * thus greatly reducing the complexity of my code
 *
 * The dingemeister seems to want to support multiple sequential commands as one reward, so we'll want to implement that too.
 * Perhaps an array of strings rather than a single one? Or should we just have a long string and chop it into pieces at runtime?
 * Or should all of this be data driven and this is a file that I'm reading line by line?
 */