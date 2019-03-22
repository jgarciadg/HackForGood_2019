package android.hackforgood.hackforgood.ui.see_profile

import android.app.Activity
import android.content.Intent
import android.hackforgood.hackforgood.R
import android.hackforgood.hackforgood.data.model.User
import android.hackforgood.hackforgood.data.see_profile.SeeProfileModel
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_see_profile.*


class SeeProfileActivity : AppCompatActivity(), SeeProfile_MVP.View {
    private lateinit var presenter: SeeProfile_MVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_profile)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
        supportActionBar?.title = null

        setupMVP()

        if (intent.extras == null) {
            presenter.getUserInfo()
        } else {
            //Get User info from extras
        }

        profile_image.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK)
            Log.d("TAG", data?.getData().toString())
    }


    override fun showUserInfo(user: User) {
        val urlImage = "http://qss.unex.es:2072/user/photo/${user.photo}"

        Picasso.get()
                .load(urlImage)
                .into(profile_image);
        nameTextView.text = "${user.firstName} ${user.lastName}"
        rating.rating = user.avgJudgment.toFloat()
        telephoneTextView.text = user.telephone
    }

    private fun setupMVP() {
        presenter = SeeProfilePresenter(this, SeeProfileModel(this))
    }
}
