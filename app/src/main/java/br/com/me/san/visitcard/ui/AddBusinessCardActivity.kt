package br.com.me.san.visitcard.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.me.san.visitcard.App
import br.com.me.san.visitcard.R
import br.com.me.san.visitcard.data.BusinessCard
import br.com.me.san.visitcard.databinding.ActivityAddBusinessCardBinding
import br.com.me.san.visitcard.viewmodel.MainViewModel
import br.com.me.san.visitcard.viewmodel.MainViewModelFactory
import com.google.android.material.textfield.TextInputLayout

class AddBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
    }

    private fun insertListeners() {
        binding.btnConfirm.setOnClickListener {
            if (validarCampos()) {
                val businessCard = BusinessCard(
                    nome = binding.tilNome.editText?.text.toString(),
                    empresa = binding.tilEmpresa.editText?.text.toString(),
                    telefone = binding.tilTelefone.editText?.text.toString(),
                    email = binding.tilEmail.editText?.text.toString(),
                    fundoPersonalizado = binding.tilCor.editText?.text.toString()
                )
                mainViewModel.insert(businessCard)
                Toast.makeText(this, R.string.label_show_success, Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        binding.btnClose.setOnClickListener {
            finish()
        }
    }

    private fun validarCampos(): Boolean {
        var retorno = true
        val textInputLayout = arrayListOf<TextInputLayout>(
            binding.tilNome,
            binding.tilTelefone,
            binding.tilEmail,
            binding.tilEmpresa
        )

        textInputLayout.forEach {
            if (it.editText?.text.toString() == "") {
                it.error = "Campo obrigatório"
                retorno = false
            } else it.error = null
        }

        val cor: String = binding.tilCor.editText?.text.toString()
        cor.length

        // #000000 = 7 cor hexa
        if (cor.length != 7) {
            binding.tilCor.error = "Coleque uma cor hexadecimal válida"
            retorno = false
        } else {
            binding.tilCor.error = null
        }

        return retorno
    }
}