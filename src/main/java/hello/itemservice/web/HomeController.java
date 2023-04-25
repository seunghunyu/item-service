package hello.itemservice.web;

import hello.itemservice.domain.member.Member;
import hello.itemservice.domain.member.MemberRepository;
import hello.itemservice.web.argumentresolver.Login;
import hello.itemservice.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Slf4j
//@RequestMapping
@Controller
//@RequiredArgsConstructor
public class HomeController {
//    private final MemberRepository memberRepository;
//    private final SessionManager sessionManager;
    @RequestMapping("/")
    public String home() {
        return "redirect:items";
    }

//    @GetMapping("/")
//    public String home()
//    {
//        log.info("home화면 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//        return "items";
//    }

    //@GetMapping("/")
//    public String homeLogin(@CookieValue(name = "memberId",required = false) Long memberId, Model model){
//        if(memberId == null){
//            return "home";
//        }
//
//        //로그인
//        Member loginMember = memberRepository.findById(memberId);
//        if(loginMember==null){
//            return "home";
//        }
//        model.addAttribute("member",loginMember);
//
//        return "loginHome";
//    }
//    //@GetMapping("/")
//    public String homeLoginV2(HttpServletRequest request, Model model){
//        //세션 관리자에 저장된 회원 정보 조회
//        Member member = (Member)sessionManager.getSession(request);
//        //로그인
//
//        if(member==null){
//            return "home";
//        }
//        model.addAttribute("member",member);
//
//        return "loginHome";
//    }
//    //@GetMapping("/")
//    public String homeLoginV3(HttpServletRequest request, Model model){
//        HttpSession session = request.getSession(false);
//        if(session == null){
//            return "home";
//        }
//        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
//        //세션에 회원 데이터가 없으면 home
//        if(loginMember==null){
//            return "home";
//        }
//
//        //세션이 유지되면 로그인으로 이동
//        model.addAttribute("member",loginMember);
//        return "loginHome";
//    }
//    //@GetMapping("/")   //@SessionAttribue 스프링 제공 annotation -> 참고로 세션을 생성하지는 않음
//    public String homeLoginV3Spring(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) Member loginMember, Model model){
//        //세션에 회원 데이터가 없으면 home
//        if(loginMember==null){
//            return "home";
//        }
//
//        //세션이 유지되면 로그인으로 이동
//        model.addAttribute("member",loginMember);
//        return "loginHome";
//    }
////    @GetMapping("/")   //@SessionAttribue 스프링 제공 annotation -> 참고로 세션을 생성하지는 않음
//    public String homeLoginV3SpringArgumentResolver(@Login Member loginMember, Model model){
//        //세션에 회원 데이터가 없으면 home
//        if(loginMember==null){
//            return "home";
//        }
//
//        //세션이 유지되면 로그인으로 이동
//        model.addAttribute("member",loginMember);
//        return "loginHome";
//    }
}
