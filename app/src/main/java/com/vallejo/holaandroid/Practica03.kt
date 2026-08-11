package com.vallejo.holaandroid

// EJERCICIO 1: Validador de formulario

data class ResultadoValidacion(val esValido: Boolean, val mensaje: String)

fun validarUsuario(nombre: String?, email: String?): ResultadoValidacion {
    val nombreLimpio = nombre?.trim()
    val longitudNombre = nombreLimpio?.length ?: 0

    if (longitudNombre < 3) {
        return ResultadoValidacion(false, "Nombre muy corto o vacío")
    }

    // TODO 1: Validar que email no sea null y contenga '@'
    val esEmailValido = email?.contains('@') ?: false

    // TODO 2: Si email es inválido, retornar error
    if (!esEmailValido) {
        return ResultadoValidacion(false, "Email inválido")
    }

    return ResultadoValidacion(true, "Usuario '$nombreLimpio' registrado correctamente")
}

// EJERCICIO 2: Registro de calificaciones

fun analizarNotas(notas: List<Int>) {
    if (notas.isEmpty()) {
        println("Sin datos")
        return
    }

    val promedio = notas.average()
    val aprobados = notas.filter { it >= 70 }
    val reprobados = notas.filter { it < 70 }
    val ordenadas = notas.sortedByDescending { it }
    val mayor = ordenadas.first()
    val menor = ordenadas.last()

    println("Total estudiantes : ${notas.size}")
    println("Promedio          : %.2f".format(promedio))
    println("Aprobados         : ${aprobados.size}")
    println("Reprobados        : ${reprobados.size}")
    println("Nota más alta     : $mayor")
    println("Nota más baja     : $menor")

    // TODO Ej 2A: Imprimir la lista de notas aprobadas ordenadas de mayor a menor
    val aprobadasOrdenadas = aprobados.sortedByDescending { it }
    println("Notas aprobadas (ordenadas) : $aprobadosOrdenadas")

    // TODO Ej 2B: Calcular e imprimir el porcentaje de aprobados
    val porcentajeAprobados = (aprobados.size.toDouble() / notas.size) * 100
    println("Porcentaje de aprobados    : %.2f%%".format(porcentajeAprobados))
}

// FUNCIÓN MAIN PRINCIPAL
fun main() {
    println("=== EJERCICIO 1: NULL-SAFETY ===")
    val casos = listOf(
        Pair(null, "test@mail.com"),
        Pair("Ana", null),
        Pair("Bo", "noesmail"),
        Pair("Carlos Pérez", "carlos@ejemplo.com")
    )
    casos.forEach { (nombre, email) ->
        val r = validarUsuario(nombre, email)
        println("[${if (r.esValido) "OK" else "ERROR"}] ${r.mensaje}")
    }

    println("\n=== EJERCICIO 2: COLECCIONES Y NOTAS ===")
    val notas = mutableListOf(85, 92, 61, 78, 45, 90, 73, 55, 88, 67)
    analizarNotas(notas)

    notas.addAll(listOf(95, 40, 82))
    println("\n--- Con nuevas notas ---")
    analizarNotas(notas)

    println("\n=== EJERCICIO 3: MAP Y GROUPBY ===")
    val estudiantes = mapOf(
        "Ana López" to 92,
        "Carlos Ruiz" to 65,
        "María Díaz" to 88,
        "Pedro Soto" to 55,
        "Laura Vega" to 75,
        "Juan Torres" to 48,
        "Sofía Reyes" to 91
    )

    val grupos = estudiantes.entries.groupBy { (_, nota) ->
        when {
            nota >= 90 -> "Sobresaliente"
            nota >= 70 -> "Aprobado"
            else -> "Reprobado"
        }
    }

    grupos.forEach { (categoria, lista) ->
        println("\n$categoria:")
        lista.forEach { (nombre, nota) ->
            println("  - $nombre: $nota")
        }
    }

    // TODO Ej 3A: Imprimir cuántos estudiantes hay en cada categoría
    println("\n--- Resumen por categorías ---")
    grupos.forEach { (categoria, lista) ->
        println("$categoria: ${lista.size} estudiante(s)")
    }

    // TODO Ej 3B: Imprimir el nombre del estudiante con la nota más alta
    val mejorEstudiante = estudiantes.maxByOrNull { it.value }
    mejorEstudiante?.let { (nombre, nota) ->
        println("\nEstudiante con la nota más alta: $nombre ($nota pts)")
    }
}
