package ma.test.promaniak.ui.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_add_user.view.*
import ma.negoapp.todolist.utils.verifyDataFromUser
import ma.test.promaniak.R
import ma.test.promaniak.data.entities.User
import ma.test.promaniak.data.viewmodel.UserViewModel


class AddUserFragment : Fragment() {

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_user, container, false)
        view.add_btn.setOnClickListener {
            val validation = verifyDataFromUser(
                view.userName_add_ed.text.toString(),
                view.userMail_add_ed.text.toString(),
                view.userPhone_add_ed.text.toString()
            )
            if (validation){
                userViewModel.addNewUser(User(
                    "",
                    view.userName_add_ed.text.toString(),
                    view.userMail_add_ed.text.toString(),
                    view.userPhone_add_ed.text.toString(),
                    null
                ))
            }
        }
        return view
    }

}