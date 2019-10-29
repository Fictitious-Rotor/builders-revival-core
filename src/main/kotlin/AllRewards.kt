const val NO_COMMAND = ""

enum class AllRewards(val numberOfPoints: Double, val bonusCommand: String, val message: String) {
    GHAST_REFLECT(5.0, "say I have access to the /say command!","You killed a ghast!"),
    KILL_SHULKER(15.0, NO_COMMAND, "You killed a shulker!"),
    MINE_32_DIAMOND_ORE(2.0, "lottery", "You mined 32 diamonds!")
}