package exeption;

public class NotFoundReceptionById extends RuntimeException {
    public NotFoundReceptionById(String e) {
        super(e);
    }
}
