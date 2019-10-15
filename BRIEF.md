Dingy Dave has a set of ranks on his server, which he's implemented using the plugin "LuckPerms"
LuckPerms appears to have an excellent API that we're able to use to interact with what he's created, but each rank lacks potency.
LuckPerms does not implement any trigger system to grant rankup, nor does it provide any rewards system upon successful rankup.

This program will implement everything that revolves around the ranks (except permissions for other commands, which are handled by luckperms)

So we want to capture somebody doing something
Say they've killed something, crafted something, achieved something, mined a number of blocks
If the action is tracked by this plugin, we want to pass it through and grant one of the following rewards:
  * You get a point
  * The server runs a command for you
Considering how there is a server command to grant points, I should expect all rewards to take the form of a command,
thus greatly reducing the complexity of my code

The dingemeister seems to want to support multiple sequential commands as one reward, so we'll want to implement that too.
Perhaps an array of strings rather than a single one? Or should we just have a long string and chop it into pieces at runtime?
Or should all of this be data driven and this is a file that I'm reading line by line?