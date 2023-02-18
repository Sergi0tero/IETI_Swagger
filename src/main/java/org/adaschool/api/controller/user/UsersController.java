package org.adaschool.api.controller.user;

import org.adaschool.api.exception.UserNotFoundException;
import org.adaschool.api.repository.user.User;
import org.adaschool.api.repository.user.UserDto;
import org.adaschool.api.service.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users/")
public class UsersController {

    private final UsersService usersService;

    public UsersController(@Autowired UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        URI createdUserUri = URI.create("");
        return ResponseEntity.created(createdUserUri).body(usersService.save(new User(userDto)));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(usersService.all());
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable("id") String id) {
        Optional<User> user = usersService.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok().body(user);
        }
        else {
            throw new UserNotFoundException(id);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserDto userDto,@PathVariable("id") String id) {
        Optional<User> userToUpdate = usersService.findById(id);
        if (userToUpdate.isPresent()) {
            userToUpdate.get().update(userDto);
            return ResponseEntity.ok().body(usersService.save(userToUpdate.get()));
        }
        else {
            throw new UserNotFoundException(id);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        if (usersService.findById(id).isPresent()) {
            usersService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else {
            throw new UserNotFoundException(id);
        }
    }
}
