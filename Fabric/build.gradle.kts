plugins {
    id("fuzs.multiloader.multiloader-convention-plugins-fabric")
}

dependencies {
    modApi(sharedLibs.fabricapi.fabric)
    modApi(sharedLibs.puzzleslib.fabric)
    modLocalRuntime(sharedLibs.cloth.fabric)
    modLocalRuntime(sharedLibs.appleskin.fabric)
}
