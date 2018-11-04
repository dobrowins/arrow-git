package com.dobrowins.arrowktplayground.repository

import com.google.gson.annotations.SerializedName

/**
 * @author Artem Dobrovinskiy
 */

data class ReposResponse(
    val repos: List<RepositoryDataResponse?>
)

data class RepositoryDataResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("node_id")
    val node_id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("full_name")
    val full_name: String?,
    @SerializedName("private")
    val private: Boolean?,
    @SerializedName("owner")
    val owner: Owner?,
    @SerializedName("html_url") //: "https://github.com/dobrowins/airbits-test",
    val html_url: String?,
    @SerializedName("description") // null,
    val description: String?,
    @SerializedName("fork") // false,
    val fork: Boolean?,
    @SerializedName("url") // "https://api.github.com/repos/dobrowins/airbits-test",
    val url: String?,
    @SerializedName("forks_url") // "https://api.github.com/repos/dobrowins/airbits-test/forks",
    val forks_url: String?,
    @SerializedName("keys_url") // "https://api.github.com/repos/dobrowins/airbits-test/keys{/key_id}",
    val keys_url: String?,
    @SerializedName("collaborators_url") // "https://api.github.com/repos/dobrowins/airbits-test/collaborators{/collaborator}",
    val collaborators_url: String?,
    @SerializedName("teams_url") // "https://api.github.com/repos/dobrowins/airbits-test/teams",
    val teams_url: String?,
    @SerializedName("hooks_url") // "https://api.github.com/repos/dobrowins/airbits-test/hooks",
    val hooks_url: String?,
    @SerializedName("issue_events_url") // "https://api.github.com/repos/dobrowins/airbits-test/issues/events{/number}",
    val issue_events_url: String?,
    @SerializedName("events_url") // "https://api.github.com/repos/dobrowins/airbits-test/events",
    val events_url: String?,
    @SerializedName("assignees_url") // "https://api.github.com/repos/dobrowins/airbits-test/assignees{/user}",
    val assignees_url: String?,
    @SerializedName("branches_url") // "https://api.github.com/repos/dobrowins/airbits-test/branches{/branch}",
    val branches_url: String?,
    @SerializedName("tags_url") // "https://api.github.com/repos/dobrowins/airbits-test/tags",
    val tags_url: String?,
    @SerializedName("blobs_url") // "https://api.github.com/repos/dobrowins/airbits-test/git/blobs{/sha}",
    val blobs_url: String?,
    @SerializedName("git_tags_url") //: "https://api.github.com/repos/dobrowins/airbits-test/git/tags{/sha}",
    val git_tags_url: String?,
    @SerializedName("git_refs_url") //"https://api.github.com/repos/dobrowins/airbits-test/git/refs{/sha}",
    val git_refs_url: String?,
    @SerializedName("trees_url") //"https://api.github.com/repos/dobrowins/airbits-test/git/trees{/sha}",
    val trees_url: String?,
    @SerializedName("statuses_url") //"https://api.github.com/repos/dobrowins/airbits-test/statuses/{sha}",
    val statuses_url: String?,
    @SerializedName("languages_url") //"https://api.github.com/repos/dobrowins/airbits-test/languages",
    val languages_url: String?,
    @SerializedName("stargazers_url") //"https://api.github.com/repos/dobrowins/airbits-test/stargazers",
    val stargazers_url: String?,
    @SerializedName("contributors_url") //"https://api.github.com/repos/dobrowins/airbits-test/contributors",
    val contributors_url: String?,
    @SerializedName("subscribers_url") //"https://api.github.com/repos/dobrowins/airbits-test/subscribers",
    val subscribers_url: String?,
    @SerializedName("subscription_url") //"https://api.github.com/repos/dobrowins/airbits-test/subscription",
    val subscription_url: String?,
    @SerializedName("commits_url") //"https://api.github.com/repos/dobrowins/airbits-test/commits{/sha}",
    val commits_url: String?,
    @SerializedName("git_commits_url") //"https://api.github.com/repos/dobrowins/airbits-test/git/commits{/sha}",
    val git_commits_url: String?,
    @SerializedName("comments_url") //"https://api.github.com/repos/dobrowins/airbits-test/comments{/number}",
    val comments_url: String?,
    @SerializedName("issue_comment_url") //"https://api.github.com/repos/dobrowins/airbits-test/issues/comments{/number}",
    val issue_comment_url: String?,
    @SerializedName("contents_url") //"https://api.github.com/repos/dobrowins/airbits-test/contents/{+path}",
    val contents_url: String?,
    @SerializedName("compare_url") //"https://api.github.com/repos/dobrowins/airbits-test/compare/{base}...{head}",
    val compare_url: String?,
    @SerializedName("merges_url") //"https://api.github.com/repos/dobrowins/airbits-test/merges",
    val merges_url: String?,
    @SerializedName("archive_url") //"https://api.github.com/repos/dobrowins/airbits-test/{archive_format}{/ref}",
    val archive_url: String?,
    @SerializedName("downloads_url") //"https://api.github.com/repos/dobrowins/airbits-test/downloads",
    val downloads_url: String?,
    @SerializedName("issues_url") //"https://api.github.com/repos/dobrowins/airbits-test/issues{/number}",
    val issues_url: String?,
    @SerializedName("pulls_url") //"https://api.github.com/repos/dobrowins/airbits-test/pulls{/number}",
    val pulls_url: String?,
    @SerializedName("milestones_url") //"https://api.github.com/repos/dobrowins/airbits-test/milestones{/number}",
    val milestones_url: String?,
    @SerializedName("notifications_url") //"https://api.github.com/repos/dobrowins/airbits-test/notifications{?since,all,participating}",
    val notifications_url: String?,
    @SerializedName("labels_url") //"https://api.github.com/repos/dobrowins/airbits-test/labels{/name}",
    val labels_url: String?,
    @SerializedName("releases_url") //"https://api.github.com/repos/dobrowins/airbits-test/releases{/id}",
    val releases_url: String?,
    @SerializedName("deployments_url") //"https://api.github.com/repos/dobrowins/airbits-test/deployments",
    val deployments_url: String?,
    @SerializedName("created_at") //: "2017-06-04T17:58:18Z",
    val created_at: String?,
    @SerializedName("updated_at") //"2017-06-04T18:01:07Z",
    val updated_at: String?,
    @SerializedName("pushed_at") //"2017-06-06T16:00:41Z",
    val pushed_at: String?,
    @SerializedName("git_url") //"git://github.com/dobrowins/airbits-test.git",
    val git_url: String?,
    @SerializedName("ssh_url") //"git@github.com:dobrowins/airbits-test.git",
    val ssh_url: String?,
    @SerializedName("clone_url") //"https://github.com/dobrowins/airbits-test.git",
    val clone_url: String?,
    @SerializedName("svn_url") //"https://github.com/dobrowins/airbits-test",
    val svn_url: String?,
    @SerializedName("homepage") //null,
    val homepage: String?,
    @SerializedName("size") //: 84,
    val size: Int?,
    @SerializedName("stargazers_count") //0,
    val stargazers_count: Int?,
    @SerializedName("watchers_count") //0,
    val watchers_count: Int?,
    @SerializedName("language") //"Java",
    val language: String?,
    @SerializedName("has_issues") //: true,
    val has_issues: Boolean?,
    @SerializedName("has_projects") //true,
    val has_projects: Boolean?,
    @SerializedName("has_downloads") //true,
    val has_downloads: Boolean?,
    @SerializedName("has_wiki") //true,
    val has_wiki: Boolean?,
    @SerializedName("has_pages") //false,
    val has_pages: Boolean?,
    @SerializedName("forks_count") // 0,
    val forks_count: String?,
    @SerializedName("mirror_url")
    val mirror_url: String?,
    @SerializedName("archived") //false,
    val archived: Boolean?,
    @SerializedName("open_issues_count") //2,
    val open_issues_count: Int?,
    @SerializedName("license")
    val license: String?,
    @SerializedName("forks") //0,
    val forks: Int?,
    @SerializedName("open_issues") //2,
    val open_issues: Int?,
    @SerializedName("watchers") //0,
    val watchers: Int?,
    @SerializedName("default_branch")
    val default_branch: String?
)

