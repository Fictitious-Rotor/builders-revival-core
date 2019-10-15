package listeners

import AllRewards
import LOG
import awardOnce
import org.bukkit.entity.Player
import org.bukkit.entity.Shulker
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDeathEvent
import java.util.logging.Level

class EntityKilled : Listener {
    @EventHandler
    fun onEntityDeath(event: EntityDeathEvent) {
        val entity = event.entity

        LOG.log(Level.INFO, "A(n) {0} has died", entity.name)

        if (entity.lastDamageCause is EntityDamageByEntityEvent && entity is Shulker) {
            val entityEvent = entity.lastDamageCause as EntityDamageByEntityEvent
            LOG.info("It was a shulker!")

            if (entityEvent.damager is Player) {
                LOG.info("A player killed the shulker!")
                val player = entityEvent.damager as Player

                awardOnce(player, AllRewards.KILL_SHULKER)
            }
        }
    }
}