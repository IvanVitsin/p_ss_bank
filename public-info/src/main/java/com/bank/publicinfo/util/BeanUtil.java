package com.bank.publicinfo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * BeanUtil - класс компонента, отвечающий за доступ к bean-компонентам из контекста приложения.
 * Логирование - @Slf4j - аннотация lombok.
 *
 * @author Semushkin Danila
 * @version 1.0
 * @since 15.02.2023
 */

@Component
@Slf4j
public class BeanUtil implements ApplicationContextAware {
    /**
     * Поле - context - контекст приложения
     */
    private static ApplicationContext context;

    /**
     * setApplicationContext устанавливает applicationContext приложения.
     *
     * @param applicationContext контекст приложения.
     * @throws BeansException исключение, если возникает ошибка.
     */

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        log.info("Контекст успешно установлен BeanUtil.class");
    }

    /**
     * getBean возвращает bean указанного типа класса.
     *
     * @param beanClass тип класса возвращаемого компонента.
     * @return bean указанного типа класса.
     * @param <T> Generic.
     */
    public static <T> T getBean(Class<T> beanClass) {
        log.info("Получен бин указанного типа BeanUtil.class");
        return context.getBean(beanClass);
    }
}
