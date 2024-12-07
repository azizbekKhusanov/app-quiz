package uz.tuit.appquiz.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.tuit.appquiz.dto.CreateSubjectDTO;
import uz.tuit.appquiz.dto.SubjectDTO;
import uz.tuit.appquiz.entity.Subject;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class SubjectMapper{

    private final TestMapper testMapper;

    public SubjectDTO convertToDTO(Subject subject) {
        return SubjectDTO.builder()
                .id(subject.getId())
                .name(subject.getName())
                .allTests(subject.getTests() != null ? subject.getTests()
                        .stream()
                        .map(testMapper::convertToDTO)
                        .toList() : new ArrayList<>())
                .build();
    }

    public Subject toSubject(CreateSubjectDTO createSubjectDTO) {
        return Subject.builder().name(createSubjectDTO.getName()).build();
    }
}
