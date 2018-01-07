package julesssss.github.bitcoinmaterial.api

import io.reactivex.Single
import julesssss.github.bitcoinmaterial.data.CurrenciesResponse
import julesssss.github.bitcoinmaterial.data.PriceResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface CoinbaseService {

    @GET("prices/{currencyPair}/spot")
    fun getSpotPrice(
            @Path("currencyPair") currencyPair: String
    ): Single<PriceResponse>

    @GET("prices/BTC-GBP/buy")
    fun getBuyPrice(): Single<PriceResponse>

    @GET("prices/BTC-GBP/sell")
    fun getSellPrice(): Single<PriceResponse>

    @GET("currencies")
    fun getCurrencies(): Single<CurrenciesResponse>

}