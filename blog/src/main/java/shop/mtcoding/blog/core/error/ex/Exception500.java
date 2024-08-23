package shop.mtcoding.blog.core.error.ex;

// 인증관련 인증이 안됐을 때 터트린다
public class Exception500 extends RuntimeException {
    public Exception500(String message) {
        super(message);
    }
}
