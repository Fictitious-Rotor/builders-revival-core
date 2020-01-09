package events.entitykilled

import events.Condition
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.event.entity.EntityDamageByEntityEvent

class MurderCondition(val entityType: EntityType, val threshold: Int) : Condition {
    fun surpassesThreshold(entity: Entity, killCount: Int) : Boolean {
        return entity.lastDamageCause is EntityDamageByEntityEvent
                && killCount >= threshold
    }
}