package julesssss.github.bitcoinmaterial.data

import com.google.gson.annotations.SerializedName

data class PriceResponse(
        @SerializedName("data") val data: Data
)

data class Data(
        @SerializedName("base") val base: String,
        @SerializedName("currency") val currency: String,
        @SerializedName("amount") val amount: String
)
