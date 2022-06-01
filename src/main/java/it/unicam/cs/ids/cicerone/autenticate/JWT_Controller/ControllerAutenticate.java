package it.unicam.cs.ids.cicerone.autenticate.JWT_Controller;

import it.unicam.cs.ids.cicerone.autenticate.JWT_Config.JwtTokenUtil;
import it.unicam.cs.ids.cicerone.autenticate.JWT_Model.JwtRequestLogin;
import it.unicam.cs.ids.cicerone.autenticate.JWT_Model.JwtResponse;
import it.unicam.cs.ids.cicerone.autenticate.JWT_Service.CustomUserDetails;
import it.unicam.cs.ids.cicerone.controller.users.ControllerAdmin;
import it.unicam.cs.ids.cicerone.controller.users.ControllerCicerino;
import it.unicam.cs.ids.cicerone.controller.users.ControllerTurista;
import it.unicam.cs.ids.cicerone.model.users.Admin;
import it.unicam.cs.ids.cicerone.model.users.Associazione;
import it.unicam.cs.ids.cicerone.model.users.Cicerino;
import it.unicam.cs.ids.cicerone.model.users.Turista;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryAdmin;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryAssociazione;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryCicerino;
import it.unicam.cs.ids.cicerone.repository.users.RepositoryTurista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins ="*" )
public class ControllerAutenticate {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetails customUserDetails;

    @Autowired
    private RepositoryCicerino repositoryCicerino;

    @Autowired
    private RepositoryTurista repositoryTurista;

    @Autowired
    private RepositoryAssociazione repositoryAssociazione;

    @Autowired
    private RepositoryAdmin repositoryAdmin;

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseEntity<?> generateLoginToken(@RequestBody JwtRequestLogin jwtRequestLogin) throws Exception {
        System.out.println("Inside Controller");
        System.out.println(jwtRequestLogin);
        try{
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequestLogin.getUsername(), jwtRequestLogin.getPassword()));
        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }catch(BadCredentialsException e){
            e.printStackTrace();
            throw new Exception("Bad credetials");
        }
        UserDetails userDetails = this.customUserDetails.loadUserByUsername(jwtRequestLogin.getUsername());

        String token = this.jwtTokenUtil.generateToken(userDetails);
        String username = this.jwtTokenUtil.extractUsername(token);
        System.out.println("JWT"+token);
        return ResponseEntity.ok(new JwtResponse(token,username));
    }

    @RequestMapping(value = "/newTurista", method = RequestMethod.POST)
    public ResponseEntity<?> newTurista(@RequestBody Turista turista) throws Exception {
        turista.setNome(turista.getNome());
        turista.setCognome(turista.getCognome());
        turista.setResidenza(turista.getResidenza());
        turista.setNumeroTelefonico(turista.getNumeroTelefonico());
        turista.setEmail(turista.getEmail());
        turista.setUsername(turista.getUsername());
        turista.setPassword(new BCryptPasswordEncoder().encode(turista.getPassword()));
        return ResponseEntity.ok(repositoryTurista.save(turista));
    }

    @RequestMapping(value = "/newCicerino", method = RequestMethod.POST)
    public ResponseEntity<?> newCicerino(@RequestBody Cicerino cicerino) throws Exception {
        cicerino.setNome(cicerino.getNome());
        cicerino.setCognome(cicerino.getCognome());
        cicerino.setResidenza(cicerino.getResidenza());
        cicerino.setEmail(cicerino.getEmail());
        cicerino.setDataNascita(cicerino.getDataNascita());
        cicerino.setUsername(cicerino.getUsername());
        cicerino.setPassword(new BCryptPasswordEncoder().encode(cicerino.getPassword()));
        cicerino.setVerificato(cicerino.getVerificato());
        return ResponseEntity.ok(repositoryCicerino.save(cicerino));
    }

    @RequestMapping(value = "/newAssociazione", method = RequestMethod.POST)
    public ResponseEntity<?> newAssociazione(@RequestBody Associazione associazione) throws Exception {
        associazione.setNomeAssociazione(associazione.getNomeAssociazione());
        associazione.setPIva(associazione.getPIva());
        associazione.setResidenza(associazione.getResidenza());
        associazione.setUsername(associazione.getUsername());
        associazione.setEmail(associazione.getEmail());
        associazione.setPassword(new BCryptPasswordEncoder().encode(associazione.getPassword()));
        associazione.setVerificato(associazione.getVerificato());
        return ResponseEntity.ok(repositoryAssociazione.save(associazione));
    }

    @RequestMapping(value = "/newAdmin", method = RequestMethod.POST)
    public ResponseEntity<?> newAdmin(@RequestBody Admin admin) throws Exception {
        admin.setEmail(admin.getEmail());
        admin.setPassword(admin.getPassword());
        admin.setUsername(new BCryptPasswordEncoder().encode(admin.getPassword()));
        return ResponseEntity.ok(repositoryAdmin.save(admin));
    }


}
