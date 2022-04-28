package com.TAS.demo.Controlleur;

import com.TAS.demo.dao.IClient;
import com.TAS.demo.models.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/users/clients")
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(RestController.class);
    @Autowired
    private IClient iclient;
    @GetMapping("/all")
    public List<Client> listClient() {
        return iclient.findAll();
    }
    @PutMapping("/update/{Id}")
    public Client update(@RequestBody Client client, @PathVariable Long Id) {
        client.setId(Id);
        return iclient.saveAndFlush(client);
    }
    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteClient(@PathVariable(value = "Id") Long Id) {

        HashMap message= new HashMap();
        try{
            iclient.delete(Id);
            message.put("etat","client deleted");
            return message;
        }

        catch (Exception e) {
            message.put("etat","client not deleted");
            return message;
        }
    }
    @GetMapping("/getone/{id}")
    public Client getoneclient(@PathVariable Long id) {
        return iclient.findOne(id);
    }
    @PostMapping("/save")
    public Client saveClient(@RequestBody Client c){
        return iclient.save(c);
    }
}
