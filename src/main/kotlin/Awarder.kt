import org.bukkit.entity.Player
import java.util.logging.Level


fun awardOnce(player: Player, toAward: AllRewards) {
    val existingRewards = getPlayerMetadata(player)
    val alreadyRewarded = existingRewards.contains(toAward)
    displayMetadataTag(player) // FOR DEBUG
    LOG.log(Level.INFO, "AlreadyRewarded: {0}", alreadyRewarded)

    if (!alreadyRewarded) {
        if (awardReward(player, toAward)) {
            setPlayerMetadata(player, existingRewards.plus(toAward))
        }
    }
}

fun awardReward(player: Player, toAward: AllRewards): Boolean {
    val rewardDelivered = dependencies.economy
                                      .depositPlayer(player, toAward.numberOfPoints)
                                      .transactionSuccess()

    if (rewardDelivered) {
        val commandSuccessful = player.performCommand(toAward.bonusCommand)

        if (!commandSuccessful) {
            LOG.warning("Command '${toAward.bonusCommand}' failed!")
            player.sendMessage("[BR-Core] Failed to run '${toAward.bonusCommand}'. Please raise this issue with staff!")
        }
        player.sendMessage(toAward.message)
    }

    return rewardDelivered
}

fun calulateRank(player: Player) {
    dependencies.luckPermsApi.userManager.getUser(player.uniqueId)!!.allNodes.forEach { LOG.info("${it.groupName} | ${it.location}") }
//    when (dependencies.economy.getBalance(player)) {
//        2 ->
//    }
}
