import javafx.beans.property.SimpleStringProperty
import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import javafx.scene.chart.XYChart.Series
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import tornadofx.*
import java.text.DecimalFormat


class CounterView : View() {
    override val root : BorderPane by fxml("/CounterView.fxml")

    val counter = SimpleStringProperty()
    val counterLabel: Label by fxid()
    val inputTextLabelA: TextField by fxid()
    val inputTextLabelB: TextField by fxid()
    val inputTextLabelC: TextField by fxid()
    val graphLabel: LineChart<Double, Double> by fxid()



    init {
        counterLabel.bind(counter)

    }

    @FXML
    fun increment() {
        var num1 = inputTextLabelA.getText().toDouble()
        var num2 = inputTextLabelB.getText().toDouble()
        var num3 = inputTextLabelC.getText().toDouble()

        var d = num2 * num2 - 4 * num1 * num3

        var x1 = (-num2 + Math.sqrt(d)) /(2 * num1)
        var x2 = (-num2 - Math.sqrt(d)) /(2 * num1)

        val dec = DecimalFormat("0.000")
        if (d > 0){
            counter.value = "Ответ: ${dec.format(x1)} и ${dec.format(x2)} "
        } else if (d == 0.0){
            counter.value = "Ответ: ${dec.format(x1)}"
        } else if (d < 0.0) {
            counter.value = "Ответ: корней нет"
        }

        plotGraph(d, x1, x2, num1, num2, num3)
    }

    fun plotGraph(d: Double, x1: Double, x2: Double, num1: Double, num2: Double, num3: Double){
        graphLabel.getData().clear()
        val series = XYChart.Series<Double, Double>()
        series.name = "График параболы"

        //Построение графика по 7 точкам
        plotWhenDNull(d, x1, x2, num1, num2, num3, series)
        plotWhenDMoreNull(d, x1, x2, num1, num2, num3, series)
        plotWhenDLessNull(d, x1, x2, num1, num2, num3, series)

        graphLabel.getData().add(series)
    }

    fun plotWhenDNull(d: Double, x1: Double, x2: Double, num1: Double, num2: Double, num3: Double, series: Series<Double, Double>) {
        if(d == 0.0){
            val t1 = x1 - 3
            val t2 = x1 - 2
            val t3 = x1 - 1
            val t4 = x1 - 0
            val t5 = x1 + 1
            val t6 = x1 + 2
            val t7 = x1 + 3
            series.getData().add(XYChart.Data(t1, parabola(t1, num1, num2, num3)))
            series.getData().add(XYChart.Data(t2, parabola(t2, num1, num2, num3)))
            series.getData().add(XYChart.Data(t3, parabola(t3, num1, num2, num3)))
            series.getData().add(XYChart.Data(t4, parabola(t4, num1, num2, num3)))
            series.getData().add(XYChart.Data(t5, parabola(t5, num1, num2, num3)))
            series.getData().add(XYChart.Data(t6, parabola(t6, num1, num2, num3)))
            series.getData().add(XYChart.Data(t7, parabola(t7, num1, num2, num3)))
        }
    }

    fun plotWhenDMoreNull(d: Double, x1: Double, x2: Double, num1: Double, num2: Double, num3: Double, series: Series<Double, Double>) {
        if(d > 0){
            val delta = (x2-x1)/3.0
            val t1 = x1 - delta
            val t2 = x1
            val t3 = x1 + 1*delta
            val t4 = x1 + 2*delta
            val t5 = x1 + 3*delta
            val t6 = x2
            val t7 = x2 + delta
            series.getData().add(XYChart.Data(t1, parabola(t1, num1, num2, num3)))
            series.getData().add(XYChart.Data(t2, parabola(t2, num1, num2, num3)))
            series.getData().add(XYChart.Data(t3, parabola(t3, num1, num2, num3)))
            series.getData().add(XYChart.Data(t4, parabola(t4, num1, num2, num3)))
            series.getData().add(XYChart.Data(t5, parabola(t5, num1, num2, num3)))
            series.getData().add(XYChart.Data(t6, parabola(t6, num1, num2, num3)))
            series.getData().add(XYChart.Data(t7, parabola(t7, num1, num2, num3)))
        }
    }

    fun plotWhenDLessNull(d: Double, x1: Double, x2: Double, num1: Double, num2: Double, num3: Double, series: Series<Double, Double>) {
        if(d < 0){
            //найдем вершину через производную
            var t4 = 0.0
            if(num1 == 0.0){
                t4 = 0.0
            } else {
                t4 = - num2/(2*num1)
            }

            val t1 = t4 - 3
            val t2 = t4 - 2
            val t3 = t4 - 1
            val t5 = t4 + 1
            val t6 = t4 + 2
            val t7 = t4 + 3
            series.getData().add(XYChart.Data(t1, parabola(t1, num1, num2, num3)))
            series.getData().add(XYChart.Data(t2, parabola(t2, num1, num2, num3)))
            series.getData().add(XYChart.Data(t3, parabola(t3, num1, num2, num3)))
            series.getData().add(XYChart.Data(t4, parabola(t4, num1, num2, num3)))
            series.getData().add(XYChart.Data(t5, parabola(t5, num1, num2, num3)))
            series.getData().add(XYChart.Data(t6, parabola(t6, num1, num2, num3)))
            series.getData().add(XYChart.Data(t7, parabola(t7, num1, num2, num3)))
        }
    }

    fun parabola(t: Double, num1: Double, num2: Double, num3: Double): Double{
        return num1*t*t + num2*t + num3
    }


}

