import de.bybackfish.wynnapi.WynnStats
import de.bybackfish.wynnapi.items.ItemCategory
import kotlin.reflect.KClass

fun main() {
    val stats = WynnStats(
        updateInterval = 3000
    );


    val p = stats.getPlayer("Keldorn")
    if(p == null){
        println("Player null!")
        return;
    }

    println("Player: ${p.data[0].rank}")


}


// just prints all fields and their objects using reflection (used it for testing and seeing if gson would get all elements)
fun printParams(origin: Any, c: KClass<*>, i: Int = 0) {
   c.java.declaredFields.forEach { it ->
       it.isAccessible = true
       val spaces = "   ".repeat(i)
       if(it.type.name.containLC("String") || it.type.name.containLC("int") || it.type.name.containLC("long") || it.type.name.containLC("double") || it.type.name.containLC("float") || it.type.name.containLC("boolean")) {
           println("${spaces}- ${it.name.camelCase()} : ${it.get(origin)}")
       } else {
           if(it.type.name.containLC("List")){
               val list = it.get(origin) as List<*>
                       list.forEachIndexed(){index, item ->
                if(item is String || item is Int || item is Number || item is Boolean){
                    println("${"$spaces  "}-  $item")
                }else{
                    printParams(item!!, item::class, i + 1)
                }

                   if(index == list.size - 1) println()
               }
           } else {
               println(spaces + "# " + it.name.camelCase())
               printParams(it.get(origin), it.get(origin)::class, i + 1);
           }
       }
   }
}


fun String.camelCase(): String {
    return this.split("_").joinToString(" ") { it.capitalize() }
}
fun String.containLC(s: String): Boolean {
    return this.toLowerCase().contains(s.toLowerCase())
}