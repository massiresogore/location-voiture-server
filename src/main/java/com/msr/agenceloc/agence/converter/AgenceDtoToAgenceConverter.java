package com.msr.agenceloc.agence.converter;

import com.msr.agenceloc.agence.Agence;
import com.msr.agenceloc.agence.dto.AgenceDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AgenceDtoToAgenceConverter implements Converter<AgenceDto, Agence> {


    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public Agence convert(AgenceDto source) {
        Agence agence = new Agence();
        agence.setAgenceId(source.agenceId());
        agence.setNom(source.nom());
        agence.setAdresse(source.adresse());
        agence.setEmail(source.email());
        agence.setTel(source.tel());;
        return agence;
    }
}
