import events.advancementearned.AdvancementCondition
import events.advancementearned.AdvancementEarned
import events.advancementearned.AdvancementEarnedRewardFlow
import events.entitykilled.EntityKilled
import events.login.PlayerConnected
import events.blockbroken.BlockBroken
import events.blockbroken.BlockBrokenCondition
import events.blockbroken.BlockBrokenRewardFlow
import events.entitykilled.MurderCondition
import events.entitykilled.MurderedMobRewardFlow
import me.lucko.luckperms.api.LuckPermsApi
import net.milkbowl.vault.chat.Chat
import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.permission.Permission
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.event.Listener
import rewards.ExpReward
import rewards.ItemReward
import rewards.PointReward
import rewards.RewardBundle


class Dependencies(val economy: Economy,
                   val permissions: Permission,
                   val chat: Chat,
                   val luckPermsApi: LuckPermsApi,
                   private val core: Core) {
    private val listeners : Set<Listener> = setOf(
        PlayerConnected(economy),
        AdvancementEarned(listOf(
            AdvancementEarnedRewardFlow(AdvancementCondition("nether/return_to_sender"), RewardBundle(setOf(PointReward(12.0)), "You got loads of points!"))
        )),
        EntityKilled(listOf(
            MurderedMobRewardFlow(MurderCondition(EntityType.SHULKER, 3), RewardBundle(setOf(ItemReward(Material.ITEM_FRAME, 13), PointReward(8.0)))),
            MurderedMobRewardFlow(MurderCondition(EntityType.TURTLE, 1), RewardBundle(setOf(PointReward(-10.0)), "You savage!")),
            MurderedMobRewardFlow(MurderCondition(EntityType.BAT, 30), RewardBundle(setOf(ExpReward(432)), "Good shot!"))
        )),
        BlockBroken(listOf(
            BlockBrokenRewardFlow(BlockBrokenCondition(Material.COAL_ORE, 31), RewardBundle(setOf(ItemReward(Material.COAL_ORE, 1)), "You missed one")),
            BlockBrokenRewardFlow(BlockBrokenCondition(Material.COAL_ORE, 63), RewardBundle(setOf(ItemReward(Material.COAL_ORE, 1)), "You missed one again! You're so careless!"))
        ))
    )

    init { listeners.forEach { core.server.pluginManager.registerEvents(it, core) } }
}