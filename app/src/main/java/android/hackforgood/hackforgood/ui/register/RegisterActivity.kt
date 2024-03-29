package android.hackforgood.hackforgood.ui.register

import android.hackforgood.hackforgood.R
import android.hackforgood.hackforgood.common.PermissionsManager
import android.hackforgood.hackforgood.data.register.RegisterModel
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), Register_MVP.View {

    private lateinit var presenter: Register_MVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setupMVP()

        registerButton.setOnClickListener {
            registerProgressBar.visibility = View.VISIBLE
            if (!PermissionsManager.checkPermissions(this))
                PermissionsManager.getPermissions(this)
            else {
                val name = nameEditText.text.toString()
                val lastName = lastNameEditText.text.toString()
                val age = ageEditText.text.toString()
                val sex = sexSpinner.selectedItem.toString()
                val username = usernameEditText.text.toString()
                val password = passwordEditText.text.toString()
                val phone = numberPhoneEditText.text.toString()
                presenter.registerButtonClicked(name, lastName, age, sex, username, password, phone)
            }
        }
    }

    override fun showErrors(errors: List<String>) {
        registerProgressBar.visibility = View.GONE

        errorTextView.text = errors[0]
    }

    private fun setupMVP() {
        presenter = RegisterPresenter(this, RegisterModel(this))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (PermissionsManager.checkPermissions(this)) {
            val name = nameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val age = ageEditText.text.toString()
            val sex = sexSpinner.selectedItem.toString()
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val phone = numberPhoneEditText.text.toString()
            presenter.registerButtonClicked(name, lastName, age, sex, username, password, phone)
        }
    }

}
