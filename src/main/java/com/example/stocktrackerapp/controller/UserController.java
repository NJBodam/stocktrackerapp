package com.example.stocktrackerapp.controller;
import com.example.stocktrackerapp.model.StockWrapper;
import com.example.stocktrackerapp.model.UserInfo;
import com.example.stocktrackerapp.service.StockServices;
import com.example.stocktrackerapp.service.serviceimplementation.StockServiceImpl;
import com.example.stocktrackerapp.service.serviceimplementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    private UserServiceImpl userServiceImpl;
    private StockServiceImpl stockServiceImpl;
    private StockServices stockServices;


    @Autowired
    public UserController(UserServiceImpl userServiceImpl, StockServiceImpl stockServiceImpl, StockServices stockServices ) {
        this.userServiceImpl = userServiceImpl;
        this.stockServiceImpl = stockServiceImpl;
        this.stockServices = stockServices;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("loginRequest", new UserInfo());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "login_page";
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute UserInfo userInfo) {
        System.out.println(userInfo);
        UserInfo user = new UserInfo();
        user.setFirstname(userInfo.getFirstname());
        user.setLastname(userInfo.getLastname());
        user.setUsername(userInfo.getUsername());
        user.setEmail(userInfo.getEmail());
        user.setDate_of_birth(userInfo.getDate_of_birth());
        user.setNumber(userInfo.getNumber());

        UserInfo userInfo1 = userServiceImpl.saverUser(userInfo);
        System.out.println(userInfo1);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login_page");
        modelAndView.addObject("userInfo", userInfo);
        return modelAndView;

    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserInfo userInfo, Model model, HttpSession httpSession, StockWrapper stock) throws Exception {
        System.out.println("loginrequest" + userInfo);
        UserInfo authenticated = userServiceImpl.authenticate(userInfo.getEmail(), userInfo.getPassword());
        if (authenticated != null) {
            model.addAttribute("bigPrice", stockServices.findPrice(stock));
            System.out.println("bigPrices ==" + stockServices.findPrice(stock) );
            System.out.println("User " + authenticated + " logged in");
            httpSession.setAttribute("user", authenticated);
            model.addAttribute("userLogin", authenticated.getFirstname() + " " + authenticated.getLastname());

            return "dashboard_charts";
        } else {
            String message = "Incorrect login details. Wrong email or password. ";
            model.addAttribute("errorMessage", message);
            model.addAttribute("errorNotice", "RETURN TO LOGIN PAGE");
            model.addAttribute("errorLink", "/login");
            return "error";
        }
    }

    @GetMapping("/dashboard")
    public String getDashBoard(Model model, HttpSession session, Model model1) {
        boolean validSession = session.getAttribute("user") == null;
        // stockServiceImpl.viewDashboard(model);
        model1.addAttribute("errorMessage", "Invalid session.");
        model1.addAttribute("errorNotice", "RETURN TO LOGIN PAGE");
        model1.addAttribute("errorLink", "/login");

        return validSession ? "error" : "dashboard";
    }



}
