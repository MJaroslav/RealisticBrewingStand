package mjaroslav.mcmods.realisticbrewingstand.gloomyfolken.hooklib.asm;

/**
 * В зависимости от этого значения определяется, что вернёт целевой метод при
 * выходе return после вызова хук-метода.
 */
public enum ReturnValue {

    /**
     * Возвращается void. Используется тогда и только тогда, когда целевой метод
     * возвращает void.
     */
    VOID,

    /**
     * Возвращается заранее установленное примитичное значение. Можно
     * использовать только когда целевой метод возвращает примитив.
     */
    PRIMITIVE_CONSTANT,

    /**
     * Возвращается null. Можно использовать только когда целевой метод
     * возвращает объект.
     */
    NULL,

    /**
     * Возвращается тот примитив или объект, который вернул хук-метод. Можно
     * использовать во всех случаях, кроме того, когда целевой метод возвращает
     * void.
     */
    HOOK_RETURN_VALUE,

    /**
     * Вызывает другой метод в том же классе и с теми же параметрами, что и
     * хук-метод, но с другим названием. Возвращает примитив или объект, который
     * вернул вызванный метод.
     */
    ANOTHER_METHOD_RETURN_VALUE

}
