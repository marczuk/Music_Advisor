import advisor.Main;
import org.hyperskill.hstest.v4.stage.MainMethodTest;
import org.hyperskill.hstest.v4.testcase.CheckResult;
import org.hyperskill.hstest.v4.testcase.TestCase;

import java.util.List;

public class MusicAdvisorTest extends MainMethodTest {
    public MusicAdvisorTest() throws Exception {
        super(Main.class);
    }

    @Override
    public List<TestCase> generateTestCases() {
        return List.of(
            new TestCase()
                .setInput(
                    "new\n" +
                    "featured\n" +
                    "categories\n" +
                    "playlists Mood\n" +
                    "exit")
        );
    }

    @Override
    public CheckResult check(String reply, Object clue) {
        return new CheckResult(
            reply.contains("---NEW RELEASES---")
            && reply.contains("---FEATURED---")
            && reply.contains("---CATEGORIES---")
            && reply.contains("---GOODBYE!---")
            && reply.contains("PLAYLISTS---"));
    }
}
