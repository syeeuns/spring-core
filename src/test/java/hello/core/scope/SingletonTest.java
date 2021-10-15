package hello.core.scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

  @Test
  void singletonBeanFind() {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        SingletonBean.class);
    context.close();
  }

  static class SingletonBean {
    @PostConstruct
    public void init() {
      System.out.println("SingletonBean.init");
    }

    @PreDestroy
    public void destroy() {
      System.out.println("SingletonBean.destroy");
    }
  }
}
