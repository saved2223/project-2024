package ds.project.mapper;

import ds.project.model.person.Person;
import ds.project.dto.PersonRegisterDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonRegisterMapper {
    Person toEntity(PersonRegisterDto personRegisterDto);

    PersonRegisterDto toDto(Person person);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Person partialUpdate(PersonRegisterDto personRegisterDto, @MappingTarget Person person);
}