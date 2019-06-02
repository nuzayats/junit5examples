package junit5examples;

public class FooImpl implements Foo {

    private final Bar bar;

    public FooImpl(Bar bar) {
        this.bar = bar;
    }

    @Override
    public String greetings() {
        return "hello";
    }
}
