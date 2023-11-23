package balazadeh.nima.calculatorxiaomi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.Toast
import balazadeh.nima.calculatorxiaomi.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Error
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var operator: Char = '+'
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onNumberClicked()
        onOperatingClicked()

    }

    fun onNumberClicked() {
        binding.btn0.setOnClickListener {
            if (binding.txtamaliat.text.isNotEmpty()) {
                appendText("0")
            }
        }
        binding.btn1.setOnClickListener {
            appendText("1")
        }
        binding.btn2.setOnClickListener {
            appendText("2")
        }
        binding.btn3.setOnClickListener {
            appendText("3")
        }
        binding.btn4.setOnClickListener {
            appendText("4")
        }
        binding.btn5.setOnClickListener {
            appendText("5")
        }
        binding.btn6.setOnClickListener {
            appendText("6")
        }
        binding.btn7.setOnClickListener {
            appendText("7")
        }
        binding.btn8.setOnClickListener {
            appendText("8")
        }
        binding.btn9.setOnClickListener {
            appendText("9")
        }
        binding.btndot.setOnClickListener {
            if (binding.txtamaliat.text.isEmpty() || binding.txtjavab.text.isNotEmpty()) {
                appendText("0.")
            } else if (!binding.txtamaliat.text.contains(".")) {
                appendText(".")
            }

        }
    }

    fun onOperatingClicked() {
        binding.btnjam.setOnClickListener {
            if (binding.txtamaliat.text.isNotEmpty()) {
                val char = binding.txtamaliat.text.last()
                if (char != '+' && char != '-' && char != '*' && char != '/') {
                    appendText("+")
                }
            }
        }
        binding.btnmenha.setOnClickListener {
            if (binding.txtamaliat.text.isNotEmpty()) {
                val char = binding.txtamaliat.text.last()
                if (char != '+' && char != '-' && char != '*' && char != '/') {
                    appendText("-")
                }
            }
        }
        binding.btnzarb.setOnClickListener {
            if (binding.txtamaliat.text.isNotEmpty()) {
                val char = binding.txtamaliat.text.last()
                if (char != '+' && char != '-' && char != '*' && char != '/') {
                    appendText("*")
                }
            }
        }
        binding.btntaghsim.setOnClickListener {
            if (binding.txtamaliat.text.isNotEmpty()) {
                val char = binding.txtamaliat.text.last()
                if (char != '+' && char != '-' && char != '*' && char != '/') {
                    appendText("/")
                }
            }
        }
        binding.btnparantezbaz.setOnClickListener {
            appendText("(")
        }
        binding.btnparantezbaste.setOnClickListener {
            appendText(")")
        }
        binding.btnAC.setOnClickListener {
            binding.txtjavab.text = ""
            binding.txtamaliat.text = ""
        }
        binding.btnpakkardan.setOnClickListener {
            val txt = binding.txtamaliat.text.toString()
            if (txt.isNotEmpty()) {
                binding.txtamaliat.text = txt.substring(0, txt.length - 1)
            }
        }
        binding.btnmosavi.setOnClickListener {
            try {
                val expression = ExpressionBuilder(binding.txtamaliat.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    binding.txtjavab.text = longResult.toString()
                } else {
                    binding.txtjavab.text = result.toString()
                }
            } catch (e: Exception) {
                binding.txtamaliat.text = ""
                binding.txtjavab.text = ""
                Toast.makeText(this, "Are you dumb or what?", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun appendText(text: String) {
        if (binding.txtjavab.text.isNotEmpty()) {
            binding.txtamaliat.text = ""
            binding.txtjavab.text = ""
        }
        binding.txtamaliat.append(text)

        val viewTree: ViewTreeObserver = binding.horizontalScrollViewTextAmaliat.viewTreeObserver
        viewTree.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.horizontalScrollViewTextAmaliat.viewTreeObserver.removeOnGlobalLayoutListener(
                    this
                )
                binding.horizontalScrollViewTextAmaliat.smoothScrollTo(binding.txtamaliat.width, 0)
            }

        })
    }
}