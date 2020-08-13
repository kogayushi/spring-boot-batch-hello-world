package helloworld

import org.springframework.batch.core.ChunkListener
import org.springframework.batch.core.scope.context.ChunkContext

class HelloWorldChunkListener : ChunkListener {

    override fun beforeChunk(context: ChunkContext) {
        println("ChunkListener#beforeChunk")
    }

    override fun afterChunk(context: ChunkContext) {
        println("ChunkListener#afterChunk")
    }

    override fun afterChunkError(context: ChunkContext) {
        println("ChunkListener#afterChunkError")
    }
}
