import advisor.Main;

import org.hyperskill.hstest.v5.mocks.web.WebServerMock;
import org.hyperskill.hstest.v5.stage.BaseStageTest;
import org.hyperskill.hstest.v5.testcase.CheckResult;
import org.hyperskill.hstest.v5.testcase.TestCase;

import java.util.List;
import java.util.function.Function;

class Attach {
    Function<String, CheckResult> func;

    Attach(Function<String, CheckResult> func) {
        this.func = func;
    }
}

public class MusicAdvisorTest extends BaseStageTest<Attach> {
    public MusicAdvisorTest() throws Exception {
        super(Main.class);
    }

    private String accessToken = "21BSZ1CA3KR0cf0LxmiNK_E87ZqnkJ3DD89VOWAZ9f0aXJcsCiH7l5Om-EVhkIfwt1AZs5WeXgfEF69e4JqL3YX1IIW9zl91egTmgLkb4xLXWwhryty478CLoL2SM9VIY6HaHgxYxdmRFGWSzrgH3yEqcvPoLpd26D8Y";

    private String refreshToken = "AQCSmdQsvsvpneadsdq1brfKlbEWleTE3nprDwPbZgNSge5dVe_svYBG-RG-_PxIGxVvA7gSnehFJjDRAczLDbbdWPjW1yUq2gtKbbNrCQVAH5ZBtY8wAYskmOIW7zn3IEiBzg";

    private String tokenResponse = "{" +
        "\"access_token\":\"" + accessToken + "\"," +
        "\"token_type\":\"Bearer\"," +
        "\"expires_in\":3600," +
        "\"refresh_token\":" + "\"" + refreshToken + "\"," +
        "\"scope\":\"\"" +
        "}";

    private int accessServerPort = 45678;
    private int resourceServerPort = 56789;

    private String accessServerUrl = "http://127.0.0.1:" + accessServerPort;
    private String resourceServerUrl = "http://127.0.0.1:" + resourceServerPort;
    private String spotifyServerUrl = "https://api\\.spotify\\.com";

    private String apiCategoriesResponse = "{\n" +
        "    \"categories\": {\n" +
        "        \"href\": \"https://api.spotify.com/v1/browse/categories?offset=0&limit=20\",\n" +
        "        \"items\": [\n" +
        "            {\n" +
        "                \"href\": \"https://api.spotify.com/v1/browse/categories/toplists\",\n" +
        "                \"icons\": [\n" +
        "                    {\n" +
        "                        \"height\": 275,\n" +
        "                        \"url\": \"https://datsnxq1rwndn.cloudfront.net/media/derived/toplists_11160599e6a04ac5d6f2757f5511778f_0_0_275_275.jpg\",\n" +
        "                        \"width\": 275\n" +
        "                    }\n" +
        "                ],\n" +
        "                \"id\": \"toplists\",\n" +
        "                \"name\": \"Top Lists\"\n" +
        "            },\n" +
        "            {\n" +
        "                \"href\": \"https://api.spotify.com/v1/browse/categories/mood\",\n" +
        "                \"icons\": [\n" +
        "                    {\n" +
        "                        \"height\": 274,\n" +
        "                        \"url\": \"https://datsnxq1rwndn.cloudfront.net/media/original/mood-274x274_976986a31ac8c49794cbdc7246fd5ad7_274x274.jpg\",\n" +
        "                        \"width\": 274\n" +
        "                    }\n" +
        "                ],\n" +
        "                \"id\": \"mood\",\n" +
        "                \"name\": \"Super Mood\"\n" +
        "            },\n" +
        "            {\n" +
        "                \"href\": \"https://api.spotify.com/v1/browse/categories/party\",\n" +
        "                \"icons\": [\n" +
        "                    {\n" +
        "                        \"height\": 274,\n" +
        "                        \"url\": \"https://datsnxq1rwndn.cloudfront.net/media/derived/party-274x274_73d1907a7371c3bb96a288390a96ee27_0_0_274_274.jpg\",\n" +
        "                        \"width\": 274\n" +
        "                    }\n" +
        "                ],\n" +
        "                \"id\": \"party\",\n" +
        "                \"name\": \"Party Time\"\n" +
        "            }\n" +
        "        ],\n" +
        "        \"limit\": 20,\n" +
        "        \"next\": null,\n" +
        "        \"offset\": 0,\n" +
        "        \"previous\": null,\n" +
        "        \"total\": 3\n" +
        "    }\n" +
        "}".replaceAll(spotifyServerUrl, resourceServerUrl);


