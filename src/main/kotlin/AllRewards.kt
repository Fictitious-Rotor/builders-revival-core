private const val LEVEL_UP = "/grant \$player 1"

enum class AllRewards(val reward: Reward) {
    GHAST_REFLECT(Reward(LEVEL_UP, "You killed a ghast!")),
    DEFEAT_WITHER(Reward(LEVEL_UP, "You beat the wither!"))
}