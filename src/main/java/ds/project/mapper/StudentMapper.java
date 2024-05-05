package ds.project.mapper;

import ds.project.dto.student.StudentDto;
import ds.project.model.student.Student;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {
    @Mapping(source = "studyStatusStudy_status", target = "studyStatus.study_status")
    @Mapping(source = "personCity", target = "person.city")
    @Mapping(source = "personDateOfBirth", target = "person.dateOfBirth")
    @Mapping(source = "personAddInfo", target = "person.addInfo")
    @Mapping(source = "personTelegram", target = "person.telegram")
    @Mapping(source = "personPhone", target = "person.phone")
    @Mapping(source = "personEmail", target = "person.email")
    @Mapping(source = "personName", target = "person.name")
    @Mapping(source = "personUsername", target = "person.username")
    Student toEntity(StudentDto studentDto);

    @InheritInverseConfiguration(name = "toEntity")
    StudentDto toDto(Student student);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Student partialUpdate(StudentDto studentDto, @MappingTarget Student student);
}