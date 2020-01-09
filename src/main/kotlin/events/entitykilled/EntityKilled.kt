package events.entitykilled

import CORE
import metadata.getPlayerMetadata
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.metadata.FixedMetadataValue

class EntityKilled(murderedMobRewardsFlows: List<MurderedMobRewardFlow>) : Listener {
    private val rewards: Map<EntityType, List<MurderedMobRewardFlow>> = murderedMobRewardsFlows
        .groupBy { it.condition.entityType }
        .mapValues { it.value.sortedBy { it.condition.threshold } }

    @EventHandler
    fun onEntityDeath(event: EntityDeathEvent) {
        val entity = event.entity
        val entityType = event.entityType
        val player = entity.killer ?: return

        val rewardFlows = rewards[entityType]

        if (rewardFlows != null) {
            val playerCounters = getPlayerMetadata(player, EntityKilledCountRepositoryName) as? CountersWrapper ?: CountersWrapper(mutableMapOf())
            val count = playerCounters.allCounters[entityType] ?: 0

            val applicableReward = rewardFlows.find { it.condition.surpassesThreshold(entity, count) }
            applicableReward?.rewards?.awardBundle(player)

            if (count < Int.MAX_VALUE) { playerCounters.allCounters[entityType] = count + 1 }
            player.setMetadata(EntityKilledCountRepositoryName, FixedMetadataValue(CORE, playerCounters))
        }
    }
}
