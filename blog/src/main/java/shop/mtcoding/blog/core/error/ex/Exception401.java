package shop.mtcoding.blog.core.error.ex;

// 인증관련 인증이 안됐을 때 터트린다
public class Exception401 extends RuntimeException {
    public Exception401(String message) {
        super(message);
    }
}
