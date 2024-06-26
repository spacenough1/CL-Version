# CL-Version

Cl-Version allows you to check for new versions of a specified repository on GitHub. It uses a builder pattern for easy and flexible instance creation.

---

### Quick Start

### 1. Add CL-Version to your project

### Gradle
```groovy
repositories {
  maven { url 'https://jitpack.io' }
  mavenCentral()
}

dependencies {
  implementation 'com.github.spacenough1:CL-Version:master-SNAPSHOT'
}
```
### 2. Usage Example

Here's how to use `VersionControl` with `VersionControlBuilder`:

```java
// if you have private repository you have to add GitHub token to ensure authentication
// #setGithubToken("token")
import pl.codelen.libs.VersionControl;
import pl.codelen.libs.VersionControlBuilder;

public class Main {
  static VersionControl versionControl;
  public static void main(String[] args) {
    versionControl = new VersionControlBuilder()
            .setCurrentVersion("1.1.0")
            .setRepositoryName("TestRepository")
            .setRepositoryOwner("spacenough1")
            .build();
    System.out.println("Is new version available: " + (versionControl.isNewVersion() ? "yes" : "no"));
    System.out.println("Latest version is: " + versionControl.latestVersionNumber());
  }
}
```

### 3. Builder Methods

- `setGithubToken(String githubToken)`: Sets authentication for private repositories
- `setRepositoryName(String repositoryName)`: Sets the repository name.
- `setRepositoryOwner(String repositoryOwner)`: Sets the repository owner.
- `setCurrentVersion(String currentVersion)`: Sets the current version of the repository.
- `build()`: Creates an instance of `VersionControl`.

### Methods

### VersionControl

- `boolean isNewVersion()`: Checks if a new version is available.
- `String latestVersionNumber()`: Returns the latest version number if available, otherwise returns the current version number.

---

1. **Initialize Builder**
2. **Set Parameters**:
    
```java
  builder.setRepositoryName("example-repo")
        .setRepositoryOwner("example-owner")
        .setCurrentVersion("1.0.0");
```
    
3. **Build VersionControl Instance**:
    
```java
  VersionConrol versionControl = builder.build();
```
    
4. **Check for New Version**:
    
```java
  System.out.println("Is new version available: " + (versionControl.isNewVersion() ? "yes" : "no"));
  System.out.println("Latest version is: " + versionControl.latestVersionNumber());
```
    

### Notes

- Ensure your project has access to the internet to fetch version details from GitHub.
- Handle `IOException` appropriately in your implementation.
