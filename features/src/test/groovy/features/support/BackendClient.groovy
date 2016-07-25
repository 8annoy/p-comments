package features.support

import groovyx.net.http.RESTClient

class BackendClient {


    RESTClient restClient

    BackendClient(String domain) {
        this.restClient = new RESTClient(domain)
    }

    def deleteCommentsBy(String email, String content) {
        restClient.delete(
                path: "/api/v1/comments",
                query: [email: email, content: content]
        )
    }
}
