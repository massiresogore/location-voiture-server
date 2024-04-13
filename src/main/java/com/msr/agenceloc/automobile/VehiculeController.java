package com.msr.agenceloc.automobile;

import com.msr.agenceloc.system.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.endpoint.base-url}/vehicules")
public class VehiculeController {


    @GetMapping
    public Result index(){

     //  AbstractAutomobile abA = this.vehiculeServcei.creeAutomobile("ToyoYa","Bleu",1200,2);

        return new Result(true, 200,"success test vehicules");
    }

}
