package android.hackforgood.hackforgood.ui.login

import android.hackforgood.hackforgood.R
import android.hackforgood.hackforgood.data.login.LoginModel
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity(), Login_MVP.View {
    private lateinit var presenter: Login_MVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupMVP()

        siginButton.setOnClickListener {
            loginProgressBar.visibility = View.VISIBLE

            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            presenter.loginButtonClicked(username, password)
        }

        registerButton.setOnClickListener {
            presenter.registerButtonClicked()
        }
    }

    override fun showErrors(errors: List<String>) {
        loginProgressBar.visibility = View.GONE

        errorTextView.text = errors[0]
    }

    private fun setupMVP() {
        presenter = LoginPresenter(this, LoginModel(this))
    }
}
