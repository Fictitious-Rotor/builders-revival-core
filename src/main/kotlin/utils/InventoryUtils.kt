package utils

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory

fun isRoomInItemStack(host: ItemStack, guest: ItemStack) : Boolean {
    return host.isSimilar(guest)
            && ((host.maxStackSize - host.amount) >= guest.amount)
}

fun isRoomInInventory(playerInventory: PlayerInventory, prospectiveItemStack: ItemStack) : Boolean {
    return playerInventory.any { it == null
            || isRoomInItemStack(it, prospectiveItemStack) }
}