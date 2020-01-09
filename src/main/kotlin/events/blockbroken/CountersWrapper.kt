package events.blockbroken

import org.bukkit.Material

data class CountersWrapper(val allCounters: MutableMap<Material, Int>)