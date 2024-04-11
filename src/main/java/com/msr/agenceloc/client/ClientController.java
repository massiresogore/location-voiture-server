package com.msr.agenceloc.client;


import com.msr.agenceloc.client.converter.ClientUserDtoToUserConverter;
import com.msr.agenceloc.client.converter.ClientUserToUserDtoConverter;
import com.msr.agenceloc.client.dto.ClientUserDto;
import com.msr.agenceloc.system.Result;
import com.msr.agenceloc.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.endpoint.base-url}/users")
public class ClientController {

    private final ClientUserService clientUserService;
    private final ClientUserDtoToUserConverter clientUserDtoToUserConverter;
    private final ClientUserToUserDtoConverter clientUserToUserDtoConverter;

    public ClientController(ClientUserService clientUserService, ClientUserDtoToUserConverter clientUserDtoToUserConverter, ClientUserToUserDtoConverter clientUserToUserDtoConverter) {
        this.clientUserService = clientUserService;
        this.clientUserDtoToUserConverter = clientUserDtoToUserConverter;
        this.clientUserToUserDtoConverter = clientUserToUserDtoConverter;
    }

    @PostMapping
    public Result addUser(@Valid @RequestBody ClientUser clientUser)
    {
        return new Result(true, StatusCode.SUCCESS,"User create success",this.clientUserService.save(clientUser));
    }

    @GetMapping("/{userId}")
    public Result findUserById(@PathVariable Long userId)
    {
        return new Result(true,StatusCode.SUCCESS,"Find user success",this.clientUserToUserDtoConverter.convert(this.clientUserService.findById(userId)));
    }

    @PutMapping("/{userId}")
    public Result updateUser(@PathVariable("userId") Long userId,@Valid @RequestBody ClientUserDto clientUserDto)
    {
        //Convert userDto to ClientUser
        ClientUser clientUser = this.clientUserDtoToUserConverter.convert(clientUserDto);

        //Update
       ClientUser updatedUser = this.clientUserService.updateUser(userId,clientUser);

        return new Result(true,
                StatusCode.SUCCESS,
                "Update user success",
                this.clientUserToUserDtoConverter.convert(updatedUser)
        );

    }

    @GetMapping
    public Result findAllUser()
    {
        List<ClientUser> userList = this.clientUserService.findAll();

        List<ClientUserDto> clientUserDtoList = userList.stream().map(this.clientUserToUserDtoConverter::convert).toList();

        return new Result(true,StatusCode.SUCCESS,"Find all user success",clientUserDtoList);
    }

    @DeleteMapping("/{userId}")
    public Result deleteUser(@PathVariable Long userId)
    {
        this.clientUserService.delete(userId);
        return new Result(true,StatusCode.SUCCESS,"Delete user success");
    }
}
