package com.autodidacta.ApiRestKotlin.model

import java.time.LocalDate
import java.util.Date
import javax.persistence.*

@Entity
@Table(name="persona")
data class Persona(
    var dni:Long=0,
    var nombre:String="",
    var apellido:String="",
    var fechaNac:LocalDate?=null
) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long = 0

}