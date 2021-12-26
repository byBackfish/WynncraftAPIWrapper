package de.bybackfish.wynnapi.network

import com.google.gson.annotations.SerializedName
import de.bybackfish.wynnapi.shared.RequestData

class PlayerSum {

    val request = RequestData()

    @SerializedName("players_online") val playerCount: Int = 0

}