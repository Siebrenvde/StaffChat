dependencies {
    implementation(project(":common"))
    compileOnly("net.md-5:bungeecord-api:1.21-R0.1-SNAPSHOT")
    implementation("net.kyori:adventure-platform-bungeecord:4.3.4")
}

tasks.withType(ProcessResources::class) {
    filesMatching("bungee.yml") {
        expand("version" to version)
    }
}
