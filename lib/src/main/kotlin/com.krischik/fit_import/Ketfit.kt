package com.krischik.fit_import

/**
 * <p>
 * </p>
 *
 * <pre>
 * Datum     ;Zeit ;Dauer    ;Watt;Puls;u/min;kcal;km;°
 * 02.02.2014;18:42;00:40:00 ;88  ;118 ;48   ;294 ;6 ;-;
 * 03.02.2014;18:29;00:40:00 ;88  ;94  ;61   ;294 ;6 ;-;
 *</pre>
 * @author martin
 * @version 1.0
 * @since 1.0
 */

data class Ketfit(
        val Datum: java.util.Date,
        val Dauer: Int,
        val Watt: Int,
        val Puls: Int,
        val umin: Int,
        val kcal: Int,
        val km: Int,
        val ω: Int)
