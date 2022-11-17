package com.example.JWTtokens.api;

import com.example.JWTtokens.domain.AppUser;
import com.example.JWTtokens.domain.Role;
import com.example.JWTtokens.service.AppUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final AppUserService appUserService;

    @Operation(summary = "Get a list of all users")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the users",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppUser.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content)
    })
    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getUsers() {
        return ResponseEntity.ok().body(appUserService.getUsers());
    }

    @Operation(summary = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "New user added",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AppUser.class)
                    ) }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Use another username",
                    content = @Content
            )
    })
    @PostMapping("/user/save")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser appUser) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(appUserService.saveUser(appUser));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(appUserService.saveRole(role));
    }

    @PostMapping("/role/assignToUser")
    public ResponseEntity<?> addRoleTOUser(@RequestBody RoleToUserForm form) {
        appUserService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}
