package com.burskey.prayer;

import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;

import java.util.Locale;
import java.util.Map;

public class DTOTransformer implements TypeRegistryConfigurer {
    public Locale locale() {
        return Locale.ENGLISH;
    }

    public void configureTypeRegistry(TypeRegistry typeRegistry) {
        typeRegistry.defineDataTableType(new DataTableType(ConfigurationDTO.class,
                        (Map<String, String> row) ->
                        {
                            ConfigurationDTO dto = new ConfigurationDTO();
                            dto.durationOfScheduleInDays  = row.get("Duration of schedule in Days");
                            dto.durationOfIndividualTeamsInDays= row.get("Duration of individual teams in days");
                            return dto;
                        }
                )
        );

        typeRegistry.defineDataTableType(new DataTableType(ParticipantDTO.class,
                        (Map<String, String> row) ->
                        {
                            ParticipantDTO dto = new ParticipantDTO();
                            dto.first = row.get("First");
                            dto.last = row.get("Last");
                            return dto;
                        }
                )
        );


    }
}