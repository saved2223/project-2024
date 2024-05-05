package ds.project.mapper;

import ds.project.dto.TrainingCreationDto;
import ds.project.model.training.Training;
import org.mapstruct.*;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TrainingCreationMapper {
    Training toEntity(TrainingCreationDto trainingDto);

    TrainingCreationDto toDto(Training training);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    Training partialUpdate(TrainingCreationDto trainingDto, @MappingTarget Training training);
}