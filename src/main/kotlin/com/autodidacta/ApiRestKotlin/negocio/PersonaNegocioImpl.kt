package com.autodidacta.ApiRestKotlin.negocio

import com.autodidacta.ApiRestKotlin.dao.PersonaRepository
import com.autodidacta.ApiRestKotlin.exception.NegocioException
import com.autodidacta.ApiRestKotlin.exception.NotFoundException
import com.autodidacta.ApiRestKotlin.model.Persona
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class PersonaNegocioImpl : IPersonaNegocio {

    @Autowired
    val repositorio:PersonaRepository? = null

    @Throws(NegocioException::class)
    override fun list(): List<Persona> {
        try {
            return repositorio!!.findAll()
        }catch (e:Exception){
            throw NegocioException(e.message)
        }
    }
    @Throws(NegocioException::class,NotFoundException::class)
    override fun load(id: Long): Persona {
        val op:Optional<Persona>
        try{
            op = repositorio!!.findById(id)
        }catch (e:Exception){
            throw NegocioException(e.message)
        }

        if(!op.isPresent) { //si el valor op no esta presenta tiro un trhows
            throw NotFoundException("No se encontro la persona con id $id")
        }
        return op.get()
        //retorna objeto de tipo persona
    }

    @Throws(NegocioException::class)
    override fun save(persona: Persona): Persona {
        try {
            return repositorio!!.save(persona)
        }catch (e:Exception){
            throw NegocioException(e.message)
        }
    }

    @Throws(NegocioException::class,NotFoundException::class)
    override fun remove(id: Long) {
        val op:Optional<Persona>
        try {
            op = repositorio!!.findById(id)
        }catch (e:Exception){
            throw NegocioException(e.message)
        }

        if(!op.isPresent){
            throw NotFoundException("No se encontro persona con id $id")
        }else{
            try {
                repositorio!!.deleteById(id)
            }catch (e:Exception){
                throw NegocioException(e.message)
            }
        }
    }

}