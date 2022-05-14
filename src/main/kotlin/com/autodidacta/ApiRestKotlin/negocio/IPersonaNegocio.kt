package com.autodidacta.ApiRestKotlin.negocio

import com.autodidacta.ApiRestKotlin.model.Persona


interface IPersonaNegocio {

    fun list():List<Persona>
    fun load(id:Long):Persona
    fun save(persona:Persona):Persona
    //cuando se guarde retornara el id de la persona creada
    fun remove(id:Long)
}