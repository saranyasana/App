
package me.woemler.springblog.controllers;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import me.woemler.springblog.models.User;
import me.woemler.springblog.repositories.UserRepositoryImpl;

@Controller
public class LoginController {
	 @Autowired
	 private UserRepositoryImpl userService;

    private static Logger logger  = LoggerFactory.getLogger(LoginController.class);
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login(ModelMap map){
        return "login";
    }
    
    @RequestMapping(value= {"/loginfailed"}, method=RequestMethod.GET)
    public String loginError(ModelMap map){
        map.addAttribute("error", "true");
        return "login";
    }
    
    
    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
       

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.saveUser(userForm);

      

        return "redirect:/home";
    }
}
