package com.example.flowmessageapp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MessageRepository {
    private val greetings = listOf(
        "Hello",           // English
        "Zdravo",          // Serbian/Bosnian/Croatian
        "Hallo",           // German
        "Salut",           // French
        "Ciao",            // Italian
        "Hola",            // Spanish
        "Hei",             // Norwegian
        "Olá",             // Portuguese
        "こんにちは (Konnichiwa)", // Japanese
        "Привет (Privet)", // Russian
        "안녕하세요 (Annyeonghaseyo)", // Korean
        "你好 (Nǐ hǎo)",   // Chinese (Mandarin)
        "Merhaba",         // Turkish
        "Shalom",          // Hebrew
        "Namaste",         // Hindi
        "Szia",            // Hungarian
        "Hej",             // Swedish/Danish
        "Salam",           // Arabic
    )
    fun messageStream(): Flow<String> = flow {
        var index = 0
        while (true) {
            emit(greetings[index])
            index = (index + 1) % greetings.size
            delay(3000)
        }
    }
}
