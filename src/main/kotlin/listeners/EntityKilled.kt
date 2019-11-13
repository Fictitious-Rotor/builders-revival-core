package listeners

import awardOnce
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.entity.Shulker
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDeathEvent
import rewards.AllRewards

data class DeathTrigger(val isRightEventAndEntity: (Entity) -> Boolean, val reward: AllRewards)

class EntityKilled : Listener {
    private val triggers = setOf(DeathTrigger({ it.lastDamageCause is EntityDamageByEntityEvent && it is Shulker }, AllRewards.KILL_SHULKER))

    @EventHandler
    fun onEntityDeath(event: EntityDeathEvent) {
        val entity = event.entity

        triggers.forEach {
            if (it.isRightEventAndEntity(entity)) {
                val entityEvent = entity.lastDamageCause as EntityDamageByEntityEvent

                if (entityEvent.damager is Player) {
                    val player = entityEvent.damager as Player

                    awardOnce(player, AllRewards.KILL_SHULKER)
                }
            }
        }
    }
}
