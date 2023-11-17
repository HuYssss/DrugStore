package gr2.clc.drugstore.controller;

import gr2.clc.drugstore.dto.authLoginDTO;
import gr2.clc.drugstore.service.userService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class authController {
    @Autowired
    private userService userService;
    @PostMapping("/login")
    public String login(@RequestBody authLoginDTO authUser,
                        HttpServletRequest request,
                        HttpServletResponse response) {

        if (userService.findByAccount(authUser).isPresent()) {
            HttpSession session = request.getSession();
            session.setAttribute("username", authUser.getUsername());

            Cookie cookie = new Cookie("userCookie", "authenticated");
            cookie.setMaxAge(60 * 30);
            response.addCookie(cookie);

            return "Login successful";
        } else {
            return "Invalid credentials";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username != null) {
            return "Welcome to the Dashboard, " + username + "!";
        } else {
            return "Not logged in";
        }
    }
}
