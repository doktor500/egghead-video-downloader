package uk.co.kenfos

import geb.Browser
import uk.co.kenfos.page.CoursePage
import uk.co.kenfos.page.SignInPage

import static java.util.Arrays.asList

class Application {

    static void main(String [] args) {
        def (userEmail, userPassword, course) = asList(args)
        def browser = createBrowser()
        println("Starting...")
        browser.drive {
            to SignInPage
            login(userEmail, userPassword)
            to CoursePage
            go(course)
            downloadVideos(course)
        }
        println("Done")
        browser.driver.quit()
    }

    private static Browser createBrowser() {
        System.setProperty('geb.env', 'defaultEnvironment')
        new Browser()
    }

}
