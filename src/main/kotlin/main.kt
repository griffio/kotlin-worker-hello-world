import kotlinx.coroutines.MainScope
import kotlinx.coroutines.promise
import org.w3c.fetch.Request
import org.w3c.fetch.Response
import org.w3c.fetch.ResponseInit
import org.w3c.workers.FetchEvent
import org.w3c.workers.ServiceWorkerGlobalScope
// Cloudflare workers are compatible with Service Workers
external val self: ServiceWorkerGlobalScope

val global = MainScope()

fun main() {
    self.addEventListener("fetch", { event ->
        event as FetchEvent

        global.promise {
            helloWorld(event.request)
        }.let { event.respondWith(it) }

    })
}

fun helloWorld(request: Request): Response {
    console.log(request)
    val headers: dynamic = object {}
    headers["content-type"] = "text/plain"
    return Response("Hello World", ResponseInit(headers = headers))
}
