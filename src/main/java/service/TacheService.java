package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.TacheRepository;
import repository.UserRepository;
import Model.Tache;
import Model.User;
import jakarta.persistence.EntityNotFoundException;



@Service
public class TacheService {
	@Autowired
    private TacheRepository tacheRepository;
	

    @Autowired
    private UserService userService;
	
    public Tache save_tache(int userId, int employeId, Tache tache) {
    	
        User user = userService.getUserById(userId);
        User employe = userService.getUserById(employeId);
        if (user != null) {
            tache.setPatient(user); // Set the user as the patient
            tache.setEmploye(employe);
            
            tache.setStatus("incomplete");
            
            return tacheRepository.save(tache);
        } else {
            throw new EntityNotFoundException("User with ID " + userId + " not found.");
        }
    }
    
    
    
    public Tache acceptTache(int tacheId) {
        Tache tache = tacheRepository.findById(tacheId).orElseThrow(() -> new EntityNotFoundException("Tache with ID " + tacheId + " not found."));

            tache.setStatus("accepted");

            return tacheRepository.save(tache);
        
    }
    
    
    
    public Tache rejeteTache(int tacheId) {
        Tache tache = tacheRepository.findById(tacheId).orElseThrow(() -> new EntityNotFoundException("Tache with ID " + tacheId + " not found."));
        tache.setStatus("rejected");

        return tacheRepository.save(tache);
    }
    
	

	public List<Tache> getTaches(){
		return tacheRepository.findAll();
	}
	
	public List<Tache> findBypatientId(int patientId) {
		  User user = userService.getUserById(patientId);
		  return tacheRepository.findBypatientId(user);
		}
	
	public List<Tache> findByemployeId(int patientId) {
		  User user = userService.getUserById(patientId);
		  return tacheRepository.findByemployeId(user);
		}

	
	
	
	
	public String deleteTache(int id) {
		tacheRepository.deleteById(id);
		return "tache supprimée avec succés !"+id ;
	}
	
	


	public Tache getTacheById(int id) {
		return tacheRepository.findById(id).orElse(null);
	}
}