    private String apiPlaylistsPartyResponse = "{\n" +
        "    \"playlists\": {\n" +
        "        \"href\": \"https://api.spotify.com/v1/browse/categories/party/playlists?offset=0&limit=20\",\n" +
        "        \"items\": [\n" +
        "            {\n" +
        "                \"collaborative\": false,\n" +
        "                \"external_urls\": {\n" +
        "                    \"spotify\": \"http://open.spotify.com/user/spotifybrazilian/playlist/4k7EZPI3uKMz4aRRrLVfen\"\n" +
        "                },\n" +
        "                \"href\": \"https://api.spotify.com/v1/users/spotifybrazilian/playlists/4k7EZPI3uKMz4aRRrLVfen\",\n" +
        "                \"id\": \"4k7EZPI3uKMz4aRRrLVfen\",\n" +
        "                \"images\": [\n" +
        "                    {\n" +
        "                        \"height\": 300,\n" +
        "                        \"url\": \"https://i.scdn.co/image/bf6544c213532e9650088dfef76c8521093d970e\",\n" +
        "                        \"width\": 300\n" +
        "                    }\n" +
        "                ],\n" +
        "                \"name\": \"Noite Eletrônica\",\n" +
        "                \"owner\": {\n" +
        "                    \"external_urls\": {\n" +
        "                        \"spotify\": \"http://open.spotify.com/user/spotifybrazilian\"\n" +
        "                    },\n" +
        "                    \"href\": \"https://api.spotify.com/v1/users/spotifybrazilian\",\n" +
        "                    \"id\": \"spotifybrazilian\",\n" +
        "                    \"type\": \"user\",\n" +
        "                    \"uri\": \"spotify:user:spotifybrazilian\"\n" +
        "                },\n" +
        "                \"public\": null,\n" +
        "                \"snapshot_id\": \"PULvu1V2Ps8lzCxNXfNZTw4QbhBpaV0ZORc03Mw6oj6kQw9Ks2REwhL5Xcw/74wL\",\n" +
        "                \"tracks\": {\n" +
        "                    \"href\": \"https://api.spotify.com/v1/users/spotifybrazilian/playlists/4k7EZPI3uKMz4aRRrLVfen/tracks\",\n" +
        "                    \"total\": 100\n" +
        "                },\n" +
        "                \"type\": \"playlist\",\n" +
        "                \"uri\": \"spotify:user:spotifybrazilian:playlist:4k7EZPI3uKMz4aRRrLVfen\"\n" +
        "            },\n" +
        "            {\n" +
        "                \"collaborative\": false,\n" +
        "                \"external_urls\": {\n" +
        "                    \"spotify\": \"http://open.spotify.com/user/spotifybrazilian/playlist/4HZh0C9y80GzHDbHZyX770\"\n" +
        "                },\n" +
        "                \"href\": \"https://api.spotify.com/v1/users/spotifybrazilian/playlists/4HZh0C9y80GzHDbHZyX770\",\n" +
        "                \"id\": \"4HZh0C9y80GzHDbHZyX770\",\n" +
        "                \"images\": [\n" +
        "                    {\n" +
        "                        \"height\": 300,\n" +
        "                        \"url\": \"https://i.scdn.co/image/be6c333146674440123073cb32c1c8b851e69023\",\n" +
        "                        \"width\": 300\n" +
        "                    }\n" +
        "                ],\n" +
        "                \"name\": \"Festa Indie\",\n" +
        "                \"owner\": {\n" +
        "                    \"external_urls\": {\n" +
        "                        \"spotify\": \"http://open.spotify.com/user/spotifybrazilian\"\n" +
        "                    },\n" +
        "                    \"href\": \"https://api.spotify.com/v1/users/spotifybrazilian\",\n" +
        "                    \"id\": \"spotifybrazilian\",\n" +
        "                    \"type\": \"user\",\n" +
        "                    \"uri\": \"spotify:user:spotifybrazilian\"\n" +
        "                },\n" +
        "                \"public\": null,\n" +
        "                \"snapshot_id\": \"V66hh9k2HnLCdzHvtoXPv+tm0jp3ODM63SZ0oISfGnlHQxwG/scupDbKgIo99Zfz\",\n" +
        "                \"tracks\": {\n" +
        "                    \"href\": \"https://api.spotify.com/v1/users/spotifybrazilian/playlists/4HZh0C9y80GzHDbHZyX770/tracks\",\n" +
        "                    \"total\": 74\n" +
        "                },\n" +
        "                \"type\": \"playlist\",\n" +
        "                \"uri\": \"spotify:user:spotifybrazilian:playlist:4HZh0C9y80GzHDbHZyX770\"\n" +
        "            }\n" +
        "        ],\n" +
        "        \"limit\": 20,\n" +
        "        \"next\": null,\n" +
        "        \"offset\": 0,\n" +
        "        \"previous\": null,\n" +
        "        \"total\": 2\n" +
        "    }\n" +
        "}".replaceAll(spotifyServerUrl, resourceServerUrl);


