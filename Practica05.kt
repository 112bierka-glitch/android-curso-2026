
interface Evaluable {
    val notaMinima: Int get() = 70
    fun estaAprobado(nota: Int): Boolean = nota >= notaMinima
    fun descripcion(): String
}


open class Persona(
    val nombre: String,
    val apellido: String,
    val cedula: String,
) {
    val nombreCompleto: String
        get() = "$nombre $apellido"

    init {
        require(cedula.length == 11) { "Cédula debe tener 11 dígitos" }
    }

    open fun presentarse(): String = "Soy $nombreCompleto, cédula $cedula"

    override fun toString(): String = nombreCompleto
}


class Estudiante(
    nombre: String,
    apellido: String,
    cedula: String,
    val matricula: String,
    val carrera: String,
) : Persona(nombre, apellido, cedula), Evaluable {

    private val _notas = mutableListOf<Int>()
    val notas: List<Int> get() = _notas

    fun agregarNota(nota: Int) {
        require(nota in 0..100) { "Nota fuera de rango" }
        _notas.add(nota)
    }

    val promedio: Double
        get() = if (_notas.isEmpty()) 0.0 else _notas.average()

    override fun descripcion() = "Estudiante: $nombreCompleto | Promedio: %.1f".format(promedio)

    override fun presentarse(): String =
        super.presentarse() + " | Matrícula: $matricula | Carrera: $carrera"
}


class Docente(
    nombre: String,
    apellido: String,
    cedula: String,
    val departamento: String,
    val anosExperiencia: Int
) : Persona(nombre, apellido, cedula), Evaluable {

    override fun presentarse(): String =
        super.presentarse() + " | Departamento: $departamento | Experiencia: $anosExperiencia años"

    override fun descripcion(): String =
        "Docente: $nombreCompleto | Depto: $departamento"
}


fun imprimirReporte(personas: List<Persona>) {
    println("\n===== REPORTE DEL SISTEMA =====")
    personas.forEach { persona ->
        println(persona.presentarse())

        if (persona is Evaluable) {
            println("  → ${persona.descripcion()}")
        }
        println("-".repeat(40))
    }
}

fun main() {
    val est1 = Estudiante("Ana", "López", "00112345678", "2024-001", "Ing. Software")
    est1.agregarNota(85)
    est1.agregarNota(92)
    est1.agregarNota(78)

    val est2 = Estudiante("Carlos", "Ruiz", "00198765432", "2024-002", "Ing. Software")
    est2.agregarNota(55)
    est2.agregarNota(68)

    val doc1 = Docente("María", "Gómez", "00111223344", "Tecnología", 8)
    val doc2 = Docente("Pedro", "Martínez", "00155667788", "Ciencias Básicas", 12)

    val personas: List<Persona> = listOf(est1, est2, doc1, doc2)
    imprimirReporte(personas)

    val estudiantesAprobados = personas
        .filterIsInstance<Estudiante>()
        .filter { it.estaAprobado(it.promedio.toInt()) }
        
    println("\nEstudiantes aprobados: ${estudiantesAprobados.map { it.nombre }}")
}
