import de.bybackfish.wynnapi.WynnStats
import java.util.*
import kotlin.reflect.KClass

fun main() {
    val stats = WynnStats(
        updateInterval = 3000
    )


    val player = stats.getPlayer("h1red")!!
    println(player.data[0].classes[0].name)

}


// just prints all fields and their objects using reflection (used it for testing and seeing if gson would get all elements)
fun printParams(origin: Any, c: KClass<*>, i: Int = 0) {
    c.java.declaredFields.forEach { it ->
        it.isAccessible = true
        val spaces = "   ".repeat(i)
        if (it.type.name.containsIgnoreCase("String") || it.type.name.containsIgnoreCase("int") || it.type.name.containsIgnoreCase(
                "long"
            ) || it.type.name.containsIgnoreCase("double") || it.type.name.containsIgnoreCase("float") || it.type.name.containsIgnoreCase(
                "boolean"
            )
        ) {
            println("${spaces}- ${it.name.camelCase()} : ${it.get(origin)}")
        } else {
            if (it.type.name.containsIgnoreCase("List")) {
                val list = it.get(origin) as List<*>
                list.forEachIndexed { index, item ->
                    if (item is String || item is Int || item is Number || item is Boolean) {
                        println("${"$spaces  "}-  $item")
                    } else {
                        printParams(item!!, item::class, i + 1)
                    }

                    if (index == list.size - 1) println()
                }
            } else {
                println(spaces + "# " + it.name.camelCase())
                printParams(it.get(origin), it.get(origin)::class, i + 1)
            }
        }
    }
}


fun String.camelCase(): String {
    return this.split("_")
        .joinToString(" ") { it -> it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } }
}

fun String.containsIgnoreCase(s: String): Boolean {
    return this.lowercase().contains(s.lowercase())
}