    private String apiNewReleasesResponse = "{\n" +
        "    \"albums\": {\n" +
        "        \"href\": \"https://api.spotify.com/v1/browse/new-releases?offset=0&limit=20\",\n" +
        "        \"items\": [\n" +
        "            {\n" +
        "                \"album_type\": \"single\",\n" +
        "                \"artists\": [\n" +
        "                    {\n" +
        "                        \"external_urls\": {\n" +
        "                            \"spotify\": \"https://open.spotify.com/artist/2RdwBSPQiwcmiDo9kixcl8\"\n" +
        "                        },\n" +
        "                        \"href\": \"https://api.spotify.com/v1/artists/2RdwBSPQiwcmiDo9kixcl8\",\n" +
        "                        \"id\": \"2RdwBSPQiwcmiDo9kixcl8\",\n" +
        "                        \"name\": \"Pharrell Williams\",\n" +
        "                        \"type\": \"artist\",\n" +
        "                        \"uri\": \"spotify:artist:2RdwBSPQiwcmiDo9kixcl8\"\n" +
        "                    }\n" +
        "                ],\n" +
        "                \"available_markets\": [\n" +
        "                    \"AD\"\n" +
        "                ],\n" +
        "                \"external_urls\": {\n" +
        "                    \"spotify\": \"https://open.spotify.com/album/5ZX4m5aVSmWQ5iHAPQpT71\"\n" +
        "                },\n" +
        "                \"href\": \"https://api.spotify.com/v1/albums/5ZX4m5aVSmWQ5iHAPQpT71\",\n" +
        "                \"id\": \"5ZX4m5aVSmWQ5iHAPQpT71\",\n" +
        "                \"images\": [\n" +
        "                    {\n" +
        "                        \"height\": 640,\n" +
        "                        \"url\": \"https://i.scdn.co/image/e6b635ebe3ef4ba22492f5698a7b5d417f78b88a\",\n" +
        "                        \"width\": 640\n" +
        "                    },\n" +
        "                    {\n" +
        "                        \"height\": 300,\n" +
        "                        \"url\": \"https://i.scdn.co/image/92ae5b0fe64870c09004dd2e745a4fb1bf7de39d\",\n" +
        "                        \"width\": 300\n" +
        "                    },\n" +
        "                    {\n" +
        "                        \"height\": 64,\n" +
        "                        \"url\": \"https://i.scdn.co/image/8a7ab6fc2c9f678308ba0f694ecd5718dc6bc930\",\n" +
        "                        \"width\": 64\n" +
        "                    }\n" +
        "                ],\n" +
        "                \"name\": \"Runnin'\",\n" +
        "                \"type\": \"album\",\n" +
        "                \"uri\": \"spotify:album:5ZX4m5aVSmWQ5iHAPQpT71\"\n" +
        "            },\n" +
        "            {\n" +
        "                \"album_type\": \"single\",\n" +
        "                \"artists\": [\n" +
        "                    {\n" +
        "                        \"external_urls\": {\n" +
        "                            \"spotify\": \"https://open.spotify.com/artist/3TVXtAsR1Inumwj472S9r4\"\n" +
        "                        },\n" +
        "                        \"href\": \"https://api.spotify.com/v1/artists/3TVXtAsR1Inumwj472S9r4\",\n" +
        "                        \"id\": \"3TVXtAsR1Inumwj472S9r4\",\n" +
        "                        \"name\": \"Drake2\",\n" +
        "                        \"type\": \"artist\",\n" +
        "                        \"uri\": \"spotify:artist:3TVXtAsR1Inumwj472S9r4\"\n" +
        "                    }\n" +
        "                ],\n" +
        "                \"available_markets\": [\n" +
        "                    \"AD\"\n" +
        "                ],\n" +
        "                \"external_urls\": {\n" +
        "                    \"spotify\": \"https://open.spotify.com/album/0geTzdk2InlqIoB16fW9Nd\"\n" +
        "                },\n" +
        "                \"href\": \"https://api.spotify.com/v1/albums/0geTzdk2InlqIoB16fW9Nd\",\n" +
        "                \"id\": \"0geTzdk2InlqIoB16fW9Nd\",\n" +
        "                \"images\": [\n" +
        "                    {\n" +
        "                        \"height\": 640,\n" +
        "                        \"url\": \"https://i.scdn.co/image/d40e9c3d22bde2fbdb2ecc03cccd7a0e77f42e4c\",\n" +
        "                        \"width\": 640\n" +
        "                    },\n" +
        "                    {\n" +
        "                        \"height\": 300,\n" +
        "                        \"url\": \"https://i.scdn.co/image/dff06a3375f6d9b32ecb081eb9a60bbafecb5731\",\n" +
        "                        \"width\": 300\n" +
        "                    },\n" +
        "                    {\n" +
        "                        \"height\": 64,\n" +
        "                        \"url\": \"https://i.scdn.co/image/808a02bd7fc59b0652c9df9f68675edbffe07a79\",\n" +
        "                        \"width\": 64\n" +
        "                    }\n" +
        "                ],\n" +
        "                \"name\": \"Sneakin’\",\n" +
        "                \"type\": \"album\",\n" +
        "                \"uri\": \"spotify:album:0geTzdk2InlqIoB16fW9Nd\"\n" +
        "            }\n" +
        "        ],\n" +
        "        \"limit\": 20,\n" +
        "        \"next\": null,\n" +
        "        \"offset\": 0,\n" +
        "        \"previous\": null,\n" +
        "        \"total\": 2\n" +
        "    }\n" +
        "}".replaceAll(spotifyServerUrl, resourceServerUrl);


