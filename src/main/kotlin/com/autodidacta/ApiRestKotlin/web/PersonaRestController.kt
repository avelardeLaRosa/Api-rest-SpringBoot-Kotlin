package com.autodidacta.ApiRestKotlin.web

import com.autodidacta.ApiRestKotlin.exception.NegocioException
import com.autodidacta.ApiRestKotlin.exception.NotFoundException
import com.autodidacta.ApiRestKotlin.model.Persona
import com.autodidacta.ApiRestKotlin.negocio.IPersonaNegocio
import com.autodidacta.ApiRestKotlin.utils.Constantes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Constantes.URL_BASE_PERSONAS)
class PersonaRestController {

    @Autowired
    val personaNegocio:IPersonaNegocio?=null

    @GetMapping("")
    fun list():ResponseEntity<List<Persona>>{
        //retorna un respnseentitu con una lista de personas
        return try {
            ResponseEntity(personaNegocio!!.list(),HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            //no encontro en el servidor las personas para listar
        }
    }

    @GetMapping("/{id}")
    fun load(@PathVariable("id") id:Long):ResponseEntity<Persona>{
        return try {
            ResponseEntity(personaNegocio!!.load(id),HttpStatus.OK)
        }catch (e:NegocioException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
            //no le encuentra en el servidor
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
            //error en el servidor
        }
    }

    @PostMapping("")
    fun insert(@RequestBody persona:Persona):ResponseEntity<Any>{
        //Request body envia un obj de tipo persona
        //puede retorn cualquier tipo de dato
        return try{
            personaNegocio!!.save(persona)
            val responseHeader = HttpHeaders()
            responseHeader.set("location",Constantes.URL_BASE_PERSONAS+"/"+persona.id)
            ResponseEntity(responseHeader,HttpStatus.CREATED)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody persona:Persona):ResponseEntity<Any>{
        return try{
            personaNegocio!!.save(persona)
            ResponseEntity(HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id:Long):ResponseEntity<Any>{
        return try{
            personaNegocio!!.remove(id)
            ResponseEntity(HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


}