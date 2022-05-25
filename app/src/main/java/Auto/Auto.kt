package Auto


import java.util.*

class Auto {
    private var codigo=0
    private var nombre=""
    private var numero=""
    private var fecha=""
    constructor()
    constructor(codigo: Int, nombre: String, numero: String, fecha: String) {
        this.codigo = codigo
        this.nombre = nombre
        this.numero = numero
        this.fecha = fecha
    }
    fun getcodigo()=codigo
    fun getnombre()=nombre
    fun getnumero()=numero
    fun getfecha()=fecha


    override fun toString(): String {
        return "Libro(codigo=$codigo, nombre='$nombre', numero='$numero', fecha='$fecha')"
    }



}