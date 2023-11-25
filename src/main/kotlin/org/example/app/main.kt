package org.example.app

import org.http4k.server.Jetty
import org.http4k.server.asServer

fun main() {
    App().asServer(Jetty(port = 8080)).start()
}
