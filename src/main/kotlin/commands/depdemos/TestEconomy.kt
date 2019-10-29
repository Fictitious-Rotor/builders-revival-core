package commands.depdemos

import Dependencies
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TestEconomy(private val dependencies: Dependencies) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, commandLabel: String, args: Array<String>) : Boolean {
        if (sender is Player) {
            // Lets give the player 1.05 currency (note that SOME economic plugins require rounding!)
            val economy = dependencies.economy

            sender.sendMessage(String.format("You have %s", economy.format(economy.getBalance(sender))))
            val deposit = economy.depositPlayer(sender, 1.05)

            sender.sendMessage(
                if (deposit.transactionSuccess())
                    "You were given '${economy.format(deposit.amount)}' and now have '${economy.format(deposit.balance)}'"
                else
                    "An error has occurred '${deposit.errorMessage}'"
            )
            return true
        }

        return false
    }
}