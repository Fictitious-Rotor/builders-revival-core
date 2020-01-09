package events.advancementearned

import rewards.RewardBundle
import rewards.RewardFlow

class AdvancementEarnedRewardFlow(condition: AdvancementCondition, rewards: RewardBundle) :  RewardFlow<AdvancementCondition>(condition, rewards) {
    override fun generateKey(from: AdvancementCondition): String = condition.advancementKey
}