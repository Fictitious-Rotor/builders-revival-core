package events.entitykilled

import org.bukkit.entity.EntityType

data class CountersWrapper(val allCounters: MutableMap<EntityType, Int>)