package com.sampson.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sampson.memorygame.models.BoardSize
import com.sampson.memorygame.models.MemoryCard
import com.sampson.memorygame.models.MemoryGame
import com.sampson.memorygame.utils.DEFAULT_ICONS

class MainActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "MainActivity"
    }

    private lateinit var recyclerBoard : RecyclerView
    private lateinit var txtMoves : TextView
    private lateinit var txtPairs : TextView

    private var boardSize : BoardSize = BoardSize.EASY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerBoard = findViewById(R.id.recyclerBoard)
        txtMoves = findViewById(R.id.txtMoves)
        txtPairs = findViewById(R.id.txtPairs)

        val memoryGame = MemoryGame(boardSize)
        recyclerBoard.adapter = MemoryBoardAdapter(this,boardSize, memoryGame.cards, object : MemoryBoardAdapter.CardClickListener{
            override fun onCardClicked(position: Int) {
                Log.i(TAG, "Card Clicked $position")
            }

        })


        recyclerBoard.setHasFixedSize(true)
        recyclerBoard.layoutManager = GridLayoutManager(this, boardSize.getWidth())

    }
}