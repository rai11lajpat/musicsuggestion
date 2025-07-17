package com.lajpat.guides.music;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>Java class for mood.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="mood"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Happy"/&gt;
 *     &lt;enumeration value="Sad"/&gt;
 *     &lt;enumeration value="Relaxing"/&gt;
 *     &lt;enumeration value="Energetic"/&gt;
 *     &lt;enumeration value="Dance"/&gt;
 *     &lt;enumeration value="DanceRomantic"/&gt;
 *     &lt;enumeration value="Romantic"/&gt;
 *     &lt;enumeration value="Devotional"/&gt;
 *     &lt;enumeration value="RomanticSad"/&gt;
 *     &lt;enumeration value="DanceSad"/&gt;
 *     &lt;enumeration value="Motivational"/&gt;
 *     &lt;enumeration value="Romance"/&gt;
 *     &lt;enumeration value="Sensual"/&gt;
 *     &lt;enumeration value="Patriotic"/&gt;
 *     &lt;enumeration value="RomanticSadSensual"/&gt;
 *     &lt;enumeration value="RomanticSensual"/&gt;
 *     &lt;enumeration value="DancePatriotic"/&gt;
 *     &lt;enumeration value="MotivationalPatriotic"/&gt;
 *     &lt;enumeration value="DanceSensual"/&gt;
 *     &lt;enumeration value="DevotionalSad"/&gt;
 *     &lt;enumeration value="DanceMotivationalPatriotic"/&gt;
 *     &lt;enumeration value="DanceRomance"/&gt;
 *     &lt;enumeration value="PatrioticSad"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "mood")
@XmlEnum
public enum Mood {

    @XmlEnumValue("Happy")
    HAPPY("Happy"),
    @XmlEnumValue("Sad")
    SAD("Sad"),
    @XmlEnumValue("Relaxing")
    RELAXING("Relaxing"),
    @XmlEnumValue("Energetic")
    ENERGETIC("Energetic"),
    @XmlEnumValue("Dance")
    DANCE("Dance"),
    @XmlEnumValue("DanceRomantic")
    DANCE_ROMANTIC("DanceRomantic"),
    @XmlEnumValue("Romantic")
    ROMANTIC("Romantic"),
    @XmlEnumValue("Devotional")
    DEVOTIONAL("Devotional"),
    @XmlEnumValue("RomanticSad")
    ROMANTIC_SAD("RomanticSad"),
    @XmlEnumValue("DanceSad")
    DANCE_SAD("DanceSad"),
    @XmlEnumValue("Motivational")
    MOTIVATIONAL("Motivational"),
    @XmlEnumValue("Romance")
    ROMANCE("Romance"),
    @XmlEnumValue("Sensual")
    SENSUAL("Sensual"),
    @XmlEnumValue("Patriotic")
    PATRIOTIC("Patriotic"),
    @XmlEnumValue("RomanticSadSensual")
    ROMANTIC_SAD_SENSUAL("RomanticSadSensual"),
    @XmlEnumValue("RomanticSensual")
    ROMANTIC_SENSUAL("RomanticSensual"),
    @XmlEnumValue("DancePatriotic")
    DANCE_PATRIOTIC("DancePatriotic"),
    @XmlEnumValue("MotivationalPatriotic")
    MOTIVATIONAL_PATRIOTIC("MotivationalPatriotic"),
    @XmlEnumValue("DanceSensual")
    DANCE_SENSUAL("DanceSensual"),
    @XmlEnumValue("DevotionalSad")
    DEVOTIONAL_SAD("DevotionalSad"),
    @XmlEnumValue("DanceMotivationalPatriotic")
    DANCE_MOTIVATIONAL_PATRIOTIC("DanceMotivationalPatriotic"),
    @XmlEnumValue("DanceRomance")
    DANCE_ROMANCE("DanceRomance"),
    @XmlEnumValue("PatrioticSad")
    PATRIOTIC_SAD("PatrioticSad");

    private final String value;

    Mood(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Mood fromValue(String v) {
        for (Mood c : Mood.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
