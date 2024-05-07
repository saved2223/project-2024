package ds.project.service;

import ds.project.dto.CheckLessonTasksDTO;
import ds.project.exception.GitlabException;
import ds.project.model.Task;
import ds.project.model.person.Person;
import ds.project.model.student.Student;
import ds.project.repository.MessageRepository;
import ds.project.repository.PersonRepository;
import ds.project.repository.StudentRepository;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
public class GitlabService {

    private final CharSequence defaultPass = "pass"; //todo в проперти поменять

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private MessageService messageService;


    private GitLabApi gitLabApi;

    public GitlabService() {
        gitLabApi = new GitLabApi("http://172.30.16.1/", "glpat-d2hFM_Rn1vB_n8kpzrHC"); //todo сделать из проперти
    }

    public void createGitlabUsersForTraining(UUID trainingId) {
        List<Student> students = studentRepository.findByTrainings_Id(trainingId);
        for (Student student : students) {
            User user = new User();
            user.setName(student.getPerson().getName());
            user.setEmail(student.getPerson().getEmail());
            user.setUsername(student.getPerson().getUsername()); //todo что-то придумать с отчисленными
            try {
                gitLabApi.getUserApi().createUser(user, defaultPass, false);
            } catch (GitLabApiException e) {
                throw new GitlabException(e.getMessage());
            }
        }
    }

    public void forkAllTaskReposToActiveUsers(Set<Task> tasks) {
        try {
            List<User> activeUsers = gitLabApi.getUserApi().getActiveUsers();
            for (Task task : tasks) {
                for (User user : activeUsers) {
                    Project project = gitLabApi.getProjectApi().forkProject(task.getReferenceRepository(), user.getUsername());
                    gitLabApi.getProjectApi().createProject(project);
                }
            }
        } catch (GitLabApiException e) {
            sendExceptionMessageToAllAdmins(e.getMessage());
        }
    }

    private void sendExceptionMessageToAllAdmins(String message) {
        List<Person> admins = personRepository.findByRoles_Name("ROLE_ADMIN");
        messageService.sendMessageToGroup(message, admins);
    }

    public List<CheckLessonTasksDTO> getAllCommitsForTask(Task task){
        try {
            List<CheckLessonTasksDTO> dtos = new ArrayList<>();
            List<Project> forkedProjects = gitLabApi.getProjectApi().getForks(task.getReferenceRepository());
            for (Project forkedProject : forkedProjects) {
                Commit lastCommit = getLastCommitOfAListOfCommits(
                        gitLabApi.getCommitsApi().getCommits(forkedProject.getPath()));
                if (lastCommit != null) {
                    dtos.add(new CheckLessonTasksDTO(
                            task.getId(),
                            task.getName(),
                            forkedProject.getOwner().getId().toString(),
                            forkedProject.getOwner().getUsername(),
                            (Date) lastCommit.getCreatedAt(),
                            lastCommit.getCommitterName(),
                            lastCommit.getWebUrl()
                    ));
                }
            }
            return dtos;
        } catch (GitLabApiException e) {
            throw new GitlabException(e.getMessage());
        }
    }

    private Commit getLastCommitOfAListOfCommits(List<Commit> commits) {
        if (commits.isEmpty()) return null;
        commits.sort(Comparator.comparing(Commit::getCreatedAt).reversed());
        return commits.get(0);
    }


}
