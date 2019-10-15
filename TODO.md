You can set up custom loggers by changing the string from "Minecraft". Will allow better customisation of the log.

Shift all variables into their own class
If you do that, you could move things into the constructor and guarantee that they are not null.

Add enable() and disable() to state object.

If only some of the plugins are available, support only the commands that can run with the currently available dependencies.

On the set, use contains() instead of any()!

Check for errors in awardreward! (see EconomyResponse::transactionSuccess)

Get it to return a report of things that it's done (in the case of that something goes wrong but other things do not)
player.performCommand(toAward.bonusCommand)

May want to add a guid to the PLUGIN_PLAYERDATA_NAME to prevent chance of large iteration to search for my plugin every time I read metadata.

Potentially rename awardReward to applyReward instead.