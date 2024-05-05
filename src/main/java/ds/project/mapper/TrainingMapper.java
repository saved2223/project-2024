package ds.project.mapper;

import ds.project.dto.TrainingDto;
import ds.project.model.training.Training;
import org.mapstruct.*;
import org.springframework.context.annotation.Bean;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TrainingMapper {
    Training toEntity(TrainingDto trainingDto);

    TrainingDto toDto(Training training);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    Training partialUpdate(TrainingDto trainingDto, @MappingTarget Training training);
}