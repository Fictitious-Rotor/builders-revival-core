package events.entitykilled

import rewards.RewardBundle
import rewards.RewardFlow

class MurderedMobRewardFlow(condition: MurderCondition, rewards: RewardBundle) : RewardFlow<MurderCondition>(condition, rewards) {
    override fun generateKey(from: MurderCondition): String = "${from.entityType}:${from.threshold}"
}