package com.TAS.demo.Controlleur;

import com.TAS.demo.dao.IIngenieur;
import com.TAS.demo.models.Ingenieur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users/ingenieurs")
public class IngenieurController {
    private static final Logger logger = LoggerFactory.getLogger(RestController.class);
    @Autowired
    private IIngenieur iingenieur;
    @GetMapping("/all")
    public List<Ingenieur> listIngenieur() {
        return iingenieur.findAll();
    }
    @PutMapping("/update/{Id}")
    public Ingenieur update(@RequestBody Ingenieur ingenieur, @PathVariable Long Id) {
        ingenieur.setId(Id);
        return iingenieur.saveAndFlush(ingenieur);
    }
    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteIngenieur(@PathVariable(value = "Id") Long Id) {

        HashMap message= new HashMap();
        try{
            iingenieur.delete(Id);
            message.put("etat","ingenieur deleted");
            return message;
        }

        catch (Exception e) {
            message.put("etat","ingenieur not deleted");
            return message;
        }
    }
    @GetMapping("/getone/{id}")
    public Ingenieur getoneingenieur(@PathVariable Long id) {
        return iingenieur.findOne(id);
    }
    @PostMapping("/save")
    public Ingenieur saveIngenieur(@RequestBody Ingenieur c){
        return iingenieur.save(c);
    }
}
