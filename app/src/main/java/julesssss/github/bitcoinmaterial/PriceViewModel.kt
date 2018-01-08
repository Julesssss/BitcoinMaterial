package julesssss.github.bitcoinmaterial

import android.graphics.Color
import android.graphics.Paint
import com.db.chart.animation.Animation
import com.db.chart.model.LineSet
import com.db.chart.renderer.AxisRenderer
import com.db.chart.util.Tools
import com.db.chart.view.LineChartView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import julesssss.github.bitcoinmaterial.api.ServiceProvider.coinbaseService
import julesssss.github.bitcoinmaterial.data.PriceResponse
import uk.co.ostmodern.util.SchedulerProvider

class PriceViewModel {

    private val mLabels = arrayOf("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")

    private val mValues = floatArrayOf(25f, 27f, 37f, 39f, 23f, 36f, 50f, 83f, 65f, 68f, 28f, 55f, 58f, 50f, 53f, 53f, 57f, 48f, 50f, 53f, 54f, 25f, 37f, 35f, 37f, 35f, 50f, 62f, 55f, 59f, 70f, 82f, 60f, 55f, 63f, 65f, 69f, 70f, 73f, 80f)

    private val scheduleProvider by lazy { SchedulerProvider(Schedulers.io(), AndroidSchedulers.mainThread()) }

    fun getBitcoinPrice(): Single<PriceResponse> = coinbaseService.getSpotPrice("BTC-GBP")
            .compose(scheduleProvider.getSchedulersForSingle())

    fun setupGraph(mChart: LineChartView) {

        val dataset = LineSet(mLabels, mValues)
        dataset.setColor(Color.parseColor("#ffffff"))
                .setThickness(Tools.fromDpToPx(3f))
                .setSmooth(true)
                .beginAt(0)
                .endAt(40)

        mChart.addData(dataset)

        val thresPaint = Paint()
        thresPaint.color = Color.parseColor("#ffffff")
        thresPaint.style = Paint.Style.STROKE
        thresPaint.isAntiAlias = true
        thresPaint.strokeWidth = Tools.fromDpToPx(1f)

        mChart.setBorderSpacing(Tools.fromDpToPx(0f).toInt())
                .setXLabels(AxisRenderer.LabelPosition.NONE)
                .setLabelsColor(Color.parseColor("#ffffff"))
                .setYLabels(AxisRenderer.LabelPosition.NONE)
                .setXAxis(false)
                .setYAxis(false)
                .setAxisBorderValues(0f, 110f)

        mChart.show(Animation().fromAlpha(0))
    }

}