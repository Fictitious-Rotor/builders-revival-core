package metadata

import CORE
import org.bukkit.entity.Player

fun getPlayerMetadata(player: Player, metadataName: String) : Any? {
    return player.getMetadata(metadataName)
        .firstOrNull { it.owningPlugin == CORE }
        ?.value()
}