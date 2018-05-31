package web;

import myAnotion.MyRequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: pengjianzhou
 * @Description:
 * @Date: Created in 下午3:43 18-5-14
 */
@Controller
@RequestMapping("test")
public class TestController {

    //
    String dir = "testDir";


    @RequestMapping("h1")
    public String h1(Model view){

        System.out.println("**********h1");
        view.addAttribute("ss", "this is h1");

        return "h1";
    }

    @MyRequestMapping
    public void h2(Model view){
        System.out.println("**********h2");
    }

    @MyRequestMapping
    public void h3(Model view){
        System.out.println("**********h3");
        view.addAttribute("ss", "this is h3");
    }
}
