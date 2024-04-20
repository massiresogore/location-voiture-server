package com.msr.agenceloc.client.converter;

import com.msr.agenceloc.client.ClientUser;
import com.msr.agenceloc.client.dto.ClientUserDto;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class ClientUserDtoToUserConverter implements Converter<ClientUserDto, ClientUser> {

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public ClientUser convert(ClientUserDto source) {
        ClientUser clientUser = new ClientUser();
        clientUser.setNom(source.nom());
        clientUser.setPrenom(source.prenom());
        clientUser.setClientUserId(source.clientUserId());
        clientUser.setEmail(source.email());
        clientUser.setPassword(source.password());
        clientUser.setRoles(source.roles());
        clientUser.setAdresse(source.adresse());
        return clientUser;
    }
}
