package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    //message를 자동으로 등록해준다.
    // 설정: application.properties, messages.properties, etc
    @Autowired
    MessageSource messageSource;

    //메시지
    @Test
    void helloMessage() {
        String result = messageSource.getMessage("hello", null, null);
        Assertions.assertThat(result).isEqualTo("Hello");
    }

    @Test
    void notFoundMessageCode() {
        assertThatThrownBy(() -> messageSource.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeDefaultMessageCode() {
        String result = messageSource.getMessage(
                "no_code",null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");
    }

    @Test
    void argumentMessage() {
        String message = messageSource.getMessage(
                "hello.name", new Object[]{"Spring"}, null);
        assertThat(message).isEqualTo("Hello Spring");
    }


    //국제화
    @Test
    void defaultLang() {
        assertThat(messageSource.getMessage("hello", null, null))
                .isEqualTo("Hello");
        assertThat(messageSource.getMessage("hello", null, Locale.ENGLISH))
                .isEqualTo("Hello");
    }

    @Test
    void koLang() {
        assertThat(messageSource.getMessage("hello", null, null))
                .isEqualTo("Hello");
        assertThat(messageSource.getMessage("hello", null, Locale.KOREA))
                .isEqualTo("안녕");
    }

}
