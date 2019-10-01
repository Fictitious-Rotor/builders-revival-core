package listeners

import AllRewards
import org.bukkit.entity.Player
import org.bukkit.entity.Shulker
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDeathEvent

class EntityKilled (private val awardFun: (Player, AllRewards) -> Unit) : Listener {
    @EventHandler
    fun onEntityDeath(event: EntityDeathEvent) {
        val entity = event.entity

        if (entity.lastDamageCause is EntityDamageByEntityEvent && entity is Shulker) {
            val entityEvent = entity.lastDamageCause as EntityDamageByEntityEvent

            if (entityEvent.damager is Player) {
                val player = entityEvent.damager as Player

                awardFun(player, AllRewards.KILL_SHULKER)
            }
        }
    }
}