package events.entitykilled

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.entity.Shulker
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDeathEvent
import rewards.ItemReward
import rewards.PointReward
import rewards.RewardBundle

class EntityKilled : Listener {
    private val newTriggers = mapOf(MurderTrigger { it is Shulker } to RewardBundle(setOf(ItemReward(Material.ITEM_FRAME, 13), PointReward(8.0))))

    @EventHandler
    fun onEntityDeath(event: EntityDeathEvent) {
        val entity = event.entity

        newTriggers.forEach {
            if (it.key.isApplicable(entity)) {
                val entityEvent = entity.lastDamageCause as EntityDamageByEntityEvent

                if (entityEvent.damager is Player) {
                    it.value.awardBundle(entityEvent.damager as Player)
                }
            }
        }
    }
}
