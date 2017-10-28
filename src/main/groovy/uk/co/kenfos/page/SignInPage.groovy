package uk.co.kenfos.page

import geb.Page

class SignInPage extends Page {

    static url = "https://egghead.io/users/sign_in"

    static content = {
        userEmail(required: false)     { $('#user_email') }
        userPassword(required: false)  { $('#user_password') }
        submitButton(required: false)  { $("input[type='submit']") }
        signOutButton(required: false) { $("a[data-method='delete']") }
    }

    void login(email, password) {
        userEmail.value(email)
        userPassword.value(password)
        submitButton.click()
        waitFor { signOutButton.present }
    }
}
