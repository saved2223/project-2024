package ds.project.service;

import ds.project.dto.CheckLessonTasksDTO;
import ds.project.model.Task;
import ds.project.model.person.Person;
import ds.project.model.student.Student;
import ds.project.repository.PersonRepository;
import ds.project.repository.StudentRepository;
import org.gitlab4j.api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;

import java.sql.Date;
import java.util.*;

import static org.mockito.Mockito.*;

class GitlabServiceTest {
    @Mock
    CharSequence defaultPass;
    @Mock
    PersonRepository personRepository;
    @Mock
    StudentRepository studentRepository;
    @Mock
    MessageService messageService;
    @Mock
    Environment environment;
    @Mock
    GitLabApi gitLabApi;
    @InjectMocks
    GitlabService gitlabService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateGitlabUsersForTraining() {
        when(studentRepository.findByTrainings_Id(any(UUID.class))).thenReturn(Arrays.<Student>asList(new Student()));
        when(gitLabApi.getUserApi()).thenReturn(new UserApi(new GitLabApi(GitLabApi.ApiVersion.V3, "hostUrl", Constants.TokenType.ACCESS, "authToken", "secretToken", new HashMap<String, Object>() {{
            put("clientConfigProperties", "clientConfigProperties");
        }})));

        gitlabService.createGitlabUsersForTraining(new UUID(0L, 0L));
    }

    @Test
    void testForkAllTaskReposToActiveUsers() {
        when(personRepository.findByRoles_Name(anyString())).thenReturn(Arrays.<Person>asList(null));
        when(gitLabApi.getProjectApi()).thenReturn(new ProjectApi(new GitLabApi(GitLabApi.ApiVersion.V3, "hostUrl", Constants.TokenType.ACCESS, "authToken", "secretToken", new HashMap<String, Object>() {{
            put("clientConfigProperties", "clientConfigProperties");
        }})));
        when(gitLabApi.getUserApi()).thenReturn(new UserApi(new GitLabApi(GitLabApi.ApiVersion.V3, "hostUrl", Constants.TokenType.ACCESS, "authToken", "secretToken", new HashMap<String, Object>() {{
            put("clientConfigProperties", "clientConfigProperties");
        }})));

        gitlabService.forkAllTaskReposToActiveUsers(new HashSet<Task>(Arrays.asList(new Task())));
        verify(messageService).sendMessageToGroup(anyString(), any(List.class));
    }

    @Test
    void testGetAllCommitsForTask() {
        when(gitLabApi.getCommitsApi()).thenReturn(new CommitsApi(new GitLabApi(GitLabApi.ApiVersion.V3, "hostUrl", Constants.TokenType.ACCESS, "authToken", "secretToken", new HashMap<String, Object>() {{
            put("clientConfigProperties", "clientConfigProperties");
        }})));
        when(gitLabApi.getProjectApi()).thenReturn(new ProjectApi(new GitLabApi(GitLabApi.ApiVersion.V3, "hostUrl", Constants.TokenType.ACCESS, "authToken", "secretToken", new HashMap<String, Object>() {{
            put("clientConfigProperties", "clientConfigProperties");
        }})));

        List<CheckLessonTasksDTO> result = gitlabService.getAllCommitsForTask(new Task());
        Assertions.assertEquals(Arrays.<CheckLessonTasksDTO>asList(new CheckLessonTasksDTO(new UUID(0L, 0L), "taskName", "userID", "username", new Date(0, 0, 0), "commitAuthorUsername", "commitLink")), result);
    }
}
