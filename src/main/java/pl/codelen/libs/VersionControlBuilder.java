package pl.codelen.libs;

public class VersionControlBuilder {
  private String repositoryName;
  private String repositoryOwner;
  private String currentVersion;

  public VersionControlBuilder setRepositoryName(String repositoryName) {
    this.repositoryName = repositoryName;
    return this;
  }

  public VersionControlBuilder setRepositoryOwner(String repositoryOwner) {
    this.repositoryOwner = repositoryOwner;
    return this;
  }

  public VersionControlBuilder setCurrentVersion(String currentVersion) {
    this.currentVersion = currentVersion;
    return this;
  }

  public VersionControl build() {
    return new VersionControl(this);
  }

  // Getters to access the private fields from the builder
  String getRepositoryName() {
    return repositoryName;
  }

  String getRepositoryOwner() {
    return repositoryOwner;
  }

  String getCurrentVersion() {
    return currentVersion;
  }
}
