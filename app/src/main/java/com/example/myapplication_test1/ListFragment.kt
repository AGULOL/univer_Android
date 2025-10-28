package com.example.myapplication_test1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListAdapter
    private lateinit var items: List<ItemData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        items = createListItems()

        adapter = ListAdapter(items)
        recyclerView.adapter = adapter

        return view
    }

    private fun createListItems(): List<ItemData> {
        return listOf(
            ItemData("Заголовок элемент №1", "Подзаголовок элемент №1", R.drawable.ic_cloud_upload),
            ItemData("Заголовок элемент №2", "Подзаголовок элемент №2", R.drawable.ic_bus),
            ItemData("Заголовок элемент №3", "Подзаголовок элемент №3", R.drawable.ic_bank),
            ItemData("Заголовок элемент №4", "Подзаголовок элемент №4", R.drawable.ic_cloud_upload),
            ItemData("Заголовок элемент №5", "Подзаголовок элемент №5", R.drawable.ic_bank),
            ItemData("Заголовок элемент №6", "Подзаголовок элемент №6", R.drawable.ic_bus),
            ItemData("Заголовок элемент №7", "Подзаголовок элемент №7", R.drawable.ic_cloud_upload),
            ItemData("Заголовок элемент №8", "Подзаголовок элемент №8", R.drawable.ic_bank),
            ItemData("Заголовок элемент №9", "Подзаголовок элемент №9", R.drawable.ic_bus),
            ItemData("Заголовок элемент №1", "Подзаголовок элемент №1", R.drawable.ic_cloud_upload),
            ItemData("Заголовок элемент №2", "Подзаголовок элемент №2", R.drawable.ic_bus),
            ItemData("Заголовок элемент №3", "Подзаголовок элемент №3", R.drawable.ic_bank)
            )
    }
}
