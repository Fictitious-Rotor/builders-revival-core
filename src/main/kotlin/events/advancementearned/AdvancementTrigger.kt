package events.advancementearned

class AdvancementTrigger(private val advancementKey: String) {
    fun isApplicable(advancementKey: String) : Boolean {
        return advancementKey == this.advancementKey
    }
}