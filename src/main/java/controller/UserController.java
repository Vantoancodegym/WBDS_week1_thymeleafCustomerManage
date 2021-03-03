package controller;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
        int id=(int)(Math.random()*100000) ;
        cus.setId(id);
        customerService.save(cus);
        return new ModelAndView("redirect:/customers");
    }
    @GetMapping("edit")
    public ModelAndView showFormEdit(@RequestParam int id){
        ModelAndView modelAndView=new ModelAndView("edit");
        Customer cus=customerService.findById(id);
        modelAndView.addObject("cus",cus);
        return modelAndView;
    }
    @PostMapping("edit")
    public ModelAndView edit(@RequestParam int id, @ModelAttribute Customer cus ){
        cus.setId(id);
        customerService.update(id,cus);
        return new ModelAndView("redirect:/customers");
    }
    @GetMapping("delete")
    public ModelAndView delete(@RequestParam int id){
        customerService.remove(id);
        return new ModelAndView("redirect:/customers");
    }

}
