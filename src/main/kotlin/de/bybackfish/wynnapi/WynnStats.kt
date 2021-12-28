package de.bybackfish.wynnapi

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import de.bybackfish.wynnapi.guilds.Guild
import de.bybackfish.wynnapi.guilds.GuildList
import de.bybackfish.wynnapi.items.ItemCategory
import de.bybackfish.wynnapi.items.Items
import de.bybackfish.wynnapi.network.PlayerSum
import de.bybackfish.wynnapi.network.ServerList
import de.bybackfish.wynnapi.player.Player
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection


class WynnStats(
    private val url: String = "https://api.wynncraft.com/",
) {

    fun getPlayer(nameOrUUID: String): Player? {
        val url = this.url + "v2/player/$nameOrUUID/stats"
        return fetch<Player>(url)
    }

    fun getGuilds(): GuildList? {
        val url = "${this.url}public_api.php?action=guildList"
        return fetch<GuildList>(url)
    }

    fun getGuild(name: String): Guild? {
        val url = "${this.url}public_api.php?action=guildStats&command=$name"
        return fetch<Guild>(url)
    }

    fun getItemsByName(name: String): Items? {
        val url = "${this.url}public_api.php?action=itemDB&search=$name"
        return fetch<Items>(url)
    }

    fun getItemsByCategory(category: ItemCategory): Items? {
        val url = "${this.url}public_api.php?action=itemDB&category=${category.name.lowercase()}"
        return fetch<Items>(url)
    }

    fun getServers(): ServerList? {
        val gson = Gson()
        val json = getFromUrl("${this.url}public_api.php?action=onlinePlayers")
        val list = gson.fromJson(json, ServerList::class.java)

        for (mutableEntry in json.entrySet()) {
            if (mutableEntry.key != "request") {
                val server = mutableEntry.key
                val players = gson.fromJson(mutableEntry.value.toString(), Array<String>::class.java)
                list.servers[server] = players
            }
        }
        return list
    }

    fun getPlayerCount(): PlayerSum? {
        val url = "${this.url}public_api.php?action=onlinePlayersSum"
        return fetch<PlayerSum>(url)
    }


    private inline fun <reified T> fetch(url: String): T? {
        val gson = Gson()
        return try {
            val json = getFromUrl(url)
            gson.fromJson(json, T::class.java)
        } catch (e: Exception) {
            null
        }
    }

    private fun getFromUrl(url: String): JsonObject {
        val json = readJsonUrl(url)
        if (json.has("error")) {
            throw Exception("Error while getting data from $url: ${json.get("error").asString}")
        }
        return json
    }

    @Throws(IOException::class)
    private fun readJsonUrl(url: String): JsonObject {
        val jElement = JsonParser().parse(getContents(url))
        return jElement.asJsonObject
    }

    @Throws(IOException::class)
    private fun getContents(url: String): String {
        val url1 = URL(url)
        val conn: URLConnection = url1.openConnection()
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0")
        conn.connect()
        val serverResponse = BufferedReader(InputStreamReader(conn.getInputStream()))
        val response = serverResponse.readLine()
        serverResponse.close()
        return response
    }

}