If only some of the plugins are available, support only the commands that can run with the currently available dependencies.
May want to add a guid to the PLUGIN_PLAYERDATA_NAME to prevent chance of large iteration to search for my plugin every time I read metadata.

Does performCommand need player permissions? [Yes it does]

class BlockMined(private val oreCounter: HashMap<Strig, Int>) : Listener {
Tut tut tut! Use a normal map!
In fact you should probably just pack it up in the user metadata instead.

Table dispatch
Look in code complete for entering program logic as data
Dagger and dependency injection.


Try to abstract AllRewards

enum class AllRewards(val numberOfPoints: Double, val bonusCommand: String, val message: String) {
    GHAST_REFLECT(5.0, "say I have access to the /say command!","You killed a ghast!"),
    KILL_SHULKER(15.0, NO_COMMAND, "You killed a shulker!"),
    MINE_32_DIAMOND_ORE(2.0, "lottery", "You mined 32 diamonds!")
}

Into a Yaml file


Split into Triggers and Rewards

Abstract it away
Map trigger into reward, passing it through given listeners

Add in some form of unit tests to ensure that everything is working
Add in a Yaml sanity test

Do the tests first, create some interfaces to see how you want it to look
Once the tests gel well with the interfaces, then you can write the implementation.

Make the listeners turn themselves off if they're not being used.

Use JUnit Pragmatist
