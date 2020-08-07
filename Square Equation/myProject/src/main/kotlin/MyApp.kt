import CounterView
import tornadofx.*
import java.util.*

class MyApp: App() {
    override val primaryView = CounterView::class
    init {
        FX.locale = Locale.FRENCH
    }
}