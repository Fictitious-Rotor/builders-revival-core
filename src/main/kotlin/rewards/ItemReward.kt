package rewards

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import utils.isRoomInInventory

class ItemReward(private val item: Material, private val quantity: Int) : Reward {
    override fun award(player: Player) : Boolean {
        val rewardItem = ItemStack(item, quantity)
        val haveRoom = isRoomInInventory(player.inventory, rewardItem)

        if (haveRoom) {
            player.inventory.addItem()
        }

        return haveRoom
    }
}