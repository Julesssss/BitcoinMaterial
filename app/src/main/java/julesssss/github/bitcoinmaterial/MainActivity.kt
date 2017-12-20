package julesssss.github.bitcoinmaterial

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import julesssss.github.bitcoinmaterial.api.ServiceProvider.coinbaseService
import julesssss.github.bitcoinmaterial.data.PriceResponse
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.ostmodern.util.SchedulerProvider

class MainActivity : AppCompatActivity() {

    private val scheduleProvider by lazy { SchedulerProvider(Schedulers.io(), AndroidSchedulers.mainThread()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }

    private fun setupView() {
        buttonBuyPrice.setOnClickListener {
            coinbaseService.getBuyPrice()
                    .compose(scheduleProvider.getSchedulersForSingle())
                    .subscribeBy(
                            onSuccess = this::handlePriceResponse,
                            onError = this::handleError
                    )
        }
        buttonSellPrice.setOnClickListener {
            coinbaseService.getSellPrice()
                    .compose(scheduleProvider.getSchedulersForSingle())
                    .subscribeBy(
                            onSuccess = this::handlePriceResponse,
                            onError = this::handleError
                    )
        }
    }

    private fun handlePriceResponse(buyPrice: PriceResponse) {
        Toast.makeText(this, "£${buyPrice.data.amount}", Toast.LENGTH_SHORT).show()
    }

    private fun handleError(exception: Throwable) {
        Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_SHORT).show()
    }
}
