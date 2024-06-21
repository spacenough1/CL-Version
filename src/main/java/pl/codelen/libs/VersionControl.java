package pl.codelen.libs;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VersionControl {
  private final String repositoryName;
  private final String repositoryOwner;
  private final String currentVersion;
  private final String githubToken;

  VersionControl(@NotNull VersionControlBuilder builder) {
    this.repositoryName = builder.getRepositoryName();
    this.repositoryOwner = builder.getRepositoryOwner();
    this.currentVersion = builder.getCurrentVersion();
    this.githubToken = builder.getGithubToken();
  }

  public boolean isNewVersion() throws IOException {
    return isNewVersionAvailable(latestVersion());
  }

  public String latestVersionNumber() throws IOException {
    return isNewVersion() ? latestVersion() : currentVersion;
  }

  private @NotNull String latestVersion() throws IOException {
    if (githubToken != null) {
      URL url = new URL("https://api.github.com/repos/" + repositoryOwner + "/" + repositoryName + "/releases/latest");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Authorization", "token " + githubToken);
      conn.setRequestProperty("Accept", "application/json");

      BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

      String output;
      StringBuilder response = new StringBuilder();
      while ((output = br.readLine()) != null) {
        response.append(output);
      }

      conn.disconnect();

      return parseLatestVersion(response.toString());
    }

    URL url = new URL("https://api.github.com/repos/" + repositoryOwner + "/" + repositoryName + "/releases/latest");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Accept", "application/json");

    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

    String output;
    StringBuilder response = new StringBuilder();
    while ((output = br.readLine()) != null) {
      response.append(output);
    }

    conn.disconnect();

    return parseLatestVersion(response.toString());
  }

  private @NotNull String parseLatestVersion(@NotNull String response) {
    int index = response.indexOf("\"tag_name\"");

    int startIndex = response.indexOf(':', index) + 1;
    int endIndex = response.indexOf(',', startIndex);

    String version = response.substring(startIndex, endIndex).trim();

    version = version.replaceAll("[^\\d.]", "");

    if (version.startsWith("v")) {
      version = version.substring(1);
    }

    return version;
  }

  private boolean isNewVersionAvailable(@NotNull String latestVersion) {
    String[] localParts = currentVersion.split("\\.");
    String[] latestParts = latestVersion.split("\\.");

    for (int i = 0; i < Math.min(localParts.length, latestParts.length); i++) {
      int localPart = Integer.parseInt(localParts[i]);
      int latestPart = Integer.parseInt(latestParts[i]);

      if (localPart < latestPart) {
        return true;
      } else if (localPart > latestPart) {
        return false;
      }
    }
    return localParts.length < latestParts.length;
  }
}
