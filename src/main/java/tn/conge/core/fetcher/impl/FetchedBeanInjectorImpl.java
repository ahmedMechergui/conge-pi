package tn.conge.core.fetcher.impl;

import com.google.common.reflect.ClassPath;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import tn.conge.core.fetcher.BeanFetcherUtils;
import tn.conge.core.fetcher.FetchedBean;
import tn.conge.core.fetcher.FetchedBeanInjector;
import tn.conge.core.fetcher.UseFetchedBeans;
import tn.conge.core.fetcher.exceptions.UseFetchedBeanAnnotationMissingException;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component("fetchedBeanInjector")
@DependsOn("applicationContextInitializer")
@Slf4j
public class FetchedBeanInjectorImpl implements FetchedBeanInjector {

    public static final String BASE_PACKAGE = "tn.conge";

    @Override
    @PostConstruct
    @SneakyThrows
    public void inject() {

        this.getFieldsWithAnnotation().forEach(field -> {
            Object bean = BeanFetcherUtils.getBean(field.getType());
            try {
                this.checkForUseFetchedBeanAnnotation(field);
                field.setAccessible(true);
                log.info("Injecting a fetched bean \"{}\" in field \"{}.{}\"", bean.getClass().getName(), field.getDeclaringClass(), field.getName());
                FieldUtils.writeStaticField(field, bean);
            } catch (IllegalAccessException e) {
                log.warn(e.getMessage());
            }
        });
    }

    private void checkForUseFetchedBeanAnnotation(Field field){
        if (Objects.isNull(field.getDeclaringClass().getAnnotation(UseFetchedBeans.class))){
            throw new UseFetchedBeanAnnotationMissingException(field.getType());
        }
    }

    @SneakyThrows
    private List<Class<?>> getProjectClasses() {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        return ClassPath.from(loader).getTopLevelClassesRecursive(BASE_PACKAGE)
                .stream()
                .map(ClassPath.ClassInfo::load)
                .collect(Collectors.toList());
    }

    private List<Field> getFieldsWithAnnotation() {
        List<Class<?>> projectClasses = this.getProjectClasses();
        List<Field> fields = new ArrayList<>();
        projectClasses.forEach(clazz -> fields.addAll(FieldUtils.getFieldsListWithAnnotation(clazz, FetchedBean.class)));

        return fields;
    }
}
