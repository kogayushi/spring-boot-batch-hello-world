package helloworld

import org.springframework.batch.item.ItemProcessor

class HelloWorldItemProcessor : ItemProcessor<String, String> {

    override fun process(item: String): String {
        println("hello, i'm item processor, processing item ->$item ")
        return "$item via item processor"
    }
}
