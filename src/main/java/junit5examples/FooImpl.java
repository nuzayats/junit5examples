package junit5examples;

import javax.inject.Inject;

public class FooImpl implements Foo {

    private final Bar bar;

    @Inject
    public FooImpl(Bar bar) {
        this.bar = bar;
    }

    @Override
    public String greetings() {
        return "Hello, " + bar.bar();
    }
}
