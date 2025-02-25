package com.unam.fciencias.bern_demo_api.mascota

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/mascotas")
class MascotaController {
    @GetMapping
    fun retrieveMascota(): ResponseEntity<Mascota> {

        val miMascota = Mascota(tipo = "perro", name = "Pelusa", peso = "6kg")
        return ResponseEntity.ok(miMascota)
    }

    /**
     * Puedo tener mas de un metodo HTTP repetido en mi controller siempre y cuando cada uno
     * tenga diferente URL.
     *
     * Por ejemplo, en este caso tengo dos metodos GET en un mismo controller:
     *
     * http://localhost:8080/v1/mascotas mandará a llamar a [retrieveMascota]
     * http://localhost:8080/v1/mascotas/exotica mandará a llamar a [retrieveMascotaExotica]
     */
    @GetMapping("/exotica")
    fun retrieveMascotaExotica(): ResponseEntity<Mascota> {

        val miMascota = Mascota(tipo = "serpiente", name = "La Cobra Gonzalez", peso = "4kg")
        return ResponseEntity.ok(miMascota)
    }

    @PostMapping
    fun createMascota(@RequestBody mascotaBody: MascotaBody): ResponseEntity<Mascota> {

        val miMascota = Mascota(
            tipo = mascotaBody.tipo,
            name = mascotaBody.name,
            peso = mascotaBody.peso
        )
        return ResponseEntity.ok(miMascota)
    }


}