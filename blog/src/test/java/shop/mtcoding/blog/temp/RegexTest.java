package shop.mtcoding.blog.temp;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

// java.util.regex.Pattern
public class RegexTest {

    //어떻게 연습하냐 GPT가서 한글만 되는 정규 표현식 알려줘 하면 맹신은 하면 안되고 태스트는 해봐야 함
    //틀린 거 있으면 왜 안되냐 ? 물어봄
    // 또 테스트함
    @Test
    public void 한글만된다_test() throws Exception {
        String value = "한글";
        //가 ~ 힣 받침 까지 된다는 말 한글이면 true나옴 하지만 ㄱ은 안됨 하려면 ㄱ-ㅎ
        boolean result = Pattern.matches("^[가-힣]+$", value);
        System.out.println("테스트 : " + result);
    }

    @Test
    public void 한글은안된다_test() throws Exception {
        String value = "abc";
        boolean result = Pattern.matches("^[^ㄱ-ㅎ가-힣]*$", value);
        System.out.println("테스트 : " + result);
    }

    @Test
    public void 영어만된다_test() throws Exception {
        String value = "ssar";
        boolean result = Pattern.matches("^[a-zA-Z]+$", value);
        System.out.println("테스트 : " + result);
    }

    @Test
    public void 영어는안된다_test() throws Exception {
        String value = "가22";
        boolean result = Pattern.matches("^[^a-zA-Z]*$", value);
        System.out.println("테스트 : " + result);
    }

    @Test
    public void 영어와숫자만된다_test() throws Exception {
        String value = "ab12";
        boolean result = Pattern.matches("^[a-zA-Z0-9]+$", value);
        System.out.println("테스트 : " + result);
    }

    @Test
    public void 영어만되고_길이는최소2최대4이다_test() throws Exception {
        String value = "ssar";
        boolean result = Pattern.matches("^[a-zA-Z]{2,4}$", value);
        System.out.println("테스트 : " + result);
    }

    // username, email, fullname
    @Test
    public void user_username_test() throws Exception {
        String username = "ssar";
        boolean result = Pattern.matches("^[a-zA-Z0-9]{2,20}$", username);
        System.out.println("테스트 : " + result);
    }

    @Test
    public void user_fullname_test() throws Exception {
        String fullname = "메타코딩";
        boolean result = Pattern.matches("^[a-zA-Z가-힣]{1,20}$", fullname);
        System.out.println("테스트 : " + result);
    }

    @Test
    public void user_email_test() throws Exception {
        String fullname = "ssaraa@nate.com"; // ac.kr co.kr or.kr
        boolean result = Pattern.matches("^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,6}\\.[a-zA-Z]{2,3}$", fullname);
        System.out.println("테스트 : " + result);
    }

    @Test
    public void account_gubun_test1() throws Exception {
        String gubun = "DEPOSIT"; // ac.kr co.kr or.kr
        boolean result = Pattern.matches("^(DEPOSIT)$", gubun);
        System.out.println("테스트 : " + result);
    }

    @Test
    public void account_gubun_test2() throws Exception {
        String gubun = "TRANSFER"; // ac.kr co.kr or.kr
        boolean result = Pattern.matches("^(DEPOSIT|TRANSFER)$", gubun);
        System.out.println("테스트 : " + result);
    }

    @Test
    public void account_tel_test1() throws Exception {
        String tel = "010-3333-7777"; // ac.kr co.kr or.kr
        boolean result = Pattern.matches("^[0-9]{3}-[0-9]{4}-[0-9]{4}", tel);
        System.out.println("테스트 : " + result);
    }

    @Test
    public void account_tel_test2() throws Exception {
        String tel = "01033337777"; // ac.kr co.kr or.kr
        boolean result = Pattern.matches("^[0-9]{11}", tel);
        System.out.println("테스트 : " + result);
    }
}
