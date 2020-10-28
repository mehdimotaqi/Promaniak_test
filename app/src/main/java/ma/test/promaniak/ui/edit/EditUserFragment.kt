package ma.test.promaniak.ui.edit

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_add_user.view.*
import kotlinx.android.synthetic.main.fragment_edit_user.view.*
import kotlinx.android.synthetic.main.fragment_edit_user.view.userMail_add_ed
import kotlinx.android.synthetic.main.fragment_edit_user.view.userName_add_ed
import kotlinx.android.synthetic.main.fragment_edit_user.view.userPhone_add_ed
import ma.negoapp.todolist.utils.verifyDataFromUser
import ma.test.promaniak.R
import ma.test.promaniak.data.entities.User
import ma.test.promaniak.data.viewmodel.UserViewModel


class EditUserFragment : Fragment() {

    private val args by navArgs<EditUserFragmentArgs>()
    private val userViewModel: UserViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_user, container, false)
        view.userName_add_ed.setText(args.currentItem.userName)
        view.userMail_add_ed.setText(args.currentItem.userMail)
        view.userPhone_add_ed.setText(args.currentItem.userPhone)

        view.edit_btn.setOnClickListener {
            val validation = verifyDataFromUser(
                view.userName_add_ed.text.toString(),
                view.userMail_add_ed.text.toString(),
               view.userPhone_add_ed.text.toString())

            if (validation){
                userViewModel.updateUser(args.currentItem.userId,
                User(args.currentItem.userId,
                    view.userName_add_ed.text.toString(),
                    view.userMail_add_ed.text.toString(),
                    view.userPhone_add_ed.text.toString(),
                    null
                )
                )
                findNavController().navigate(R.id.action_editUserFragment_to_userListFragment)
            }
        }

        view.delete_btn.setOnClickListener {
            AlertDialog.Builder(requireActivity())
                .setTitle(getString(R.string.confirmation_label))
                .setMessage(getString(R.string.confirmation))
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(getString(R.string.yes),
                    DialogInterface.OnClickListener { dialog, whichButton ->
                        userViewModel.deleteUser(args.currentItem.userId)
                        findNavController().navigate(R.id.action_editUserFragment_to_userListFragment)
                    })
                .setNegativeButton(getString(R.string.no), null).show()
        }
        return view
    }


}