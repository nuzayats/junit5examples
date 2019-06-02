package junit5examples;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.util.Modules;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FooTest {

    @Test
    void name() {
        Injector injector = Guice.createInjector(new MyModule());
        Foo sut = injector.getInstance(Foo.class);

        String actual = sut.greetings();

        assertThat(actual).isEqualTo("Hello, it's BarImpl");
    }

    @Test
    void override() {
        Injector injector = Guice.createInjector(Modules.override(new MyModule()).with(new TestModule()));
        Foo sut = injector.getInstance(Foo.class);

        String actual = sut.greetings();

        assertThat(actual).isEqualTo("Hello, it's MyBar");
    }

    private static class TestModule extends AbstractModule {

        @Override
        protected void configure() {
            bind(Bar.class).to(MyBar.class);
        }
    }

    private static class MyBar implements Bar {

        @Override
        public String bar() {
            return "it's MyBar";
        }
    }
}
