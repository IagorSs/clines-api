package br.com.caelum.clines.api.users;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import java.net.URI;

import java.util.List;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("{email}")
    UserView show(@PathVariable String email) {
        return service.showUserByEmail(email);
    }

    @GetMapping
    List<UserView> list() {
        return service.findAll();
    }

    @PostMapping
    ResponseEntity<?> create(@RequestBody @Valid UserForm form) {
        Long id = service.createUser(form);

        var uri = URI.create("/users/").resolve(id.toString());

        return created(uri).build();
    }
}
