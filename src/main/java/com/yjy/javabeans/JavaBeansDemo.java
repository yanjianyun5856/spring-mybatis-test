package com.yjy.javabeans;

import com.yjy.springmybatistest.entity.User;
import org.springframework.core.env.PropertySource;
import org.springframework.util.StringUtils;

import java.beans.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

public class JavaBeansDemo {
    public static void main(String[] args) throws  Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class);

        //Bean 描述符
        BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
        System.out.println(beanDescriptor);
        //方法描述符
        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
        Stream.of(methodDescriptors).forEach(System.out::println);

        //字段描述符
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        User user = new User();
        Stream.of(propertyDescriptors).forEach(propertyDescriptor -> {
            String propertyName=propertyDescriptor.getName();
            if ("id".equals(propertyName)){
                propertyDescriptor.setPropertyEditorClass(IdPropertyEditor.class);
                PropertyEditor propertyEditor = propertyDescriptor.createPropertyEditor(user);

                propertyEditor.addPropertyChangeListener(event ->{
                    PropertyEditor source = (PropertyEditor) event.getSource();
                    Object newValue = event.getNewValue();
                    Method setIdMethod = propertyDescriptor.getWriteMethod();
                    try {
                        setIdMethod.invoke(user,source.getValue());
                    } catch (Exception e) {

                    }
                });
                //触发时间  同时事件源
                propertyEditor.setAsText("1");
            }
        });
    }

    class IdPropertyEditor extends PropertyEditorSupport{
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            if (StringUtils.hasText(text)){
                setValue(Long.parseLong(text));
            }else{
                setValue(Long.MIN_VALUE);
            }
        }
    }
}
