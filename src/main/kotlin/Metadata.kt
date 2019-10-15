import org.bukkit.entity.Player
import org.bukkit.metadata.FixedMetadataValue

private const val PLUGIN_PLAYERDATA_NAME: String = "BR-Core"

fun getPlayerMetadata(player: Player): Set<*> {
    return player.getMetadata(PLUGIN_PLAYERDATA_NAME)
                 .firstOrNull { it.owningPlugin == CORE }
                 ?.value() as Set<*>? ?: HashSet<AllRewards>() // Check if this crashes when you feed it the wrong type
}
fun setPlayerMetadata(player: Player, value: Set<*>) {
    player.setMetadata(PLUGIN_PLAYERDATA_NAME, FixedMetadataValue(CORE, value))
}

fun displayMetadataTag(player: Player) {
    player.sendMessage(getPlayerMetadata(player).fold("Your rewards are: ") { acc, s -> "$acc, $s" }.toString())
}

fun removeMetadataTag(player: Player) {
    player.setMetadata(PLUGIN_PLAYERDATA_NAME, FixedMetadataValue(CORE, HashSet<AllRewards>()))
    player.sendMessage("Your data has been wiped")
}