package features.support

class MyWorld {

    def domain = 'http://localhost:8888'

    def browser = new SeleniumWebDriver()

    AppClient appClient = new AppClient(browser)

    BackendClient backendClient = new BackendClient(domain)
}
