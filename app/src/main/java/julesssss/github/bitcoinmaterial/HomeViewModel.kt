package julesssss.github.bitcoinmaterial

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import julesssss.github.bitcoinmaterial.api.ServiceProvider.coinbaseService
import julesssss.github.bitcoinmaterial.data.CurrencyResponse
import julesssss.github.bitcoinmaterial.data.PriceResponse
import uk.co.ostmodern.util.SchedulerProvider

class HomeViewModel {

    private val scheduleProvider by lazy { SchedulerProvider(Schedulers.io(), AndroidSchedulers.mainThread()) }

    var currencyPair: String = "BTC-GBP"

    fun getBuyPrice(): Single<PriceResponse> = coinbaseService.getBuyPrice()
            .compose(scheduleProvider.getSchedulersForSingle())

    fun getSellPrice(): Single<PriceResponse> = coinbaseService.getSellPrice()
            .compose(scheduleProvider.getSchedulersForSingle())

    fun getSpotPrice(): Single<PriceResponse> = coinbaseService.getSpotPrice(currencyPair)
            .compose(scheduleProvider.getSchedulersForSingle())

    fun getCurrencies(): Single<CurrencyResponse> = coinbaseService.getCurrencies()
            .compose(scheduleProvider.getSchedulersForSingle())

}