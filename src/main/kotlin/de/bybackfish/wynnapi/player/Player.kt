package de.bybackfish.wynnapi.player

class Player {

    val data = ArrayList<PlayerData>()
    val version: String? = null
    val code: Int = 0

    fun getData() = data[0]

}