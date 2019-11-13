
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.bukkit.entity.Player
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import rewards.AllRewards

@RunWith(JUnitParamsRunner::class)
class TestAwarder {

    @Test
    @Parameters(
        "17, false",
        "22, true")
    @Throws(Exception::class)
    fun personIsAdult(age: Int, valid: Boolean) {
        assertThat(Person(age).isAdult(), `is`(valid))
    }

    @Test
    @Parameters(
        "17, false",
        "22, true")
    @Throws(Exception::class)
    fun testAwardOnce(player: Player, toAward: AllRewards) {
        Player()
    }
}