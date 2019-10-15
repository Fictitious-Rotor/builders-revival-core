import org.bukkit.entity.Player
import java.util.logging.Level


fun awardOnce(player: Player, toAward: AllRewards) {
    val existingRewards = getPlayerMetadata(player)
    val alreadyRewarded = existingRewards.contains(toAward)
    displayMetadataTag(player) // FOR DEBUG
    LOG.log(Level.INFO, "AlreadyRewarded: {0}", alreadyRewarded)

    if (!alreadyRewarded) {
        awardReward(player, toAward)
        setPlayerMetadata(player, existingRewards.plus(toAward))
    }
}

fun awardReward(player: Player, toAward: AllRewards) {
    dependencies.economy.depositPlayer(player, toAward.numberOfPoints).transactionSuccess() // TODO Use me!
    player.performCommand(toAward.bonusCommand)
    player.sendMessage(toAward.message)
}