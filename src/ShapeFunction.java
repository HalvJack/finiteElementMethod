@FunctionalInterface
public interface ShapeFunction<T,R,M> {
    M apply(T t, R r);

}
