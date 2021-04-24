package com.sampson.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerBoard : RecyclerView
    private lateinit var txtMoves : TextView
    private lateinit var txtPairs : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerBoard = findViewById(R.id.recyclerBoard)
        txtMoves = findViewById(R.id.txtMoves)
        txtPairs = findViewById(R.id.txtPairs)

        recyclerBoard.adapter = MemoryBoardAdapter(this,8)
        recyclerBoard.setHasFixedSize(true)
        recyclerBoard.layoutManager = GridLayoutManager(this, 2)

    }
}