package julesssss.github.bitcoinmaterial

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_price.*

class PriceActivity : AppCompatActivity() {

    private val viewModel: PriceViewModel = PriceViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_price)

        setupUi()
    }

    private fun setupUi() {
        viewModel.getBitcoinPrice()
                .subscribeBy(
                        onSuccess = {
                            textPrice.text = "£${it.data.amount}"
                            textTitle.visibility = View.VISIBLE
                            textPrice.visibility = View.VISIBLE
                        },
                        onError = ::handleError
                )

        viewModel.setupGraph(chart)
    }

    private fun handleError(exception: Throwable) {
        Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_SHORT).show()
    }

}