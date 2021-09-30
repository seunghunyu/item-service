package hello.itemservice.web;

import hello.itemservice.domain.member.Member;
import hello.itemservice.web.session.SessionManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionManagerTest {
    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest(){

        //세션생성 (Mock 붙은건 스프링에서 제공해주는 객체 기존의 HttpServlet Response, Request는 인터페이스 이기 때문)
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.createSession(member, response);

        //요청에 응답 쿠키 저장
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        //세션조회
        Object result = sessionManager.getSession(request);
        assertThat(result).isEqualTo(member);

        //세션만료
        sessionManager.expire(request);
        Object expired = sessionManager.getSession(request);
        assertThat(expired).isNull();
    }
}
