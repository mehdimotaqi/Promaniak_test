package ma.test.promaniak.ui.list.adapter

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_row.view.*
import ma.negoapp.todolist.utils.inflate
import ma.test.promaniak.R
import ma.test.promaniak.data.entities.User
import ma.test.promaniak.ui.list.UserListFragmentDirections

class UserListAdapter: RecyclerView.Adapter<UserListAdapter.MyViewHolder>(){

    var dataList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(parent.inflate(R.layout.user_row))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.userName_tv.text = dataList[position].userName
        holder.itemView.userMail_tv.text = dataList[position].userMail
        holder.itemView.userPhone_tv.text = dataList[position].userPhone

        holder.itemView.row_background.setOnClickListener {
            val action = UserListFragmentDirections.actionUserListFragmentToEditUserFragment(dataList[position])
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(users: List<User>){
        dataList = users
        notifyDataSetChanged()
    }
}