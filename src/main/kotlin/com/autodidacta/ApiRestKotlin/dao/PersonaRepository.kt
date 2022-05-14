package com.autodidacta.ApiRestKotlin.dao

import com.autodidacta.ApiRestKotlin.model.Persona
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonaRepository : JpaRepository<Persona,Long> {



}