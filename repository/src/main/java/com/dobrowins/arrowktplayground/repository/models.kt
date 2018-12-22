package com.dobrowins.arrowktplayground.repository

import com.google.gson.annotations.SerializedName

data class ReposResponse(
    val repos: List<RepositoryDataResponse?>
)

data class RepositoryDataResponse(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("node_id")
    val node_id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("full_name")
    val full_name: String? = null,
    @SerializedName("private")
    val private: Boolean? = null,
    @SerializedName("owner")
    val owner: Owner? = null,
    @SerializedName("html_url") //: "https://github.com/dobrowins/airbits-test",
    val html_url: String? = null,
    @SerializedName("description") // null,
    val description: String? = null,
    @SerializedName("fork") // false,
    val fork: Boolean? = null,
    @SerializedName("url") // "https://api.github.com/repos/dobrowins/airbits-test",
    val url: String? = null,
    @SerializedName("forks_url") // "https://api.github.com/repos/dobrowins/airbits-test/forks",
    val forks_url: String? = null,
    @SerializedName("keys_url") // "https://api.github.com/repos/dobrowins/airbits-test/keys{/key_id}",
    val keys_url: String? = null,
    @SerializedName("collaborators_url") // "https://api.github.com/repos/dobrowins/airbits-test/collaborators{/collaborator}",
    val collaborators_url: String? = null,
    @SerializedName("teams_url") // "https://api.github.com/repos/dobrowins/airbits-test/teams",
    val teams_url: String? = null,
    @SerializedName("hooks_url") // "https://api.github.com/repos/dobrowins/airbits-test/hooks",
    val hooks_url: String? = null,
    @SerializedName("issue_events_url") // "https://api.github.com/repos/dobrowins/airbits-test/issues/events{/number}",
    val issue_events_url: String? = null,
    @SerializedName("events_url") // "https://api.github.com/repos/dobrowins/airbits-test/events",
    val events_url: String? = null,
    @SerializedName("assignees_url") // "https://api.github.com/repos/dobrowins/airbits-test/assignees{/user}",
    val assignees_url: String? = null,
    @SerializedName("branches_url") // "https://api.github.com/repos/dobrowins/airbits-test/branches{/branch}",
    val branches_url: String? = null,
    @SerializedName("tags_url") // "https://api.github.com/repos/dobrowins/airbits-test/tags",
    val tags_url: String? = null,
    @SerializedName("blobs_url") // "https://api.github.com/repos/dobrowins/airbits-test/git/blobs{/sha}",
    val blobs_url: String? = null,
    @SerializedName("git_tags_url") //: "https://api.github.com/repos/dobrowins/airbits-test/git/tags{/sha}",
    val git_tags_url: String? = null,
    @SerializedName("git_refs_url") //"https://api.github.com/repos/dobrowins/airbits-test/git/refs{/sha}",
    val git_refs_url: String? = null,
    @SerializedName("trees_url") //"https://api.github.com/repos/dobrowins/airbits-test/git/trees{/sha}",
    val trees_url: String? = null,
    @SerializedName("statuses_url") //"https://api.github.com/repos/dobrowins/airbits-test/statuses/{sha}",
    val statuses_url: String? = null,
    @SerializedName("languages_url") //"https://api.github.com/repos/dobrowins/airbits-test/languages",
    val languages_url: String? = null,
    @SerializedName("stargazers_url") //"https://api.github.com/repos/dobrowins/airbits-test/stargazers",
    val stargazers_url: String? = null,
    @SerializedName("contributors_url") //"https://api.github.com/repos/dobrowins/airbits-test/contributors",
    val contributors_url: String? = null,
    @SerializedName("subscribers_url") //"https://api.github.com/repos/dobrowins/airbits-test/subscribers",
    val subscribers_url: String? = null,
    @SerializedName("subscription_url") //"https://api.github.com/repos/dobrowins/airbits-test/subscription",
    val subscription_url: String? = null,
    @SerializedName("commits_url") //"https://api.github.com/repos/dobrowins/airbits-test/commits{/sha}",
    val commits_url: String? = null,
    @SerializedName("git_commits_url") //"https://api.github.com/repos/dobrowins/airbits-test/git/commits{/sha}",
    val git_commits_url: String? = null,
    @SerializedName("comments_url") //"https://api.github.com/repos/dobrowins/airbits-test/comments{/number}",
    val comments_url: String? = null,
    @SerializedName("issue_comment_url") //"https://api.github.com/repos/dobrowins/airbits-test/issues/comments{/number}",
    val issue_comment_url: String? = null,
    @SerializedName("contents_url") //"https://api.github.com/repos/dobrowins/airbits-test/contents/{+path}",
    val contents_url: String? = null,
    @SerializedName("compare_url") //"https://api.github.com/repos/dobrowins/airbits-test/compare/{base}...{head}",
    val compare_url: String? = null,
    @SerializedName("merges_url") //"https://api.github.com/repos/dobrowins/airbits-test/merges",
    val merges_url: String? = null,
    @SerializedName("archive_url") //"https://api.github.com/repos/dobrowins/airbits-test/{archive_format}{/ref}",
    val archive_url: String? = null,
    @SerializedName("downloads_url") //"https://api.github.com/repos/dobrowins/airbits-test/downloads",
    val downloads_url: String? = null,
    @SerializedName("issues_url") //"https://api.github.com/repos/dobrowins/airbits-test/issues{/number}",
    val issues_url: String? = null,
    @SerializedName("pulls_url") //"https://api.github.com/repos/dobrowins/airbits-test/pulls{/number}",
    val pulls_url: String? = null,
    @SerializedName("milestones_url") //"https://api.github.com/repos/dobrowins/airbits-test/milestones{/number}",
    val milestones_url: String? = null,
    @SerializedName("notifications_url") //"https://api.github.com/repos/dobrowins/airbits-test/notifications{?since,all,participating}",
    val notifications_url: String? = null,
    @SerializedName("labels_url") //"https://api.github.com/repos/dobrowins/airbits-test/labels{/name}",
    val labels_url: String? = null,
    @SerializedName("releases_url") //"https://api.github.com/repos/dobrowins/airbits-test/releases{/id}",
    val releases_url: String? = null,
    @SerializedName("deployments_url") //"https://api.github.com/repos/dobrowins/airbits-test/deployments",
    val deployments_url: String? = null,
    @SerializedName("created_at") //: "2017-06-04T17:58:18Z",
    val created_at: String? = null,
    @SerializedName("updated_at") //"2017-06-04T18:01:07Z",
    val updated_at: String? = null,
    @SerializedName("pushed_at") //"2017-06-06T16:00:41Z",
    val pushed_at: String? = null,
    @SerializedName("git_url") //"git://github.com/dobrowins/airbits-test.git",
    val git_url: String? = null,
    @SerializedName("ssh_url") //"git@github.com:dobrowins/airbits-test.git",
    val ssh_url: String? = null,
    @SerializedName("clone_url") //"https://github.com/dobrowins/airbits-test.git",
    val clone_url: String? = null,
    @SerializedName("svn_url") //"https://github.com/dobrowins/airbits-test",
    val svn_url: String? = null,
    @SerializedName("homepage") //null,
    val homepage: String? = null,
    @SerializedName("size") //: 84,
    val size: Int? = null,
    @SerializedName("stargazers_count") //0,
    val stargazers_count: Int? = null,
    @SerializedName("watchers_count") //0,
    val watchers_count: Int? = null,
    @SerializedName("language") //"Java",
    val language: String? = null,
    @SerializedName("has_issues") //: true,
    val has_issues: Boolean? = null,
    @SerializedName("has_projects") //true,
    val has_projects: Boolean? = null,
    @SerializedName("has_downloads") //true,
    val has_downloads: Boolean? = null,
    @SerializedName("has_wiki") //true,
    val has_wiki: Boolean? = null,
    @SerializedName("has_pages") //false,
    val has_pages: Boolean? = null,
    @SerializedName("forks_count") // 0,
    val forks_count: String? = null,
    @SerializedName("mirror_url")
    val mirror_url: String? = null,
    @SerializedName("archived") //false,
    val archived: Boolean? = null,
    @SerializedName("open_issues_count") //2,
    val open_issues_count: Int? = null,
    @SerializedName("license")
    val license: String? = null,
    @SerializedName("forks") //0,
    val forks: Int? = null,
    @SerializedName("open_issues") //2,
    val open_issues: Int? = null,
    @SerializedName("watchers") //0,
    val watchers: Int? = null,
    @SerializedName("default_branch")
    val default_branch: String? = null
)

