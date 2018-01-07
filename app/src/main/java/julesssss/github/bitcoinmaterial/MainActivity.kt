package julesssss.github.bitcoinmaterial

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import io.reactivex.rxkotlin.subscribeBy
import julesssss.github.bitcoinmaterial.data.CurrenciesResponse
import julesssss.github.bitcoinmaterial.data.PriceResponse
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel = HomeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }

    private fun setupView() {
        buttonBuyPrice.setOnClickListener {
            viewModel.getBuyPrice()
                    .subscribeBy(
                            onSuccess = this::handlePriceResponse,
                            onError = this::handleError
                    )
        }
        buttonSellPrice.setOnClickListener {
            viewModel.getSellPrice()
                    .subscribeBy(
                            onSuccess = this::handlePriceResponse,
                            onError = this::handleError
                    )
        }
        buttonSpotPrice.setOnClickListener {
            viewModel.getSpotPrice()
                    .subscribeBy(
                            onSuccess = this::handlePriceResponse,
                            onError = this::handleError
                    )
        }
        buttonD.setOnClickListener {
            viewModel.getCurrencies()
                    .subscribeBy(
                            onSuccess = ::handleExchangeResponse,
                            onError = ::handleError
                    )
        }
    }

    private fun handleExchangeResponse(currencies: CurrenciesResponse) {
        Toast.makeText(this, "Currencies: ${currencies.data.map { "\n${it.id}" }}", Toast.LENGTH_SHORT).show()
    }

    private fun handlePriceResponse(buyPrice: PriceResponse) {
        Toast.makeText(this, "£${buyPrice.data.amount}", Toast.LENGTH_SHORT).show()
    }

    private fun handleError(exception: Throwable) {
        Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_SHORT).show()
    }
}
