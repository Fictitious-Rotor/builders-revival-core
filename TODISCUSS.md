Usage of `lateinit var CORE` in `Metadata.kt` - I wanted to have a nice static utility method that would still have access to the quintessential plugin class, which I need for all metadata methods. 

Note how I've actually had to manually write a setter in Kotlin! It feels as though I've done something wrong, considering that I've effectively re-implemented a class 

What I really need is some sort of module that I can wrap around everything but the Core class, that I could somehow instantiate  or setup.

That way, the program can have an init state, where just the Core is ready to go, then when onEnable is called, the rest of the program can be made with the 'init' variables as global constants