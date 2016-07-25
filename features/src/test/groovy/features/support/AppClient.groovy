package features.support

class AppClient {


    def selenium

    AppClient(selenium) {
        this.selenium = selenium
    }

    def createComment(email, text) {
        selenium.findElementById('input-email').sendKeys(email)
        selenium.findElementById('input-text').sendKeys(text)
        selenium.findElementById('input-text').submit()
        sleep(2000)
    }

    def iSee(String... texts) {
        def pageSource = selenium.getPageSource()
        texts.each {
            if(!pageSource.contains(it)) {
                return false
            }
        }
        true
    }

}
