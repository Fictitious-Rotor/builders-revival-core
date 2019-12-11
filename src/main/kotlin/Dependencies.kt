import events.advancementearned.AdvancementEarned
import events.entitykilled.EntityKilled
import events.login.LoginListener
import events.blockbroken.BlockMined
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
        BlockMined()
    )

    init { listeners.forEach { core.server.pluginManager.registerEvents(it, core) } }
}