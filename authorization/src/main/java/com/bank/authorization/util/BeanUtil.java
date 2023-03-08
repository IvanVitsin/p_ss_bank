package com.bank.authorization.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Получение бина из Application Context
 * @author Zhukova Ekaterina
 * @version 1.0
 * @since 15.02.2023
 */

@Slf4j
@Component
public class BeanUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        log.info("Контекст установлен BeanUtil.class");
    }

    public static <T> T getBean(Class<T> beanClass) {
        log.info("Получен бин указанного типа.BeanUtil.class");
        return context.getBean(beanClass);
    }
}
