package de.bybackfish.wynnapi.player

class Player {

    private val data = ArrayList<PlayerData>()
    val version: String? = null
    val code: Int = 0

    public fun getData() = data[0]

}