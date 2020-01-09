package events.blockbroken

import rewards.RewardBundle
import rewards.RewardFlow

class BlockBrokenRewardFlow(condition: BlockBrokenCondition, rewards: RewardBundle) : RewardFlow<BlockBrokenCondition>(condition, rewards) {
    override fun generateKey(from: BlockBrokenCondition): String = "${from.material}:${from.thresholdPoint}"
}