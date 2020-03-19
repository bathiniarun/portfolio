package org.bitnick.portfolio.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.bitnick.portfolio.model.User;
import org.bitnick.portfolio.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Api(value = "UsersControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {
    private UserService userService;

    private Logger LOG = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    @ApiOperation("Gets the product with specific id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = User.class)})
    public Object getUser(@PathVariable(name = "id") String id) {
        return userService.getUser(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User saveUser(@RequestBody User userToSave) {
        return userService.saveUser(userToSave);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User userToUpdate, @PathVariable(name = "id") String id) {
        return userService.updateUser(userToUpdate, id);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable(name = "id") String id) {
        userService.deleteUser(id);
    }
}
