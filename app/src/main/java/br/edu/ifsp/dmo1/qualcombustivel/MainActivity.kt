package br.edu.ifsp.dmo1.qualcombustivel

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var gasolineEditText: EditText
    private lateinit var ethanolEditText: EditText
    private lateinit var actionButton: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findById()
        setClick()
    }


    private  fun calculate(){
        if(gasolineEditText.text.toString().isEmpty() || ethanolEditText.text.toString().isEmpty()){
            Toast.makeText(

                this,
                getString(R.string.error_message),
                Toast.LENGTH_SHORT
            ).show()
            textView.text= ""
        }else{

            val gasoline = retrieValue(gasolineEditText)
            val ethanol = retrieValue(ethanolEditText)

            val result = ethanol/gasoline

            if(result < 0.7){
                textView.text = getString(R.string.ethanol_result)
                textView.setTextColor(resources.getColor(R.color.alcool_color,this.theme))
            }else{
                textView.text = getString(R.string.gasoline_result)
                textView.setTextColor(resources.getColor(R.color.gasoline_color,this.theme))
            }
        }

    }

    private fun retrieValue(input: EditText): Double{

        return try{
            input.text.toString().toDouble()
        }catch(e: NumberFormatException){
            Toast.makeText(this,getString(R.string.invalid_value),Toast.LENGTH_SHORT).show()
            0.0
        }
    }

    private fun setClick(){

        actionButton.setOnClickListener(this)
    }

    private fun findById(){

        gasolineEditText = findViewById(R.id.edittextvalue_gasoline)
        ethanolEditText = findViewById(R.id.editextvalue_alcool)
        actionButton = findViewById(R.id.action_buttom)
        textView = findViewById(R.id.textview_result)
    }

    override fun onClick(v: View?) {
        if(v == actionButton){
            calculate()
        }
    }
}