    private String apiFeaturedPlaylistsResponse = "{\n" +
        "    \"message\": \"Monday morning music, coming right up!\",\n" +
        "    \"playlists\": {\n" +
        "        \"href\": \"https://api.spotify.com/v1/browse/featured-playlists?offset=0&limit=20\",\n" +
        "        \"items\": [\n" +
        "            {\n" +
        "                \"collaborative\": false,\n" +
        "                \"external_urls\": {\n" +
        "                    \"spotify\": \"http://open.spotify.com/user/spotify/playlist/6ftJBzU2LLQcaKefMi7ee7\"\n" +
        "                },\n" +
        "                \"href\": \"https://api.spotify.com/v1/users/spotify/playlists/6ftJBzU2LLQcaKefMi7ee7\",\n" +
        "                \"id\": \"6ftJBzU2LLQcaKefMi7ee7\",\n" +
        "                \"images\": [\n" +
        "                    {\n" +
        "                        \"height\": 300,\n" +
        "                        \"url\": \"https://i.scdn.co/image/7bd33c65ebd1e45975bbcbbf513bafe272f033c7\",\n" +
        "                        \"width\": 300\n" +
        "                    }\n" +
        "                ],\n" +
        "                \"name\": \"Monday Morning Mood\",\n" +
        "                \"owner\": {\n" +
        "                    \"external_urls\": {\n" +
        "                        \"spotify\": \"http://open.spotify.com/user/spotify\"\n" +
        "                    },\n" +
        "                    \"href\": \"https://api.spotify.com/v1/users/spotify\",\n" +
        "                    \"id\": \"spotify\",\n" +
        "                    \"type\": \"user\",\n" +
        "                    \"uri\": \"spotify:user:spotify\"\n" +
        "                },\n" +
        "                \"public\": null,\n" +
        "                \"snapshot_id\": \"WwGvSIVUkUvGvqjgj/bQHlRycYmJ2TkoIxYfoalWlmIZT6TvsgvGMgtQ2dGbkrAW\",\n" +
        "                \"tracks\": {\n" +
        "                    \"href\": \"https://api.spotify.com/v1/users/spotify/playlists/6ftJBzU2LLQcaKefMi7ee7/tracks\",\n" +
        "                    \"total\": 245\n" +
        "                },\n" +
        "                \"type\": \"playlist\",\n" +
        "                \"uri\": \"spotify:user:spotify:playlist:6ftJBzU2LLQcaKefMi7ee7\"\n" +
        "            },\n" +
        "            {\n" +
        "                \"collaborative\": false,\n" +
        "                \"external_urls\": {\n" +
        "                    \"spotify\": \"http://open.spotify.com/user/spotify__sverige/playlist/4uOEx4OUrkoGNZoIlWMUbO\"\n" +
        "                },\n" +
        "                \"href\": \"https://api.spotify.com/v1/users/spotify__sverige/playlists/4uOEx4OUrkoGNZoIlWMUbO\",\n" +
        "                \"id\": \"4uOEx4OUrkoGNZoIlWMUbO\",\n" +
        "                \"images\": [\n" +
        "                    {\n" +
        "                        \"height\": 300,\n" +
        "                        \"url\": \"https://i.scdn.co/image/24aa1d1b491dd529b9c03392f350740ed73438d8\",\n" +
        "                        \"width\": 300\n" +
        "                    }\n" +
        "                ],\n" +
        "                \"name\": \"Upp och hoppa!\",\n" +
        "                \"owner\": {\n" +
        "                    \"external_urls\": {\n" +
        "                        \"spotify\": \"http://open.spotify.com/user/spotify__sverige\"\n" +
        "                    },\n" +
        "                    \"href\": \"https://api.spotify.com/v1/users/spotify__sverige\",\n" +
        "                    \"id\": \"spotify__sverige\",\n" +
        "                    \"type\": \"user\",\n" +
        "                    \"uri\": \"spotify:user:spotify__sverige\"\n" +
        "                },\n" +
        "                \"public\": null,\n" +
        "                \"snapshot_id\": \"0j9Rcbt2KtCXEXKtKy/tnSL5r4byjDBOIVY1dn4S6GV73EEUgNuK2hU+QyDuNnXz\",\n" +
        "                \"tracks\": {\n" +
        "                    \"href\": \"https://api.spotify.com/v1/users/spotify__sverige/playlists/4uOEx4OUrkoGNZoIlWMUbO/tracks\",\n" +
        "                    \"total\": 38\n" +
        "                },\n" +
        "                \"type\": \"playlist\",\n" +
        "                \"uri\": \"spotify:user:spotify__sverige:playlist:4uOEx4OUrkoGNZoIlWMUbO\"\n" +
        "            }\n" +
        "        ],\n" +
        "        \"limit\": 20,\n" +
        "        \"next\": null,\n" +
        "        \"offset\": 0,\n" +
        "        \"previous\": null,\n" +
        "        \"total\": 2\n" +
        "    }\n" +
        "}".replaceAll(spotifyServerUrl, resourceServerUrl);


