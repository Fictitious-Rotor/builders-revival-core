package events.blockbroken

import CORE
import metadata.getPlayerMetadata
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.metadata.FixedMetadataValue

class BlockBroken(blockBreakBrokenRewardFlows: List<BlockBrokenRewardFlow>) : Listener {
    private val rewards: Map<Material, List<BlockBrokenRewardFlow>> = blockBreakBrokenRewardFlows
        .groupBy { it.condition.material }
        .mapValues { it.value.sortedBy { it.condition.thresholdPoint } }

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val material = event.block.type
        val player = event.player

        val rewardFlows = rewards[material]
        if (rewardFlows != null) {
            val playerCounters = getPlayerMetadata(player, BrokenBlockCountRepositoryName) as? CountersWrapper ?: CountersWrapper(mutableMapOf())
            val count = playerCounters.allCounters[material] ?: 0

            val applicableReward = rewardFlows.find { it.condition.surpassesThreshold(count) }
            applicableReward?.rewards?.awardBundle(player)

            if (count < Int.MAX_VALUE) { playerCounters.allCounters[material] = count + 1 }
            player.setMetadata(BrokenBlockCountRepositoryName, FixedMetadataValue(CORE, playerCounters))
        }
    }
}

// Idk what this is for. Probably demonstration of something idk
// val b = BrokenBlockRewardFlow(BlockBrokenTrigger(Material.BLACK_BANNER, 5), RewardBundle(setOf(ExpReward(4))))

// You were going to pack allCounters into an object and verify that. By doing this, you will skip all of the verification expense.

/*
 * Try Condition rather than Trigger
 * Probably keep Listener
 * Give a new name for Reward, to free up the term to represent the flow of { Trigger -> Listener -> Reward }
 * Create a new FQDN system for the rewards.
 * Add an abstract method to what is currently known as the 'Thingy' class, which asks its implementation to define a primary string key
 * This allows us to distribute the problem of reward keys without having to manually put one into each installation.
 */