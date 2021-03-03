package controller;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class UserController {
    @Autowired
    CustomerService customerService;
    @GetMapping("")
    public ModelAndView showAll(){
        ModelAndView modelAndView=new ModelAndView("showAll");
        List<Customer> list=customerService.findAll();
        modelAndView.addObject("list",list);
        return modelAndView;
    }
    @GetMapping("create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView=new ModelAndView("create");
        modelAndView.addObject("cus",new Customer());
        return modelAndView;
    }
    @PostMapping("create")
    public ModelAndView create(@ModelAttribute Customer cus){
        int id=customerService.findAll().size();
        cus.setId(id);
        customerService.save(cus);
        return showAll();
    }

}
