package controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Model.Tache;
import service.TacheService;

@RestController
@CrossOrigin("*")
public class TacheController {
	@Autowired
	private TacheService service;
	
	
	@PostMapping("/tache")
	public Tache tache(@RequestParam(name = "userId") int userId,@RequestParam(name = "employeId") int employeId,
			@RequestBody Tache tache) {
	    return service.save_tache(userId,employeId, tache);
	}
	
	

	@GetMapping("/tache/{id}")
	public Tache findUserById(@PathVariable(name = "id") int tacheId) { 
		return service.getTacheById(tacheId);
	}
	@GetMapping("/tachebypat")
	public List<Tache> findBypatientId(@RequestParam(name = "id") int id) { 
		return service.findBypatientId(id);
	}
	
	@GetMapping("/tachebyemp")
	public List<Tache> findByemployeId(@RequestParam(name = "id") int id) { 
		return service.findByemployeId(id);
	}
	
	
	@PostMapping("/tache/accept")
    public Tache acceptTache(@RequestParam(name = "tacheId") int tacheId )
                             {
        return service.acceptTache(tacheId);
    }
	
	
	@PostMapping("/tache/rejeter")
    public Tache rejeteTache(@RequestParam(name = "tacheId") int tacheId
                             ) {
        return service.rejeteTache(tacheId);
    }
	
	@GetMapping("/taches")
	public List<Tache> findAllTaches() { 
		System.out.println("taches");
		return service.getTaches();
	}
	
	@DeleteMapping("/deletetache/{id}")
	public String deleteTache (@PathVariable(name = "id") int tacheId) { 
		return service.deleteTache(tacheId);
	}
}
