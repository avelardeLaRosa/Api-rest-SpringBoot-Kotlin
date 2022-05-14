package com.autodidacta.ApiRestKotlin

import com.autodidacta.ApiRestKotlin.dao.PersonaRepository
import com.autodidacta.ApiRestKotlin.model.Persona
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SpringBootApplication
class ApiRestKotlinApplication:CommandLineRunner{


	@Autowired
	val repositorio:PersonaRepository?=null

	override fun run(vararg args: String?) {
		val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
		val persona1 = Persona(75755,"alexander","velarde", LocalDate.parse("10-06-2022",formatter))

		repositorio!!.save(persona1)
	}

}

fun main(args: Array<String>) {
	runApplication<ApiRestKotlinApplication>(*args)
}
