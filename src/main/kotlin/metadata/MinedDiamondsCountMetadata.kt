package metadata

import CORE
import org.bukkit.entity.Player
import org.bukkit.metadata.FixedMetadataValue
import rewards.AllRewards

private const val ALL_REWARDS_METADATA_NAME = "MinedDiamondCount"

fun getMinedDiamondCountMetadata(player: Player) : Int {
    val metadataAtLocation = getPlayerMetadata(player, ALL_REWARDS_METADATA_NAME)

    return if (metadataAtLocation == null || metadataAtLocation !is Int) { 0 }
           else { metadataAtLocation }
}

fun setMinedDiamondCountMetadata(player: Player, value: Int) {
    player.setMetadata(ALL_REWARDS_METADATA_NAME, FixedMetadataValue(CORE, value))
}

fun displayMinedDiamondCountMetadataTag(player: Player) {
    player.sendMessage(getAllRewardsMetadata(player).joinToString(prefix = "Your rewards are: "))
}

fun removeMinedDiamondCountMetadata(player: Player) {
    player.removeMetadata(ALL_REWARDS_METADATA_NAME, CORE)
}