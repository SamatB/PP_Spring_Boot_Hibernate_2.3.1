package beganov.web.controller;

import beganov.web.model.User;
import beganov.web.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("users") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/updatePage")
    public String updatePage(Model model, @RequestParam("id") Long id) {
        model.addAttribute("userUpdate", userService.getUserById(id));
        return "updateUser";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("userUpdate") User user, @RequestParam("id") Long id) {
        userService.updateUser(user, id);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