data class Owner(
    @SerializedName("login") //"dobrowins",
    val login: String?,
    @SerializedName("id") // 18611797,
    val id: Int?,
    @SerializedName("node_id") //"MDQ6VXNlcjE4NjExNzk3",
    val node_id: String?,
    @SerializedName("avatar_url") //"https://avatars2.githubusercontent.com/u/18611797?v=4",
    val avatar_url: String?,
    @SerializedName("gravatar_id") //"",
    val gravatar_id: String?,
    @SerializedName("url") //"https://api.github.com/users/dobrowins",
    val url: String?,
    @SerializedName("html_url") //"https://github.com/dobrowins",
    val html_url: String?,
    @SerializedName("followers_url") //"https://api.github.com/users/dobrowins/followers",
    val followers_url: String?,
    @SerializedName("following_url") //"https://api.github.com/users/dobrowins/following{/other_user}",
    val following_url: String?,
    @SerializedName("gists_url") //"https://api.github.com/users/dobrowins/gists{/gist_id}",
    val gists_url: String?,
    @SerializedName("starred_url") //"https://api.github.com/users/dobrowins/starred{/owner}{/repo}",
    val starred_url: String?,
    @SerializedName("subscriptions_url") //"https://api.github.com/users/dobrowins/subscriptions",
    val subscriptions_url: String?,
    @SerializedName("organizations_url") //"https://api.github.com/users/dobrowins/orgs",
    val organizations_url: String?,
    @SerializedName("repos_url") //"https://api.github.com/users/dobrowins/repos",
    val repos_url: String?,
    @SerializedName("events_url") //"https://api.github.com/users/dobrowins/events{/privacy}",
    val events_url: String?,
    @SerializedName("received_events_url") //"https://api.github.com/users/dobrowins/received_events",
    val received_events_url: String?,
    @SerializedName("type") //"User",
    val type: String?,
    @SerializedName("site_admin") //false
    val site_admin: Boolean?
)