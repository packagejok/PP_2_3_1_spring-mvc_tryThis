package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping()
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }
    @GetMapping("/new")
    public String addUserForm(@ModelAttribute("user") User user) {
        return "form";
    }
    @PostMapping()
    public String saveUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/users";
    }
    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user) {
        if (user.getId() != null) {
            userService.updateUser(user);
        }

        return "redirect:/users";
    }
    @GetMapping("/delete")
    public String deleteUser(@RequestParam(value = "id", required = true, defaultValue = "") long id) {
        User user = userService.deleteUser(id);
        return "redirect:/users";
    }
    @GetMapping("/edit")
    public String editUserForm(@RequestParam(value = "id", required = true, defaultValue = "") long id, Model model) {
        User user = userService.getUser(id);

        if (null == user) {
            return "redirect:/users";
        }

        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }
}
