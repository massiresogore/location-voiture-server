package com.msr.agenceloc.security;

import com.msr.agenceloc.client.ClientUser;
import com.msr.agenceloc.client.converter.ClientUserToUserDtoConverter;
import com.msr.agenceloc.client.dto.ClientUserDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final JwtProvider jwtProvider;
    private final ClientUserToUserDtoConverter userToUserDtoConverter;

    public AuthService(JwtProvider jwtProvider, ClientUserToUserDtoConverter userToUserDtoConverter) {
        this.jwtProvider = jwtProvider;
        this.userToUserDtoConverter = userToUserDtoConverter;
    }


    public Map<String, Object> createLoginInfo(Authentication authentication) {
        //Create UserInfo.
        MyUserPrincipal principal = (MyUserPrincipal) authentication.getPrincipal();
        ClientUser user = principal.getClientUser();
        ClientUserDto userDto = this.userToUserDtoConverter.convert(user);


        //Craete JWT
        String token =  this.jwtProvider.createToken(authentication);
        //String token = "i'm token";

        Map<String, Object> loginResultMap = new HashMap<>();

        loginResultMap.put("userInfo",userDto);
        loginResultMap.put("token",token);

        return loginResultMap;
    }
}
