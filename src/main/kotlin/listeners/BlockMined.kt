package listeners

import AllRewards
import LOG
import awardOnce
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import java.util.logging.Level

const val DIAMOND_THRESHOLD = 3

class BlockMined(private val oreCounter: HashMap<String, Int>) : Listener {
    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        LOG.log(Level.INFO, "Player broke a(n) {0}", event.block.type.name)

        if (event.block.type == Material.DIAMOND_ORE) {
            val count = oreCounter[event.player.name] ?: 0

            if (count <= DIAMOND_THRESHOLD) {
                oreCounter[event.player.name] = count + 1
                LOG.log(Level.INFO, "Player has broken {0} diamond ore so far", count + 1)

                if (count + 1 == DIAMOND_THRESHOLD) {
                    LOG.info("Diamond threshold reached!")
                    awardOnce(event.player, AllRewards.MINE_32_DIAMOND_ORE)
                }
            }
        }
    }
}