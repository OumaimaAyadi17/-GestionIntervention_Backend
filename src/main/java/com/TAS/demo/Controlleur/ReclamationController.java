package com.TAS.demo.Controlleur;

import com.TAS.demo.dao.IReclamation;
import com.TAS.demo.models.Reclamation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users/reclamations")
public class ReclamationController {
    private static final Logger logger = LoggerFactory.getLogger(RestController.class);
    @Autowired
    private IReclamation ireclamation;
    @GetMapping("/all")
    public List<Reclamation> listReclamations() {
        return ireclamation.findAll();
    }
    @PutMapping("/update/{Id}")
    public Reclamation update(@RequestBody Reclamation reclamation, @PathVariable Long Id) {
        reclamation.setId(Id);
        return ireclamation.saveAndFlush(reclamation);
    }
    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteReclamation(@PathVariable(value = "Id") Long Id) {

        HashMap message= new HashMap();
        try{
            ireclamation.delete(Id);
            message.put("etat","reclamation deleted");
            return message;
        }

        catch (Exception e) {
            message.put("etat","reclamation not deleted");
            return message;
        }
    }
    @GetMapping("/getone/{id}")
    public Reclamation getonereclamation(@PathVariable Long id) {
        return ireclamation.findOne(id);
    }
    @PostMapping("/save")
    public Reclamation saveReclamation(@RequestBody Reclamation c){
        return ireclamation.save(c);
    }
}

