package rewards

import events.Condition

abstract class RewardFlow<T : Condition>(val condition: T, val rewards: RewardBundle) {
    abstract fun generateKey(from: T): String
}