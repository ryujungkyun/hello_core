package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StateFulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StateFulService stateFulService1 = ac.getBean(StateFulService.class);
        StateFulService stateFulService2 = ac.getBean(StateFulService.class);

        //ThreadA: A사용자 10000원 주문
        //ThreadB: B사용자 20000원 주문
        stateFulService1.order("userA", 10000);
        stateFulService2.order("userA",20000);

        
    }

    static class TestConfig {

        @Bean
        public StateFulService stateFulService() {
            return new StateFulService();
        }

    }
}