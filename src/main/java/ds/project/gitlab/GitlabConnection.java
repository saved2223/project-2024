package ds.project.gitlab;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GitlabConnection {
    GitLabApi gitLabApi;
    public GitlabConnection(){
        gitLabApi = new GitLabApi("http://172.30.16.1/", "glpat-d2hFM_Rn1vB_n8kpzrHC");
    }
    public List<Project> connect() throws GitLabApiException {
        return gitLabApi.getProjectApi().getProjects();
    }
}
