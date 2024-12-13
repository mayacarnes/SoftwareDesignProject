package edu.trincoll.jsonplaceholder.ollama


import io.ktor.client.*
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class OllamaRequest(
    val model: String,
    val prompt: String
)

@Serializable
data class OllamaResponse(
    val response: String
)

fun main() = runBlocking {
    val client = HttpClient(CIO)

    // Set the timeout to 40 seconds using Kotlin's withTimeout
    try {
        val response = withTimeout(80_000) {  // 40 seconds in milliseconds
            client.post("http://localhost:11434/api/generate") {
                contentType(ContentType.Application.Json)
                setBody("{\"model\": \"llama3.2:latest\", \"prompt\": \"How many apples can you eat?\"}")
            }
        }
        val responseBody = response.bodyAsText()
        println("Response: ${responseBody}")


    } catch (e: TimeoutCancellationException) {
        println("Request timed out")
    }

    client.close()
}