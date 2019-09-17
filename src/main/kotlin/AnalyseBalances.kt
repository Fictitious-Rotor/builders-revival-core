import net.milkbowl.vault.economy.Economy
import org.bukkit.entity.Player

class AnalyseBalances {
    fun runCheck(previousBalances: Map<Player, Double>, econ: Economy) {
        previousBalances.filter { it.value > econ.getBalance(it.key) }
    }
}