import net.milkbowl.vault.economy.Economy
import org.bukkit.entity.Player

class AdministerAchievements {
    val allAchievements = linkedMapOf(
        10 to AllRewards.TEN_POINTS_REACHED.reward,
        20 to AllRewards.TWENTY_POINTS_REACHED.reward
    )

    fun findEarnedAchievements(player: Player, previousBalances: Map<Player, Double>, econ: Economy): Map<Int, Reward> {
        val currentBalance = econ.getBalance(player)
        val previousBalance = previousBalances[player] ?: 0.0

        return allAchievements.filter { curAch -> (curAch.key > previousBalance) && (curAch.key <= currentBalance) }
    }

    fun awardEarnedAchievements(player: Player, previousBalances: Map<Player, Double>, econ: Economy) {
        val currentBalance = econ.getBalance(player)
        val previousBalance = previousBalances[player]
        var previousAchievement: Reward? = null

        for (achievement in allAchievements.asIterable()) {
            if (achievement.key < currentBalance) {
                previousAchievement = achievement.value
            }
        }
    }

    private fun isReachedThreshold(value: Int): Reward? {
        return when(value) {
            10 -> AllRewards.TEN_POINTS_REACHED.reward
            else -> null
        }
    }
}