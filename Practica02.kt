package com.vallejo.holaandroid

// Ej. 2: Clasificador de notas universitarias (Acepta Double para el reto adicional)
fun clasificarNota(nota: Double): String = when {
    nota < 0.0 || nota > 100.0 -> "Nota inválida"
    nota >= 90.0 -> "Sobresaliente (A)"
    nota >= 80.0 -> "Muy Bueno (B)"
    nota >= 70.0 -> "Bueno (C)"
    nota >= 60.0 -> "Aprobado (D)"
    else         -> "Reprobado (F)"
}


fun clasificarNota(nota: Int): String = clasificarNota(nota.toDouble())


fun fizzBuzz() {
    println("=== FizzBuzz (1 al 30) ===")
    for (i in 1..30) {
        val resultado = when {
            i % 15 == 0 -> "FizzBuzz"
            i % 3  == 0 -> "Fizz"
            i % 5  == 0 -> "Buzz"
            else        -> "$i"
        }
        print("$resultado\t")
        if (i % 10 == 0) println()   
    }
}

// Ej. 3: Parte B — Tabla de multiplicar
fun tablaMultiplicar(n: Int) {
    println("\n=== Tabla del $n ===")
    for (i in 1..10) {
        println("$n x $i = ${n * i}")
    }
}

fun main() {
    println("--- EJERCICIO 1 ---")
    val nombreCurso = "Programación Móvil I"
    val añoInicio: Int = 2026

    var calificacion: Double = 95.5
    calificacion = 88.0

    println("Curso: $nombreCurso, Año: $añoInicio")
    println("Calificación actual: ${calificacion * 0.3} (30%)")

    val nombreCompleto = "Bierka Vallejo"

    
    var edadActual = 21

    
    println("Me llamo $nombreCompleto y tengo $edadActual años")

    println("\n--- EJERCICIO 2 ---")
    val notas = listOf(100.0, 92.5, 85.0, 73.2, 61.0, 45.0, -5.0)
    for (nota in notas) {
        println("Nota $nota -> ${clasificarNota(nota)}")
    }

    println("\n--- EJERCICIO 3 ---")
    fizzBuzz()

    
    print("\nIngresa un número para la tabla de multiplicar: ")
    val entrada = readLine()
    val num = entrada?.toIntOrNull() ?: 7
    tablaMultiplicar(num)
}
