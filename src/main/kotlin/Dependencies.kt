import listeners.AdvancementEarned
import listeners.EntityKilled
import listeners.LoginListener
import listeners.BlockMined
import me.lucko.luckperms.api.LuckPermsApi
import net.milkbowl.vault.chat.Chat
import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.permission.Permission


class Dependencies(val economy: Economy,
                   val permissions: Permission,
                   val chat: Chat,
                   val luckPermsApi: LuckPermsApi,
                   private val core: Core) {
    private val listeners = setOf(
        LoginListener(economy),
        AdvancementEarned(),
        EntityKilled(),
        BlockMined(HashMap())
    )

    init { listeners.forEach { core.server.pluginManager.registerEvents(it, core) } }
}