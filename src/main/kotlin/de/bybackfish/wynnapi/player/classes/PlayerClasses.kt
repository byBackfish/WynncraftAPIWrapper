package de.bybackfish.wynnapi.player.classes

import de.bybackfish.wynnapi.player.classes.dungeons.PlayerDungeons
import de.bybackfish.wynnapi.player.classes.other.PlayerGamemode
import de.bybackfish.wynnapi.player.classes.other.PlayerPVP
import de.bybackfish.wynnapi.player.classes.professions.PlayerProfessions
import de.bybackfish.wynnapi.player.classes.quests.PlayerQuests
import de.bybackfish.wynnapi.player.classes.raid.PlayerRaids
import de.bybackfish.wynnapi.player.classes.skills.PlayerSkills

class PlayerClasses {

    val name: String? = null
    val level: Int = 0
    val dungeons = PlayerDungeons()
    val raids = PlayerRaids()
    val quests = PlayerQuests()
    val pvp = PlayerPVP()

    val itemsIdentified: Long = 0
    val mobsKilled: Long = 0
    val chestsFound: Long = 0
    val blocksWalked: Long = 0

    val logins: Long = 0
    val deaths: Long = 0
    val playtime: Long = 0

    val gamemode = PlayerGamemode()
    val skills = PlayerSkills()
    val professions = PlayerProfessions()

    val discoveries: Int = 0
    val eventsWon: Int = 0
    val preEconomyUpdate: Boolean = false


}