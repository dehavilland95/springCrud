package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String index(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "user/index";
    }
    @GetMapping(params = "id")
    public String show(
            @RequestParam(name = "id", required = false, defaultValue = "0") int id,
            Model model){
        System.out.println(id);
        model.addAttribute("user", userService.getById(id));
        return "user/show";
    }
    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user){
        return "user/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("user") User user){
        userService.save(user);
        return "redirect:/user";
    }
    @GetMapping(value = "/edit", params = "id")
    public String edit(
            @RequestParam(name = "id", required = false, defaultValue = "0") int id,
            Model model){
        model.addAttribute("user", userService.getById(id));
        return "user/edit";
    }
    @PatchMapping(params = "id")
    public String update(
            @ModelAttribute("user") User user,
            @RequestParam(name = "id", required = false, defaultValue = "0") int id){
        userService.update(id, user);
        return "redirect:/user";
    }
    @DeleteMapping(params = "id")
    public String delete(@RequestParam(name = "id", required = false, defaultValue = "0") int id){
        System.out.println();
        userService.delete(id);
        return "redirect:/user";
    }
}