    private String[] arguments = new String[]{
        "-access",
        accessServerUrl,
        "-resource",
        resourceServerUrl
    };

    private WebServerMock accessServer = new WebServerMock(accessServerPort)
        .setPage("/api/token", tokenResponse);

    private WebServerMock resourceServer = new WebServerMock(resourceServerPort)
        .setPage("/v1/browse/categories", apiCategoriesResponse)
        .setPage("/v1/browse/categories/party/playlists", apiPlaylistsPartyResponse)
        .setPage("/v1/browse/new-releases", apiNewReleasesResponse)
        .setPage("/v1/browse/featured-playlists", apiFeaturedPlaylistsResponse);

    @Override
    public List<TestCase<Attach>> generate() {
        return List.of(
            new TestCase<Attach>()
                .setInput("auth\nexit")
                .addArguments(arguments)
                .runWith(accessServer)
                .runWith(resourceServer)
                .setAttach(new Attach(reply -> {
                    if (!reply.contains("Success!")) {
                        return new CheckResult(false,
                            "There is no \"Success!\" after \"auth\" but should be");
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
                .setInput("categories\nexit")
                .setAttach(new Attach(reply -> {
                    if (!reply.strip().startsWith("Please, provide access for application.")) {
                        return new CheckResult(false,
                            "When no access provided you should output " +
                                "\"Please, provide access for application.\"");
                    }
                    return CheckResult.TRUE;
                })),

            new TestCase<Attach>()
                .setInput("playlists Party Time\nexit")
                .setAttach(new Attach(reply -> {
                    if (!reply.strip().startsWith("Please, provide access for application.")) {
                        return new CheckResult(false,
                            "When no access provided you should output " +
                                "\"Please, provide access for application.\"");
                    }
                    return CheckResult.TRUE;
                })),

            new TestCase<Attach>()
                .setInput("auth\nnew\nexit")
                .setAttach(new Attach(reply -> {

                    String album1 =
                        "Runnin'\n" +
                            "[Drake]\n" +
                            "https://open.spotify.com/album/5ZX4m5aVSmWQ5iHAPQpT71"
                                .replaceAll(spotifyServerUrl, resourceServerUrl);

                    String album2 =
                        "Sneakin’\n" +
                            "[Drake2]\n" +
                            "https://open.spotify.com/album/0geTzdk2InlqIoB16fW9Nd"
                                .replaceAll(spotifyServerUrl, resourceServerUrl);

                    if (!reply.contains(album1) || !reply.contains(album2)) {
                        return new CheckResult(false,
                            "There is no albums in correct format on \"new\" action");
                    }
                    return CheckResult.TRUE;
                })),

            new TestCase<Attach>()
                .setInput("auth\ncategories\nexit")
                .setAttach(new Attach(reply -> {

                    String category1 = "Top Lists";
                    String category2 = "Super Mood";
                    String category3 = "Party Time";

                    if (!reply.contains(category1)
                        || !reply.contains(category2)
                        || ! reply.contains(category3)) {

                        return new CheckResult(false,
                            "There is no categories in correct format on \"category\" action");
                    }
                    return CheckResult.TRUE;
                })),

            new TestCase<Attach>()
                .setInput("auth\nfeatured\nexit")
                .setAttach(new Attach(reply -> {

                    String featured1 =
                        "Monday Morning Mood\n" +
                            "http://open.spotify.com/user/spotify/playlist/6ftJBzU2LLQcaKefMi7ee7"
                                .replaceAll(spotifyServerUrl, resourceServerUrl);

                    String featured2 =
                        "Upp och hoppa!\n" +
                            "http://open.spotify.com/user/spotify__sverige/playlist/4uOEx4OUrkoGNZoIlWMUbO"
                                .replaceAll(spotifyServerUrl, resourceServerUrl);

                    if (!reply.contains(featured1)
                        || !reply.contains(featured2)) {

                        return new CheckResult(false,
                            "There is no featured playlists in correct format on \"featured\" action");
                    }
                    return CheckResult.TRUE;
                })),

            new TestCase<Attach>()
                .setInput("auth\nplaylists Party Time\nexit")
                .setAttach(new Attach(reply -> {

                    String playlist1 =
                        "Noite Eletrônica\n" +
                            "http://open.spotify.com/user/spotifybrazilian/playlist/4k7EZPI3uKMz4aRRrLVfen"
                                .replaceAll(spotifyServerUrl, resourceServerUrl);

                    String playlist2 =
                        "Festa Indie\n" +
                            "http://open.spotify.com/user/spotifybrazilian/playlist/4HZh0C9y80GzHDbHZyX770"
                                .replaceAll(spotifyServerUrl, resourceServerUrl);

                    if (!reply.contains(playlist1)
                        || !reply.contains(playlist2)) {

                        return new CheckResult(false,
                            "There is no playlists in correct format on \"playlists {name}\" action");
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
