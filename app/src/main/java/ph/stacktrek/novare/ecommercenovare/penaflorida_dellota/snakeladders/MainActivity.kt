package ph.stacktrek.novare.ecommercenovare.penaflorida_dellota.snakeladders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var board: Array<IntArray>
    private var boardSize = 10

    private var snakes = mapOf(
        17 to 7,
        54 to 34,
        62 to 19,
        64 to 60,
        87 to 24,
        93 to 73,
        95 to 75,
        99 to 78
    )

    private var ladders = mapOf(
        4 to 14,
        9 to 31,
        20 to 38,
        28 to 84,
        40 to 59,
        51 to 67,
        63 to 81,
        71 to 91
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = Array(boardSize) { IntArray(boardSize) }

        generateBoard()
    }

    private fun generateBoard() {
        var row = boardSize - 1
        var cellValue = 1
        for (i in 0 until boardSize) {
            for (j in 0 until boardSize) {
                board[row][if (row % 2 == 0) j else boardSize - j - 1] = cellValue
                cellValue++
            }
            row--
        }

        val tableLayout = findViewById<TableLayout>(R.id.tableLayout)
        for (i in 0 until boardSize) {
            val tableRow = TableRow(this)
            tableRow.layoutParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT,
                1.0f
            )
            tableLayout.addView(tableRow)
            for (j in 0 until boardSize) {
                val textView = TextView(this)
                textView.text = board[i][j].toString()
                textView.setBackgroundResource(R.drawable.cell_bg)
                textView.layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.MATCH_PARENT,
                    1.0f
                )

                // Check if the current cell has a snake or ladder, and update the text accordingly
                val cellValue = board[i][j]
                val snakeTail = snakes[cellValue]
                val ladderTop = ladders[cellValue]
                if (snakeTail != null) {
                    textView.text = "$cellValue [S$snakeTail]"
                } else if (ladderTop != null) {
                    textView.text = "$cellValue [L$ladderTop]"
                }

                tableRow.addView(textView)
            }
        }
    }
}
