package org.example.app

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.CREATED
import org.http4k.core.Status.Companion.OK
import org.http4k.format.Jackson.asJsonObject
import org.http4k.hamkrest.hasStatus
import org.junit.jupiter.api.Test

class AppTest {
    @Test
    fun `a post can be added and retrieved by ID`() {
        val app = App()

        val text = "Some important post!"
        val author = "Alice"

        val addRequest =
            Request(POST, "/posts")
                .body("""{"text": "$text," "author": "$author"}""")

        val addResponse: Response = app(addRequest)

        assertThat(addResponse, hasStatus(CREATED))

        val id =
            addResponse
                .bodyString()
                .asJsonObject()
                .at("/id")
                .asText()

        val retrieveRequest = Request(GET, "/posts/$id")

        val retrieveResponse: Response = app(retrieveRequest)

        assertThat(retrieveResponse, hasStatus(OK))

        val retrievedJSON = retrieveResponse.bodyString().asJsonObject()
        assertThat(
            retrievedJSON.at("/text").asText(),
            equalTo(text)
        )
        assertThat(
            retrievedJSON.at("/author").asText(),
            equalTo(author)
        )
    }
}