package features
import cucumber.api.groovy.EN
import cucumber.api.groovy.Hooks

this.metaClass.mixin(Hooks)
this.metaClass.mixin(EN)

def email = "p.p@gmail.com"
def text = "Ble bla, bla bla!"


When(~/^I create a new comment$/) { ->
    appClient.createComment(email, text)
}

Then(~/^I should see it in the comments list$/) { ->
    assert appClient.iSee(email, text)
}

After("@DeleteComments") {
    backendClient.deleteCommentsBy(email, text)
}