package de.bybackfish.wynnapi.guilds

import de.bybackfish.wynnapi.shared.RequestData

class Guild {

    val name: String? = null
    val prefix: String? = null
    val members = ArrayList<GuildMember>()
    val xp: Float = 0.0f
    val level: Int = 0
    val created: String? = null
    val createdFriendly: String? = null
    val territories: Int = 0
    val banner = GuildBanner();

    val request = RequestData()

}