package ds.project.mapper;

import ds.project.dto.student.StudentRegisterDto;
import ds.project.model.student.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentRegisterDtoMapper {
    Student toEntity(StudentRegisterDto studentRegisterDto);
}