package com.msr.agenceloc.agence.converter;

import com.msr.agenceloc.agence.Agence;
import com.msr.agenceloc.agence.dto.AgenceDto;
import com.msr.agenceloc.automobile.repositories.AutomobilRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AgenceToAgenceDtoConverter implements Converter<Agence, AgenceDto> {
    private final AutomobilRepository automobilRepository;

    public AgenceToAgenceDtoConverter(AutomobilRepository automobilRepository) {
        this.automobilRepository = automobilRepository;
    }


    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public AgenceDto convert(Agence source) {
     return  new AgenceDto(
             source.getAgenceId(),
             source.getNom(),
             source.getEmail(),
             source.getTel(),
             source.getAdresse(),
             this.automobilRepository.findAllByAgence(source).size()
     );

    }
}
