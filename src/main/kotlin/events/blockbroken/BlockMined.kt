package events.blockbroken

import CORE
import metadata.getPlayerMetadata
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.metadata.FixedMetadataValue
import rewards.ItemReward
import rewards.RewardBundle

private const val BrokenBlockCountRepositoryName = "BROKEN_BLOCK_COUNT_REPOSITORY"

class BlockMined : Listener {
    private val rewards = mapOf(BlockMinedTrigger(Material.DIAMOND_ORE, 32) to RewardBundle(setOf(ItemReward(Material.DIAMOND, 3))))

    @Suppress("UNCHECKED_CAST") // IntelliJ didn't spot that it the cast is safe.
    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val material = event.block.type
        val player = event.player
        val allCounters = getPlayerMetadata(player, BrokenBlockCountRepositoryName) as? Map<*, *> ?: mutableMapOf<Material, Int>()
        val usableCounters : MutableMap<Material, Int> = allCounters.filter { it.key is Material && it.value is Int } as MutableMap<Material, Int>
        var allCountersTouched = false

        rewards.forEach {
            if (it.key.isCorrectMaterial(material)) {
                val count = usableCounters[material] ?: 0

                if (it.key.isAtThreshold(count)) {
                    it.value.awardBundle(player)
                    allCountersTouched = true
                }

                usableCounters[material] = count + 1
            }
        }

        if (allCountersTouched) {
            player.setMetadata(BrokenBlockCountRepositoryName, FixedMetadataValue(CORE, allCounters))
        }
    }
}
