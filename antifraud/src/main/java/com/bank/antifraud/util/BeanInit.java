package com.bank.antifraud.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BeanInit implements ApplicationContextAware {
    public static ApplicationContext applicationContext;

    /**
     * Этот метод устанавливает applicationContext приложения.
     *
     * @param applicationContext1 контекст приложения.
     * @throws BeansException, если возникает ошибка при установке контекста приложения.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext1) throws BeansException {
        applicationContext1 = applicationContext;
        log.info("current context is BeanInit.class");
    }

    /**
     * Этот метод возвращает bean указанного типа класса.
     *
     * @param beanClass
     * @return бин указанного типа класса.
     */
    public static <T> T getBean(Class<T> beanClass) {
        log.info("Получен бин указанного типа.BeanUtil.class");
        return applicationContext.getBean(beanClass);
    }

}
