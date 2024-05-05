package ds.project.mapper;

import ds.project.dto.MessageDto;
import ds.project.model.Message;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MessageMapper {
    @Mapping(source = "personToUsername", target = "personTo.username")
    @Mapping(source = "personFromUsername", target = "personFrom.username")
    Message toEntity(MessageDto messageDto);

    @InheritInverseConfiguration(name = "toEntity")
    MessageDto toDto(Message message);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Message partialUpdate(MessageDto messageDto, @MappingTarget Message message);
}