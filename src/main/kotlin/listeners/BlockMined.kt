package listeners

import AllRewards
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

const val DIAMOND_THRESHOLD = 32

class BlockMined(private val awardFun: (Player, AllRewards) -> Unit,
                 private val oreCounter: HashMap<String, Int>) : Listener {
    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        if (event.block.type == Material.DIAMOND_ORE) {
            val count = oreCounter[event.player.name] ?: 0

            if (count < DIAMOND_THRESHOLD) {
                oreCounter[event.player.name] = count + 1

                if (count == DIAMOND_THRESHOLD) {
                    awardFun(event.player, AllRewards.MINE_32_DIAMOND_ORE)
                }
            }
        }
    }
}