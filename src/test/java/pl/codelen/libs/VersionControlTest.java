package pl.codelen.libs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class VersionControlTest {
  private VersionControl versionControl;

  @BeforeEach
  void setUp() {
    versionControl = new VersionControlBuilder()
            .setCurrentVersion("1.0.0")
            .setRepositoryName("TestRepository")
            .setRepositoryOwner("spacenough1")
            .build();
  }

  @Test
  void testIsNewVersion() {
    try {
      assertFalse(versionControl.isNewVersion(), "Should return false as 1.0.0 is the latest version");
    } catch (IOException e) {
      fail("IOException occurred: " + e.getMessage());
    }
  }

  @Test
  void testLatestVersionNumber() {
    try {
      assertEquals("1.0.0", versionControl.latestVersionNumber(), "Should return the latest version in this case 1.0.0");
    } catch (IOException e) {
      fail("IOException occurred: " + e.getMessage());
    }
  }
}