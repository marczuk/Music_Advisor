import advisor.Main;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.hyperskill.hstest.v4.stage.MainMethodTest;
import org.hyperskill.hstest.v4.testcase.CheckResult;
import org.hyperskill.hstest.v4.testcase.TestCase;

import java.util.List;
import java.util.function.Function;

class Attach {
    Function<String, CheckResult> func;

    Attach(Function<String, CheckResult> func) {
        this.func = func;
    }
}

public class MusicAdvisorTest extends MainMethodTest<Attach> {
    public MusicAdvisorTest() throws Exception {
        super(Main.class);
    }

    @Override
    public List<TestCase<Attach>> generateTestCases() {
        return List.of(
            new TestCase<Attach>()
                .setInput("auth\nexit")
                .setAttach(new Attach(reply -> {
                    if (!reply.contains("---SUCCESS---")) {
                        return new CheckResult(false,
                            "There is no \"---SUCCESS---\" after \"auth\" but should be");
                    }
                    int startBracket = reply.indexOf("{");
                    int endBracket = reply.indexOf("}");
                    if (startBracket == -1 || endBracket == -1) {
                        return new CheckResult(false,
                            "There is no JSON response in output after \"auth\" input line");
                    }
                    String json = reply.substring(startBracket, endBracket + 1);
                    JsonObject response = new JsonParser().parse(json).getAsJsonObject();
                    if (!response.has("access_token")) {
                        return new CheckResult(false,
                            "There is no access_token field in " +
                                "response JSON output after \"auth\" input line");
                    }
                    return CheckResult.TRUE;
                })),

            new TestCase<Attach>()
                .setInput("new\nexit")
                .setAttach(new Attach(reply -> {
                    if (!reply.strip().startsWith("Please, provide access for application.")) {
                        return new CheckResult(false,
                            "When no access provided you should output " +
                                "\"Please, provide access for application.\"");
                    }
                    return CheckResult.TRUE;
                })),

            new TestCase<Attach>()
                .setInput("featured\nexit")
                .setAttach(new Attach(reply -> {
                    if (!reply.strip().startsWith("Please, provide access for application.")) {
                        return new CheckResult(false,
                            "When no access provided you should output " +
                                "\"Please, provide access for application.\"");
                    }
                    return CheckResult.TRUE;
                })),

            new TestCase<Attach>()
                .setInput("auth\nnew\nfeatured\nexit")
                .setAttach(new Attach(reply -> {
                    if (!reply.contains("---NEW RELEASES---")) {
                        return new CheckResult(false,
                            "When \"new\" was inputted there should be \"---NEW RELEASES---\" line");
                    }
                    if (!reply.contains("---FEATURED---")) {
                        return new CheckResult(false,
                            "When \"featured\" was inputted there should be \"---FEATURED---\" line");
                    }
                    return CheckResult.TRUE;
                }))

        );
    }

    @Override
    public CheckResult check(String reply, Attach clue) {
        return clue.func.apply(reply);
    }
}
