package com.sampson.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sampson.memorygame.models.BoardSize
import com.sampson.memorygame.models.MemoryCard
import com.sampson.memorygame.models.MemoryGame
import com.sampson.memorygame.utils.DEFAULT_ICONS
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var clRoot: ConstraintLayout
    private lateinit var recyclerBoard: RecyclerView
    private lateinit var txtMoves: TextView
    private lateinit var txtPairs: TextView

    private lateinit var memoryGame: MemoryGame
    private lateinit var adapter: MemoryBoardAdapter
    private var boardSize: BoardSize = BoardSize.EASY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clRoot = findViewById(R.id.clRoot)
        recyclerBoard = findViewById(R.id.recyclerBoard)
        txtMoves = findViewById(R.id.txtMoves)
        txtPairs = findViewById(R.id.txtPairs)

        memoryGame = MemoryGame(boardSize)

        adapter = MemoryBoardAdapter(
            this,
            boardSize,
            memoryGame.cards,
            object : MemoryBoardAdapter.CardClickListener {
                override fun onCardClicked(position: Int) {
                    updateGameWithFlip(position)
                }

            })

        recyclerBoard.adapter = adapter
        recyclerBoard.setHasFixedSize(true)
        recyclerBoard.layoutManager = GridLayoutManager(this, boardSize.getWidth())

    }

    private fun updateGameWithFlip(position: Int) {
        if (memoryGame.haveWonGame()) {
            Snackbar.make(clRoot, "You Already won!", Snackbar.LENGTH_LONG).show()
            return
        }
        if (memoryGame.isCardFaceUp(position)) {
            Snackbar.make(clRoot, "Invalid movement!", Snackbar.LENGTH_LONG).show()
            return
        }
        if (memoryGame.flipCard(position)){
            Log.i(TAG,"Found a match! Num Pairs found: ${memoryGame.numPairsFound}")
        }
        adapter.notifyDataSetChanged()

    }
}