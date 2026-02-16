package com.example.rfcapp

fun calcularRFC(nombre: String, apellidoPaterno: String, apellidoMaterno: String, fecha: String): String{
    if(nombre.isBlank() || apellidoPaterno.isBlank() || fecha.length < 10){
        return "Datos incompletos"
    }

    try {
        val ap = apellidoPaterno.uppercase()
        val am = if (apellidoMaterno.isBlank()) "X" else apellidoMaterno.uppercase()
        val nom = nombre.uppercase()

        //Primer letra  apellido paterno
        val letra1 = ap.first()

        //Primera vocal interna
        val vocalInterna = ap.drop(1).firstOrNull { it in "AEIOU" } ?: 'X'

        // Apellido materno
        val letra3 = am.first()

        //Nombre
        val nombres = nom.split(" ")
        val nombrePrincipal = if (nombres.size > 1 && (nombres[0] == "JOSÉ" || nombres[0] == "MARÍA" || nombres[0] == "JOSE" || nombres[0] == "MARIA")) {
            nombres[1]
        } else {
            nombres[0]
        }

        val letra4 = nombrePrincipal.first()

        //Fecha
        val partes = fecha.split("-")

        if (partes.size != 3) return ""

        val anio = partes[0]
        val mes = partes[1]
        val dia = partes[2]

        val mesInt = mes.toIntOrNull() ?: return ""
        val diaInt = dia.toIntOrNull() ?: return ""

        if (mesInt !in 1..12) return ""
        if (diaInt !in 1..31) return ""

        val fechaFormateada = anio.takeLast(2) + mes.padStart(2, '0') + dia.padStart(2, '0')


        val homoclave = "XXX"
        var rfc = "$letra1$vocalInterna$letra3$letra4$fechaFormateada$homoclave"

        //Palabras prohibidas
        val prohibidas = listOf(
            "BUEI","KAKA","LOCA","PUTO","QLOX","COJE","KOGE",
            "MAME","PEDA","BUEY","FETO","GUEY","JOTO","KAGO","MOCO","PENE","CACA"
        )
        val primeras4 = rfc.substring(0,4)

        if (prohibidas.contains(primeras4)) {
            rfc = rfc.substring(0,3) + "X" + rfc.substring(4)
        }

        return rfc
    }catch (e: Exception){
        return "FORMATO INVÁLIDO"
    }
}

fun rfcEnConstruccion(
    nombre: String,
    apellidoPaterno: String,
    apellidoMaterno: String,
    fecha: String
): String {

    val ap = apellidoPaterno.uppercase()
    val am = if (apellidoMaterno.isBlank()) "X" else apellidoMaterno.uppercase()
    val nom = nombre.uppercase()

    val l1 = ap.firstOrNull()?.toString() ?: "X"
    val l2 = ap.drop(1).firstOrNull { it in "AEIOU" }?.toString() ?: ""
    val l3 = am.firstOrNull()?.toString() ?: "X"

    val nombres = nom.split(" ")
    val principal = if(nombres.size > 1 &&
        (nombres[0]=="JOSE"||nombres[0]=="MARIA"||nombres[0]=="JOSÉ"||nombres[0]=="MARÍA")){
        nombres.getOrNull(1) ?: "X"
    } else nombres.getOrNull(0) ?: "X"

    val l4 = principal.firstOrNull()?.toString() ?: "X"

    val fechaPart = if(fecha.length >= 10){
        val p = fecha.split("-")
        if(p.size==3) p[0].takeLast(2)+p[1]+p[2] else ""
    } else "000000"
    val homoclave = "XXX"
    return "$l1$l2$l3$l4$fechaPart$homoclave"
}



