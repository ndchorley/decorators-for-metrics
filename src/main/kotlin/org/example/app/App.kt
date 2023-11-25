package org.example.app

import org.http4k.core.*
import org.http4k.core.Method.POST
import org.http4k.core.Status.Companion.CREATED
import org.http4k.core.Status.Companion.OK
import org.http4k.format.Jackson.asJsonObject
import org.http4k.routing.bind
import org.http4k.routing.routes

class App : HttpHandler {
    private val router = routes(
        "/posts" bind POST to ::addPost,
        "/posts/{id}" bind Method.GET to ::retrievePost
    )

    override fun invoke(request: Request): Response = router(request)

    private fun addPost(request: Request): Response {
        val body = AddPostResponse(id = 1)

        return Response(CREATED).body(body.asJsonObject().toPrettyString())
    }

    private fun retrievePost(request: Request): Response {
        val body =
            RetrievePostResponse(text = "Some important post!", author = "Alice")

        return Response(OK).body(body.asJsonObject().toPrettyString())
    }
}

private data class AddPostResponse(val id: Int)
private data class RetrievePostResponse(val text: String, val author: String)
