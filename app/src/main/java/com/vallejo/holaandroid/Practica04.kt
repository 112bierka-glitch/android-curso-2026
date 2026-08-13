package com.vallejo.holaandroid

fun generarRecibo(
    nombreCliente: String,
    monto: Double,
    descuento: Double = 0.0,          
    impuesto: Double = 0.18,        
    moneda: String = "DOP"
): String {
    val subtotal = monto - (monto * descuento)
    val impuestoVal = subtotal * impuesto
    val total = subtotal + impuestoVal

    val descPorcentaje = (descuento * 100).toInt()
    val impPorcentaje = (impuesto * 100).toInt()

    return """
  
    RECIBO — $moneda
    Cliente   : $nombreCliente
    Subtotal  : DOP %.2f
    Descuento : $descPorcentaje% (DOP %.2f)
    Impuesto  : $impPorcentaje% (DOP %.2f)
    Total     : DOP %.2f
  
    """.trimIndent().format(
        subtotal,
        monto * descuento,
        impuestoVal,
        total
    )
}


fun String.esEmail(): Boolean = contains('@') && contains('.')

fun String.aTitulo(): String = split(' ').joinToString(" ") { palabra ->
    palabra.replaceFirstChar { c -> c.uppercase() }
}

fun String.mascararTarjeta(): String {
    if (length <= 4) return this
    val ultimosCuatro = takeLast(4)
    val ocultos = "*".repeat(length - 4)
    return ocultos + ultimosCuatro
}


fun List<Int>.promedio(): Double = if (isEmpty()) 0.0 else sum().toDouble() / size

fun List<Int>.aprobados(): List<Int> = filter { it >= 70 }

fun List<Int>.estadisticas() {
    if (isEmpty()) {
        println("Lista vacía, sin estadísticas disponibles.")
        return
    }
    val min = minOrNull() ?: 0
    val max = maxOrNull() ?: 0
    val prom = promedio()
    println("Estadísticas -> Mínima: $min | Máxima: $max | Promedio: %.2f".format(prom))
}



data class Pedido(
    val id: Int,
    var producto: String,
    var precio: Double,
    var activo: Boolean = true
)

fun filtrarPedidos(pedidos: List<Pedido>, criterio: (Pedido) -> Boolean): List<Pedido> {
    return pedidos.filter(criterio)
}

fun main() {
    println("=== EJERCICIO 1: PARÁMETROS NOMBRADOS ===")
    println(generarRecibo(nombreCliente = "Ana López", monto = 1500.0, descuento = 0.10))
    println(generarRecibo("Pedro Ruiz", 2800.0))
    println(generarRecibo(monto = 500.0, nombreCliente = "Empresa ABC", moneda = "USD", impuesto = 0.0))

    println("\n=== EJERCICIO 2: FUNCIONES DE EXTENSIÓN ===")
    println("Correo 'test@mail.com' es válido: ${"test@mail.com".esEmail()}")
    println("Correo 'noesmail' es válido: ${"noesmail".esEmail()}")
    println("Título: ${"programacion movil".aTitulo()}")

    val tarjeta = "1234567890123456"
    println("Tarjeta enmascarada: ${tarjeta.mascararTarjeta()}")

    val notas = listOf(85, 92, 61, 78, 45, 90)
    println("Notas aprobadas: ${notas.aprobados()}")
    notas.estadisticas()

    println("\n=== EJERCICIO 3: FUNCIONES DE ORDEN SUPERIOR Y ÁMBITO ===")
    val p1 = Pedido(1, "", 0.0).apply {
        producto = "Smartphone"
        precio = 25000.0
    }

    val pedidos = listOf(
        p1,
        Pedido(2, "Audífonos", 1800.0),
        Pedido(3, "Tablet", 18000.0, activo = false),
        Pedido(4, "Cargador", 850.0),
    )

    val activos = filtrarPedidos(pedidos) { it.activo }
    val caros = filtrarPedidos(pedidos) { it.precio > 5000.0 && it.activo }

    println("Pedidos activos: ${activos.size}")
    println("Pedidos caros:   ${caros.map { it.producto }}")

    val resumen = pedidos
        .filter { it.activo }
        .map { it.precio }
        .also { println("Precios activos: $it") }
        .sum()

    println("Total activos: DOP %.2f".format(resumen))

    pedidos.filter { it.activo }.maxByOrNull { it.precio }?.let { pedidoCaro ->
        println("Producto activo más caro: '${pedidoCaro.producto}' con un precio de DOP %.2f".format(pedidoCaro.precio))
    }
}
