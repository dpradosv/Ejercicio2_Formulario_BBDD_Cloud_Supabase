package com.example.ejercicio2_formulario_bbdd_cloud_supabase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.ejercicio2_formulario_bbdd_cloud_supabase.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val submitButton = binding.buttonFormSubmit
        submitButton.isEnabled = false

        fun isValidEmail(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun validateFormFields(): Boolean {
            val title = binding.editTextFormTitle.text.toString().trim()
            val description = binding.editTextFormDescription.text.toString().trim()
            val email = binding.editTextFormEmail.text.toString().trim()
            val prioritySelected = binding.radioGroupFormPriority.checkedRadioButtonId != -1
            return title.length in 5..60 && description.length in 20..500 && isValidEmail(email) && prioritySelected

        }

        fun updateSubmitButtonState() {
            submitButton.isEnabled = validateFormFields()
        }


        binding.editTextFormTitle.doAfterTextChanged {
            val title = binding.editTextFormTitle.text.toString().trim()
            if (title.length !in 5..60) {
                binding.editTextFormTitle.error = getString(R.string.error_form_title)
            } else {
                binding.editTextFormTitle.error = null
            }
            updateSubmitButtonState()
        }


        binding.editTextFormDescription.doAfterTextChanged {
            val description = binding.editTextFormDescription.text.toString().trim()
            if (description.length !in 20..500) {
                binding.editTextFormDescription.error = getString(R.string.error_form_description)
            } else {
                binding.editTextFormDescription.error = null
            }
            updateSubmitButtonState()
        }


        binding.editTextFormEmail.doAfterTextChanged {
            val email = binding.editTextFormEmail.text.toString().trim()
            if (!isValidEmail(email)) {
                binding.editTextFormEmail.error =
                    getString(R.string.error_form_email_invalid_format)
            } else {
                binding.editTextFormEmail.error = null

            }
            updateSubmitButtonState()
        }

        binding.radioGroupFormPriority.setOnCheckedChangeListener { _, _ ->
            updateSubmitButtonState()
        }

        
        submitButton.setOnClickListener {
            submitButton.isEnabled = false
            submitButton.text = getString(R.string.button_form_sending)

        }
    }
}