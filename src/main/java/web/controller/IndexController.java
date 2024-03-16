package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class IndexController {
    private UserService userService;
    public IndexController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping()
    public String indexView(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }
    @GetMapping("/new")
    public String addUser(@ModelAttribute("user") User user) {
        return "form";
    }
    @PostMapping()
    public String saveUser(@ModelAttribute("user") User user) {
        userService.createOrUpdateUser(user);
        return "redirect:/users";
    }
    @GetMapping("/delete")
    public String deleteUser(@RequestParam(value = "id", required = true, defaultValue = "") long id) {
        User user = userService.deleteUser(id);
        return "redirect:/users";
    }
    @GetMapping("/edit")
    public String editUser(@RequestParam(value = "id", required = true, defaultValue = "") long id, Model model) {
        User user = userService.readUser(id);

        if (null == user) {
            return "redirect:/users";
        }

        model.addAttribute("user", userService.readUser(id));
        return "form";
    }
}
