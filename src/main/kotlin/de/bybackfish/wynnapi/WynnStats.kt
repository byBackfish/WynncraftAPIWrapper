package de.bybackfish.wynnapi

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import de.bybackfish.wynnapi.player.Player
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.net.URLConnection


class WynnStats(
    private val url: String = "https://api.wynncraft.com/v2/player/",
    private val updateInterval: Int = 300000,
    ) {

    private val playerCache: HashMap<String, Pair<Long, Player>> = HashMap()

    fun getPlayer(nameOrUUID: String): Player? {
        val name = nameOrUUID.replace("-", "")
        val gson = Gson()
        val jsonUrl = "$url/$name/stats"
        var json: JsonObject? = null;
       try{
           json = getFromUrl(jsonUrl)
       }catch (e: Exception){
        return null;
       }
        return gson.fromJson(json, Player::class.java)
    }

    fun getPlayerCached(username: String): Player? {
        if (playerCache.containsKey(username)) {
            val pair: Pair<Long, Player>? = playerCache[username]
            if (pair != null) {
                if (pair.first + updateInterval <= System.currentTimeMillis()) {
                    val playerData: Player = getPlayer(username) ?: return null
                    playerCache[username!!] = Pair(System.currentTimeMillis(), playerData)
                    println("Found outdated Player in Cache")
                    return playerData
                }
            }
            println("Found Player in Cache")
            return playerCache[username]!!.second
        }
        val playerData: Player = getPlayer(username) ?: return null
        println("Registering new Player in Cache")
        playerCache[username] = Pair(System.currentTimeMillis(), playerData)
        return playerData
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
        val jElement = JsonParser().parse(getPage(url))
        return jElement.asJsonObject
    }

    @Throws(IOException::class)
    private fun getPage(url: String): String {
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