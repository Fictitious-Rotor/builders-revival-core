package events.blockbroken

import events.Condition
import org.bukkit.Material

class BlockBrokenCondition(val material: Material, val thresholdPoint: Int) : Condition {
    fun surpassesThreshold(blockCount: Int) : Boolean {
        return blockCount >= this.thresholdPoint
    }
}