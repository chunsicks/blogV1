package shop.mtcoding.blog.core.error;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import shop.mtcoding.blog.core.error.ex.Exception400;

@Component
@Aspect
public class GlobalValidationHandler {

    //만약 put에 사용하고 싶으면  @Before("@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping)")

    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void validCheck(JoinPoint jp) {
        //메서드 매개변수 알려줌
        //postMapping의 매개 변수 전부 가지고 온나
        //@PathVariable("id") int id 는 1개로 칠까 2개로 칠까?
        Object[] args = jp.getArgs();
        for (Object arg : args) {
            System.out.println("arg임 : " + arg);
            //만약 arg에 Errors가 있다면 두개중 타입이 Errors가 있다면  instanceof 타입 검사    다운케스팅 할거임
            //에러스 없으면 밸리데이션 검사 안하고 잇으면 검사 한다
            if (arg instanceof Errors) {
                Errors errors = (Errors) arg;
                //에서 있는지 검사
                if (errors.hasErrors()) {
                    if (errors.hasErrors()) {
                        //field는 변수 명 만약 에러 2개 여도 하나 throw하면 끝난다
                        // 바디데이터 10개중에 10개 다 에러면 한번에 다 알려줄 필요 없다 한개만 날려도 됨
                        //한번에 주고 싶으면 hasMap에 담아서 한번에 주면 된다.
                        for (FieldError error : errors.getFieldErrors()) {
                            throw new Exception400(error.getDefaultMessage() + " : " + error.getField());
                        }
                    }
                }
            }
        }
    }


    // 연습용
    //어라운드에서만 Object사용 DS에 넘겨 줘야 하니까

    //특정한 어노테이션 전에 발동될 수 있게
    // 메서드나 매개변수 이후에 하기 힘들기 때문
    // 사용할 떄 풀 내임 적어야 하는데
    //지금 getMapping실행되면 될 수 있게
    //@Before("@annotation(org.springframework.web.bind.annotation.GetMapping)")

    /*
    1.
    2. jp.proceed() 는 hello 어노테이션 붙은 함수 변수 값이 들어옴
        받는 변수가 String,인지 Board인지 모르기 때문에 Obejct 사용해야함
     */
    @Around("@annotation(shop.mtcoding.blog.core.Hello)")
    public Object hello(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("aop hello1 before 호출됨");
        // 언제 발동될 것인지
        Object proceed = jp.proceed();  //@Hello 어노테이션 붙은 함수 호출 "user/login-form"
        System.out.println("aop hello1 after 호출됨");
        System.out.println(proceed);
        return proceed;// 왜 리턴? DS가 받아서 처리 해야 해서
        //매서드 실행 시간 성능 체크 하려고
    }
}
