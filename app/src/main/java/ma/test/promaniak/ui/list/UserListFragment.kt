package ma.test.promaniak.ui.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_user_list.*
import kotlinx.android.synthetic.main.fragment_user_list.view.*
import ma.test.promaniak.MainActivity
import ma.test.promaniak.R
import ma.test.promaniak.data.viewmodel.UserViewModel
import ma.test.promaniak.ui.DataLoadingState
import ma.test.promaniak.ui.list.adapter.UserListAdapter

class UserListFragment : Fragment() {

    private val userViewModel: UserViewModel by viewModels()
    private val userListAdapter: UserListAdapter by lazy { UserListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)
        initializeRecyclerView(view)
        initializeObservers()
        actionToAddNewUser(view)
        return view
    }

    private fun initializeObservers() {

        userViewModel.dataLoadingState.observe(viewLifecycleOwner, Observer {
            updateUi(it)
            Log.d("CHECKDATA", "dataLoadingState: ${it.toString()}")
        })

        userViewModel.getAllUsers().observe(viewLifecycleOwner, Observer {
            userListAdapter.setData(it)
            Log.d("CHECKDATA", "initializeObservers: ${it.size}")
        })
    }

    private fun updateUi(state: DataLoadingState?) {
        when (state) {
            DataLoadingState.LOADING -> {
                progressBar.visibility = View.VISIBLE
                recyclerView.visibility = View.INVISIBLE
            }
            DataLoadingState.LOADED -> {
                progressBar.visibility = View.INVISIBLE
                recyclerView.visibility = View.VISIBLE
            }
            DataLoadingState.ERROR -> {
                progressBar.visibility = View.INVISIBLE
                recyclerView.visibility = View.INVISIBLE
            }
        }
    }

    private fun initializeRecyclerView(view: View) {
        val recyclerView = view.recyclerView
        recyclerView.apply {
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            hasFixedSize()
        }
    }

    private fun actionToAddNewUser(view: View) {
        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_userListFragment_to_addUserFragment)
        }
    }
}