package helloworld

import org.springframework.batch.item.ItemWriter

class HelloWorldItemWriter : ItemWriter<String> {

    override fun write(items: List<String>) {
        println("hello, i'm item writer, write item ->$items ")
    }
}
