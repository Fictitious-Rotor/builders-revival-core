package events.advancementearned

import events.Condition

class AdvancementCondition(val advancementKey: String) : Condition {
    fun isApplicable(advancementKey: String) : Boolean {
        return advancementKey == this.advancementKey
    }
}