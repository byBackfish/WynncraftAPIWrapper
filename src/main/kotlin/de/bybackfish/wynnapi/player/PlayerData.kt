package de.bybackfish.wynnapi.player

import de.bybackfish.wynnapi.player.classes.PlayerClasses
import de.bybackfish.wynnapi.player.other.PlayerGuild
import de.bybackfish.wynnapi.player.other.PlayerMeta
import de.bybackfish.wynnapi.player.ranking.PlayerRanking

class PlayerData {

    val username: String? = null
    val rank: String? = null
    val uuid: String? = null

    val meta: PlayerMeta? = null
    val classes = ArrayList<PlayerClasses>()
    val guild = PlayerGuild()
    val ranking = PlayerRanking()

}