data class Owner(
    @SerializedName("login") //"dobrowins",
    val login: String? = null,
    @SerializedName("id") // 18611797,
    val id: Int? = null,
    @SerializedName("node_id") //"MDQ6VXNlcjE4NjExNzk3",
    val node_id: String? = null,
    @SerializedName("avatar_url") //"https://avatars2.githubusercontent.com/u/18611797?v=4",
    val avatar_url: String? = null,
    @SerializedName("gravatar_id") //"",
    val gravatar_id: String? = null,
    @SerializedName("url") //"https://api.github.com/users/dobrowins",
    val url: String? = null,
    @SerializedName("html_url") //"https://github.com/dobrowins",
    val html_url: String? = null,
    @SerializedName("followers_url") //"https://api.github.com/users/dobrowins/followers",
    val followers_url: String? = null,
    @SerializedName("following_url") //"https://api.github.com/users/dobrowins/following{/other_user}",
    val following_url: String? = null,
    @SerializedName("gists_url") //"https://api.github.com/users/dobrowins/gists{/gist_id}",
    val gists_url: String? = null,
    @SerializedName("starred_url") //"https://api.github.com/users/dobrowins/starred{/owner}{/repo}",
    val starred_url: String? = null,
    @SerializedName("subscriptions_url") //"https://api.github.com/users/dobrowins/subscriptions",
    val subscriptions_url: String? = null,
    @SerializedName("organizations_url") //"https://api.github.com/users/dobrowins/orgs",
    val organizations_url: String? = null,
    @SerializedName("repos_url") //"https://api.github.com/users/dobrowins/repos",
    val repos_url: String? = null,
    @SerializedName("events_url") //"https://api.github.com/users/dobrowins/events{/privacy}",
    val events_url: String? = null,
    @SerializedName("received_events_url") //"https://api.github.com/users/dobrowins/received_events",
    val received_events_url: String? = null,
    @SerializedName("type") //"User",
    val type: String? = null,
    @SerializedName("site_admin") //false
    val site_admin: Boolean? = null
)