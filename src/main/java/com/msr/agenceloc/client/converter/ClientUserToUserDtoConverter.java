package com.msr.agenceloc.client.converter;

import com.msr.agenceloc.client.ClientUser;
import com.msr.agenceloc.client.dto.ClientUserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClientUserToUserDtoConverter implements Converter<ClientUser, ClientUserDto> {

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public ClientUserDto convert(ClientUser source) {
        return  new ClientUserDto(
                source.getClientUserId(),
                source.getNom(),
                source.getPrenom(),
                source.getRoles(),
                source.getEmail(),
                source.getPassword(),
                source.getAdresse()
        );
    }
}
