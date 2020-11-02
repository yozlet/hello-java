import com.launchdarkly.sdk.LDUser;
import com.launchdarkly.sdk.LDValue;
import com.launchdarkly.sdk.server.LDClient;

import java.io.IOException;

public class Hello {

  // Set SDK_KEY to your LaunchDarkly SDK key before compiling
  static final String SDK_KEY = "sdk-dd6dc077-97f0-4aa5-a366-81855423818e";

  // Set FEATURE_FLAG_KEY to the feature flag key you want to evaluate
  static final String FEATURE_FLAG_KEY = "example-flag";

  public static void main(String... args) throws IOException {
    if (SDK_KEY.equals("")) {
      System.out.println("Please edit Hello.java to set SDK_KEY to your LaunchDarkly SDK key first");
      System.exit(1);
    }

    LDClient client = new LDClient(SDK_KEY);

    // Set up the user properties. This user should appear on your LaunchDarkly users dashboard
    // soon after you run the demo.
    LDUser user = new LDUser.Builder("bob@example.com")
                            .firstName("Bob")
                            .lastName("Loblaw")
                            .custom("groups", LDValue.buildArray().add("beta_testers").build())
                            .build();

    boolean showFeature = client.boolVariation("example-flag", user, false);

    System.out.println("Feature flag '" + FEATURE_FLAG_KEY + "' is " + showFeature + " for this user");

    // Calling client.close() ensures that the SDK shuts down cleanly before the program exits.
    // Unless you do this, the SDK may not have a chance to deliver analytics events to LaunchDarkly,
    // so the user properties and the flag usage statistics may not appear on your dashboard. In a
    // normal long-running application, events would be delivered automatically in the background
    // and you would not need to close the client.
    client.close();
  }
}
