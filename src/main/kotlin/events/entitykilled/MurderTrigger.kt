package events.entitykilled

import org.bukkit.entity.Entity
import org.bukkit.event.entity.EntityDamageByEntityEvent

class MurderTrigger(private val typeCheckFn: (Entity) -> Boolean) {
    fun isApplicable(entity: Entity) : Boolean {
        return entity.lastDamageCause is EntityDamageByEntityEvent
                && typeCheckFn(entity)
    }
}