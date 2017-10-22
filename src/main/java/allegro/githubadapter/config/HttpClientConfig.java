package allegro.githubadapter.config;

import allegro.githubadapter.service.api.GitHubApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
public class HttpClientConfig {

    @Value("${maxIdleConnections:2}")
    private int maxIdleConnections;

    @Value("${keepAliveDuration:15}")
    private int keepAliveDuration;

    @Bean
    public GitHubApi getGitHubApi() {

        OkHttpClient okHttp = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(maxIdleConnections, keepAliveDuration, SECONDS))
                .build();

        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GitHubApi.GITHUB_API_ADDRESS)
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GitHubApi api = retrofit.create(GitHubApi.class);
        return api;
    }
}
