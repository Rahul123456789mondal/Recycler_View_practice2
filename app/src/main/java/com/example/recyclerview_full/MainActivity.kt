package com.example.recyclerview_full

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var animals: ArrayList<String> = ArrayList()
    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)

        animals.add("cat")
        animals.add("owl")
        animals.add("cheetah")
        animals.add("raccoon")
        animals.add("bird")
        animals.add("snake")
        animals.add("lizard")
        animals.add("hamster")
        animals.add("bear")
        animals.add("lion")
        animals.add("tiger")
        animals.add("horse")
        animals.add("frog")
        animals.add("fish")
        animals.add("shark")
        animals.add("turtle")
        animals.add("elephant")
        animals.add("cow")
        animals.add("beaver")
        animals.add("bison")
        animals.add("porcupine")
        animals.add("rat")
        animals.add("mouse")
        animals.add("goose")
        animals.add("deer")
        animals.add("fox")
        animals.add("moose")
        animals.add("buffalo")
        animals.add("monkey")
        animals.add("penguin")
        animals.add("parrot")

        // To show the devider into the recyclerView this line is used
        val dividerItemDecoration: DividerItemDecoration =
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerAdapter = RecyclerAdapter(animals)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerAdapter(animals)

        // This line add the itemtouch helper into the recyclerView
        val itemTouchHelper: ItemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    lateinit var deleteData: String

    // Hare i implement the swipe delete futures
    val itemTouchCallback: ItemTouchHelper.SimpleCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                viewHolder1: RecyclerView.ViewHolder
            ): Boolean {
                //2 Return false in onMove. You don’t want to perform any special behavior here
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                //3 Call onSwiped when you swipe an item in the direction specified in the ItemTouchHelper. Here, you request the viewHolder parameter passed for the position of the item view, and then you remove that item from your list of photos. Finally, you inform the RecyclerView adapter that an item has been removed at a specific position.
                // This line holde the position on adapter
                val position: Int = viewHolder.adapterPosition

                when (swipeDir) {
                    ItemTouchHelper.LEFT -> {
                        deleteData = animals.get(position)
                        animals.removeAt(position)
                        recyclerView.adapter?.notifyItemRemoved(position)
                        // in this stape we implement the undo button in to the recyclerView/ This snackbar implement from meterial
                        Snackbar.make(recyclerView, deleteData, Snackbar.LENGTH_LONG)
                            .setAction("undo", View.OnClickListener {
                                animals.add(position, deleteData)
                                recyclerView.adapter?.notifyItemChanged(position)
                            }).show()
                    }

                    ItemTouchHelper.RIGHT -> {
                        // For the right swipe we would  flow this stape
                        // step1 - make a list in out side the function
                        // step2 - val data : String  =  animals.get(position)
                        // step3 - this data into the created list
                        // step4 - val archiveData : List<String> = ArrayList()
                        // step5 - archiveData.add(data)
                        /*
                        * animals.removeAt(position)
                        recyclerView.adapter?.notifyItemRemoved(position)

                        // in this stape we implement the undo button in to the recyclerView/ This snackbar implement from meterial

                        Snackbar.make(recyclerView, deleteData, Snackbar.LENGTH_LONG).setAction("Archived", View.OnClickListener {
                            archiveData.removed(archiveData.lastIndexof(data)
                            animals.add(position, data)
                            recyclerView.adapter?.notifyItemChanged(position)
                        }).show()

                        * */

                    }
                }
            }

        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)
        val item: MenuItem = menu.findItem(R.id.search)
        val searchView: SearchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recyclerAdapter.filter.filter(newText)
                return false
            }

        })


        return true
    }


}



















































