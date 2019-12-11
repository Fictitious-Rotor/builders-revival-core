package events.blockbroken

import org.bukkit.Material

class BlockMinedTrigger(private val material: Material, private val thresholdPoint: Int) {
    fun isCorrectMaterial(material: Material) : Boolean {
        return material == this.material
    }

    fun isAtThreshold(blockCount: Int) : Boolean {
        return blockCount == this.thresholdPoint
    }
}