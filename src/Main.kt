fun main() {
    while (true) {
        println("Выберите калькулятор:")
        println("1 - Калькулятор 1")
        println("2 - Калькулятор 2")
        println("0 - Выход")
        print("Ваш выбор: ")

        val choice = readLine()?.toIntOrNull() // Используем readLine() вместо Scanner
        if (choice == null) {
            println("Введіть число.")
            continue
        }

        when (choice) {
            1 -> calculator1()
            2 -> calculator2()
            0 -> {
                println("Вихід")
                break
            }
            else -> println("Хибний вибір :(")
        }
    }
}

fun calculator1() {
    println("Калькулятор 1 - Введіть значення:")

    print("Hp: ")
    val hp = readLine()?.toDoubleOrNull()
    print("Cp: ")
    val cp = readLine()?.toDoubleOrNull()
    print("Sp: ")
    val sp = readLine()?.toDoubleOrNull()
    print("Np: ")
    val np = readLine()?.toDoubleOrNull()
    print("Op: ")
    val op = readLine()?.toDoubleOrNull()
    print("Wp: ")
    val wp = readLine()?.toDoubleOrNull()
    print("Ap: ")
    val ap = readLine()?.toDoubleOrNull()

    if (hp != null && cp != null && sp != null && np != null && op != null && wp != null && ap != null) {
        val result = calculate1(hp, cp, sp, np, op, wp, ap)
        println(result)
    } else {
        println("Введено неправильні дані")
    }
}

fun calculator2() {
    println("Калькулятор 2 - Введіть значенення:")

    print("Cg: ")
    val cg = readLine()?.toDoubleOrNull()
    print("Hg: ")
    val hg = readLine()?.toDoubleOrNull()
    print("Og: ")
    val og = readLine()?.toDoubleOrNull()
    print("Sg: ")
    val sg = readLine()?.toDoubleOrNull()
    print("Qi: ")
    val qi = readLine()?.toDoubleOrNull()
    print("Vg: ")
    val vg = readLine()?.toDoubleOrNull()
    print("Wg: ")
    val wg = readLine()?.toDoubleOrNull()
    print("Ag: ")
    val ag = readLine()?.toDoubleOrNull()

    if (cg != null && hg != null && og != null && sg != null && qi != null && vg != null && wg != null && ag != null) {
        val result = calculate2(cg, hg, og, sg, qi, vg, wg, ag)
        println(result)
    } else {
        println("Введено неправильні дані")
    }
}

fun calculate1(hp: Double, cp: Double, sp: Double, np: Double, op: Double, wp: Double, ap: Double): String {
    val kpc = 100 / (100 - wp)
    val krg = 100 / (100 - wp - ap)

    val hc = hp * kpc
    val cc = cp * kpc
    val sc = sp * kpc
    val nc = np * kpc
    val oc = op * kpc
    val ac = ap * kpc
    val totalCheck = hc + cc + sc + nc + oc + ac

    val hg = hp * krg
    val cg = cp * krg
    val sg = sp * krg
    val ng = np * krg
    val og = op * krg
    val totalCheck2 = hg + cg + sg + ng + og

    val qph = (339 * cp + 1030 * hp - 108.8 * (op - sp) - 25 * wp) /1000
    val qch = (qph + 0.025 * wp) * 100.0 / (100 - wp)
    val qgh = (qph + 0.025 * wp) * 100.0 / (100 - wp - ap)

    return if (totalCheck == 100.0 && totalCheck2 == 100.0) {
        """
        Коефіцієнт переходу від робочої до сухої маси: ${"%.3f".format(kpc)}
        Коефіцієнт переходу від робочої до горючої маси: ${"%.3f".format(krg)}
        Cклад сухої маси палива:
        Hc = ${"%.3f".format(hc)} %
        Cc = ${"%.3f".format(cc)} %
        Sc = ${"%.3f".format(sc)} %
        Nc = ${"%.3f".format(nc)} %
        Oc = ${"%.3f".format(oc)} %
        Ac = ${"%.3f".format(ac)} %

        Cклад горючої маси палива:
        Hg = ${"%.3f".format(hg)} %
        Cg = ${"%.3f".format(cg)} %
        Sg = ${"%.3f".format(sg)} %
        Ng = ${"%.3f".format(ng)} %
        Og = ${"%.3f".format(og)} %

        Теплота згорання робочої маси: ${"%.3f".format(qph)} МДж/кг
        Теплота згорання сухої маси: ${"%.3f".format(qch)} МДж/кг
        Теплота згорання горючої маси: ${"%.3f".format(qgh)} МДж/кг
        """.trimIndent()
    } else {
        "Помилка в розрахунках totalCheck $totalCheck totalCheck2 $totalCheck2"
    }
}

fun calculate2(cg: Double, hg: Double, og: Double, sg: Double, qi: Double, vg: Double, wg: Double, ag: Double): String {
    val cp = cg * (100 - wg - ag) / 100.0
    val hp = hg * (100 - wg - ag) / 100.0
    val op = og * (100 - wg - ag) / 100.0
    val sp = sg * (100 - wg - ag) / 100.0
    val ap = ag * (100 - wg) / 100.0
    val vp = vg * (100 - wg) / 100.0



    val qri = qi * (100 - wg - ap )/ 100 - 0.025 * wg

    return         """
        Склад робочої маси мазуту:
        Вуглець Сp = $cp %
        Водень Hp = $hp %
        Кисень Op = $op %
        Сірка Sp = $sp %
        Зола Ap = $ap %
        Ванадій Vp = $vp мг/кг
       $qri = $qi * (100 - $wg - $ag / 100) - 0.025 * $wg
        Нижча теплота згоряння мазуту на робочу масу для робочої маси за заданим складом компонентів палива: $qri кДж/кг
        """.trimIndent()

}
