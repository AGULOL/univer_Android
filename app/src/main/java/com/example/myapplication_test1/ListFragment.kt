package com.example.myapplication_test1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListAdapter
    private var items: List<ItemData> = emptyList()
    private val apiService = RickAndMortyService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = ListAdapter(items)
        recyclerView.adapter = adapter

        // Загрузка данных из API
        loadDataFromApi()

        return view
    }

    private fun loadDataFromApi() {
        lifecycleScope.launch {
            try {
                // Загружаем  персонажей
                val characters = apiService.getCharacters(listOf(1, 2, 3, 4, 5,27))
                // Загружаем  локации
                val locations = apiService.getLocations(listOf(1, 2, 3, 4, 10))

                // Преобразуем в ItemData
                val characterItems = characters.map { character ->
                    ItemData(
                        title = character.name,
                        subtitle = "${character.species} - ${character.status}",
                        imageUrl = character.image,
                        type = "character"

                    )
                }

                val locationItems = locations.map { location ->
                    ItemData(
                        title = location.name,
                        subtitle = "${location.type} - ${location.dimension}",
                        imageRes = R.drawable.ic_bank, // используем иконку для локаций
                        type = "location"
                    )
                }

                // Объединяем списки
//                items = characterItems + locationItems
                items = (characterItems + locationItems).shuffled()

                adapter = ListAdapter(items)
                recyclerView.adapter = adapter

            } catch (e: Exception) {
                e.printStackTrace()
                // В случае ошибки показываем локальные данные
                items = createListItems()
                adapter = ListAdapter(items)
                recyclerView.adapter = adapter
            }
        }
    }

    private fun createListItems(): List<ItemData> {
        return listOf(
            ItemData(
                title = "Ошибка загрузки",
                subtitle = "Проверьте подключение",
                imageRes = R.drawable.ic_cloud_upload,
                type = "error"
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        apiService.close()
    }
}