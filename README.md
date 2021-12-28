# WynncraftAPIWrapper
* Simple wrapper to get Wynncraft Stats of a player or a guild and more in Java!

# Installation

```gradle
// Add this to your build.gradle
repositories {
			maven { url 'https://jitpack.io' }
}

dependencies {
	        implementation 'com.github.byBackfish:WynncraftAPiWrapper:Tag'
}
```

* See more on [Jitpack](https://jitpack.io/#byBackfish/WynncraftAPiWrapper/-SNAPSHOT)

# Usage

```kotlin
val stats = WynnStats()
val player = stats.getPlayer("Keldorn") ?: return println("Player not found!")
val data = player.getData() 
val classes = player.classes
val firstClass = classes[0]
// do stuff here

```

* Works similar in Java. Above is a Kotlin example
