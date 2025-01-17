import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.logging.Level


fun awardOnce(player: Player, toAward: AllRewards) {
    val existingRewards = getPlayerMetadata(player)
    val alreadyRewarded = existingRewards.contains(toAward)
    displayMetadataTag(player) // FOR DEBUG
    LOG.log(Level.INFO, "AlreadyRewarded: {0}", alreadyRewarded)

    if (!alreadyRewarded) {
        if (applyReward(player, toAward)) {
            setPlayerMetadata(player, existingRewards.plus(toAward))
        }
    }
}

fun applyReward(player: Player, toAward: AllRewards): Boolean {
    val deposit = dependencies.economy
                              .depositPlayer(player, toAward.numberOfPoints)

    if (deposit.transactionSuccess() && toAward.bonusCommand != NO_COMMAND) {
        player.sendMessage(toAward.message)
        val commandSuccessful = player.performCommand(toAward.bonusCommand)

        if (!commandSuccessful) {
            LOG.warning("Failed to run '${toAward.bonusCommand}' when attempting to award ${player.name} with ${toAward.name}!")
            player.sendMessage("[BR-Core] Failed to grant bonus command for '${toAward.name}'. Please raise this issue with staff!")
        }
    } else {
        LOG.severe("Payment for reward ${toAward.name} failed to reach ${player.name} (${player.uniqueId})\nThe economy returned the following error:${deposit.errorMessage}")
    }

    return deposit.transactionSuccess()
}