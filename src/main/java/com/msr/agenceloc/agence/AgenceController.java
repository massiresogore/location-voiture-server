package com.msr.agenceloc.agence;


import com.msr.agenceloc.agence.converter.AgenceDtoToAgenceConverter;
import com.msr.agenceloc.agence.converter.AgenceToAgenceDtoConverter;
import com.msr.agenceloc.agence.dto.AgenceDto;
import com.msr.agenceloc.system.Result;
import com.msr.agenceloc.system.StatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}/agences")
public class AgenceController {

    public final AgenceService agenceService;
    public final AgenceToAgenceDtoConverter agenceToAgenceDtoConverter;
    public final AgenceDtoToAgenceConverter agenceDtoToAgenceConverter;

    public AgenceController(AgenceService agenceService, AgenceToAgenceDtoConverter agenceToAgenceDtoConverter, AgenceDtoToAgenceConverter agenceDtoToAgenceConverter) {
        this.agenceService = agenceService;
        this.agenceToAgenceDtoConverter = agenceToAgenceDtoConverter;
        this.agenceDtoToAgenceConverter = agenceDtoToAgenceConverter;
    }

    @GetMapping
    public Result getAllAgence()
    {
        return new Result(


                true,
                StatusCode.SUCCESS,
                "Find all success",
                this.agenceService.getAllAgence().stream().map(agenceToAgenceDtoConverter::convert).collect(Collectors.toList())
        );
    }

    @GetMapping("/{agenceId}")
    public Result getAgneceById(@PathVariable("agenceId") Long agenceId)
    {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Find one success",
                this.agenceToAgenceDtoConverter.convert(this.agenceService.findById(agenceId))

        );
    }

    @DeleteMapping("/{agenceId}")
    public Result deleteAgence(@PathVariable("agenceId") Long agenceId)
    {
        this.agenceService.delete(agenceId);
        return  new Result(true,StatusCode.SUCCESS,"Detele agence success");
    }

    @PostMapping
    public Result addAgence(@Valid @RequestBody AgenceDto agenceDto)
    {
       // System.out.println(agenceDto);
        //convert to agence
       Agence agence = agenceDtoToAgenceConverter.convert(agenceDto);
       Agence savedAgence = this.agenceService.save(agence);


        //convert to AgenceDto and  send response
        return  new Result(true,StatusCode.SUCCESS,
                "Create agence success",
                agenceToAgenceDtoConverter.convert(savedAgence));
    }

    @PutMapping("/{agenceId}")
    public Result updateAgence(@Valid @RequestBody AgenceDto agenceDto, @PathVariable Long agenceId)
    {
       // System.out.println(agenceDto);
      return  new Result(true,StatusCode.SUCCESS,
                "Update success",
                this.agenceToAgenceDtoConverter.convert(this.agenceService.update(agenceId,this.agenceDtoToAgenceConverter.convert(agenceDto))));
    }

}
