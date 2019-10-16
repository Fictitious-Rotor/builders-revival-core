import commands.testEconomy
import commands.testLuckPerms
import commands.testPermission
import me.lucko.luckperms.api.LuckPermsApi
import net.milkbowl.vault.chat.Chat
import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.permission.Permission
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger
import kotlin.reflect.KClass

val LOG = Logger.getLogger("Builders-Revival-Core")

// TODO can I get away with this?
lateinit var CORE: Core
lateinit var dependencies: Dependencies

class Core : JavaPlugin() {
    @Suppress("UNCHECKED_CAST")
    private fun <T> getService(kClass: KClass<*>): T? = server.servicesManager.getRegistration(kClass.java)?.provider as T?

    private fun logError(dependencyName: String) {
        LOG.severe(String.format("[%s] - Disabled due to no %s dependency found!", description.name, dependencyName))

        server.pluginManager.disablePlugin(this)
    }

    override fun onEnable() {
        val economy = getService<Economy>(Economy::class)
        val permissions = getService<Permission>(Permission::class)
        val chat = getService<Chat>(Chat::class)
        val luckPermsApi = getService<LuckPermsApi>(LuckPermsApi::class)

        if (economy == null) { return logError("Vault") }
        if (permissions == null) { return logError("Vault Permissions") }
        if (chat == null) { return logError("Vault Chat") }
        if (luckPermsApi == null) { return logError("Luck Perms API") }

        CORE = this
        dependencies = Dependencies(economy, permissions, chat, luckPermsApi, this)
    }

    override fun onDisable() {
        LOG.info(String.format("[%s] Disabled Version %s", description.name, description.version))
    }

    override fun onCommand(sender: CommandSender, command: Command, commandLabel: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            LOG.info("Only players are supported for this Example Plugin, but you should not do this!!!")
            return true
        }

        return when (command.label) {
            "test-economy" -> { testEconomy(sender, dependencies.economy); true }
            "test-permission" -> { testPermission(sender, dependencies.permissions); true }
            "/wand" -> { sender.sendMessage("Successfully intercepted '/wand'!"); true }
            "say" -> { sender.sendMessage("Successfully intercepted 'send'!"); true }
            "test-luck-perms" -> { testLuckPerms(dependencies.luckPermsApi); true }
            "wipe-player" -> { removeMetadataTag(sender); true }
            "display-player" -> { displayMetadataTag(sender); true }
            "luck-perms-foray" -> { calulateRank(sender); true }
            else -> false
        }
    }
}
