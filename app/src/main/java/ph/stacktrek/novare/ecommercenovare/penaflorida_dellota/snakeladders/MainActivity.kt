package ph.stacktrek.novare.ecommercenovare.penaflorida_dellota.snakeladders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var board: Array<IntArray>
    private var boardSize = 10

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
                tableRow.addView(textView)
            }
        }
    }
}