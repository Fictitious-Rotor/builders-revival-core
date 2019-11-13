package metadata

import CORE
import org.bukkit.entity.Player
import org.bukkit.metadata.FixedMetadataValue
import rewards.AllRewards

private const val ALL_REWARDS_METADATA_NAME = "AllRewards"

fun getAllRewardsMetadata(player: Player) : Set<AllRewards> {
    val metadataAtLocation = getPlayerMetadata(player, ALL_REWARDS_METADATA_NAME)

    return if (metadataAtLocation is Set<*>
        && metadataAtLocation.isNotEmpty()
        && metadataAtLocation.first() is AllRewards) {
        metadataAtLocation.map { it as AllRewards }.toSet()
    } else {
        emptySet()
    }
}

fun setAllRewardsMetadata(player: Player, value: Set<AllRewards>) {
    player.setMetadata(ALL_REWARDS_METADATA_NAME, FixedMetadataValue(CORE, value))
}

fun displayAllRewardsMetadataTag(player: Player) {
    player.sendMessage(getAllRewardsMetadata(player).joinToString(prefix = "Your rewards are: "))
}

fun removeAllRewardsMetadata(player: Player) {
    player.removeMetadata(ALL_REWARDS_METADATA_NAME, CORE)
}