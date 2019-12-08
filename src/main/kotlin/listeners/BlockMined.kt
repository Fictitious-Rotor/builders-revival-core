package listeners

import LOG
import awardOnce
import metadata.getMinedDiamondCountMetadata
import metadata.setMinedDiamondCountMetadata
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import rewards.AllRewards
import java.util.logging.Level

data class BreakTrigger(val triggerMaterial: Material, val threshold: Int, val reward: AllRewards)

class BlockMined : Listener {
    private val triggers = setOf(BreakTrigger(Material.DIAMOND_ORE, 3, AllRewards.MINE_32_DIAMOND_ORE))

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        LOG.log(Level.INFO, "Player broke a(n) {0}", event.block.type.name)

        triggers.forEach {
            if (event.block.type == it.triggerMaterial) {
                val count = getMinedDiamondCountMetadata(event.player)

                if (count <= it.threshold) {
                    val newCount = count + 1
                    setMinedDiamondCountMetadata(event.player, newCount)
                    LOG.log(Level.INFO, "Player has broken {0} {1} so far", arrayOf(newCount, it.triggerMaterial))

                    if (newCount == it.threshold) {
                        LOG.log(Level.INFO,"{0} threshold reached!", it.triggerMaterial)
                        awardOnce(event.player, it.reward)
                    }
                }
            }
        }
    }

    private val blocks: Set<Material> = emptySet()
    private val rewards: Map<Trigger, Set<Reward>> = emptyMap()

    @EventHandler
    fun onBlockBreak2(event: BlockBreakEvent) {
        val material = event.block.type
        if (!blocks.contains(material)) { return }

        val count = incremenCounter(event.player, material)

        val rewards = findRewards(material, count)
        if (rewards.isEmpty()) { return }

        for (reward: rewards) {
            awardOnce(event.player, it.reward)
        }
    }
}
