package com.TAS.demo.Controlleur;

import com.TAS.demo.dao.ITache;
import com.TAS.demo.models.Tache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users/taches")
public class TacheController {
    private static final Logger logger = LoggerFactory.getLogger(RestController.class);
    @Autowired
    private ITache itache;
    @GetMapping("/all")
    public List<Tache> listTache() {
        return itache.findAll();
    }
    @PutMapping("/update/{Id}")
    public Tache update(@RequestBody Tache tache, @PathVariable Long Id) {
        tache.setId(Id);
        return itache.saveAndFlush(tache);
    }
    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteTache(@PathVariable(value = "Id") Long Id) {

        HashMap message= new HashMap();
        try{
            itache.delete(Id);
            message.put("etat","Tache deleted");
            return message;
        }

        catch (Exception e) {
            message.put("etat","Tache not deleted");
            return message;
        }
    }
    @GetMapping("/getone/{id}")
    public Tache getonetache(@PathVariable Long id) {
        return itache.findOne(id);
    }
    @PostMapping("/save")
    public Tache saveTache(@RequestBody Tache c){
        return itache.save(c);
    }
}
