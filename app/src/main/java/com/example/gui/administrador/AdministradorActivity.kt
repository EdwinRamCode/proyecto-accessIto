package com.example.gui.administrador

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gui.R
import androidx.appcompat.widget.PopupMenu
import com.example.gui.seguridad.generarReporteActivity


class AdministradorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mostrarqradministrador)
        val botonMenu = findViewById<ImageButton>(R.id.botonOpcionesExtras)

        botonMenu.setOnClickListener {
            val popup = PopupMenu(this, it)
            popup.menuInflater.inflate(R.menu.menu_opciones, popup.menu)

            popup.setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    /**
                     * Nota importante, dado que el flujo que va desde el submenu, al elegir la opcion de usuarios, se abre la activity usuariosAdministradorActivity
                     * y por consiguiente al presionar el boton btnDarAltaAdministrador se redirige a la activity DarAltaUsuarioAdminActivity se tienene 3 botnes, pero
                     * el que importa en este caso es el boton HOME (salir), al presionarlo se implemento unas banderas que eliminan las actividades intermedias de la pila
                     * y si esta actividad no esta en la pila (AdministradorActivity) la inicia normalmente o si esta en el top la reutiliza, peroooooooo
                     * puede haber inconvenientes a la hora de cargar el qr, se debe de tener en cuenta eso.
                     */
                    R.id.menu_usuarios -> {
                        val intent = Intent(this, usuariosAdministradorActivity::class.java)
                        startActivity(intent)
                        true
                    }

                    // Submenú de SOLICITUDES
                    R.id.menu_solicitudes_usuarios -> {
                        //Se arreglo lo del boton home y salir
                        val intent = Intent(this, SolicitudesUsuariosAdministradorActivity::class.java)
                        startActivity(intent)
                        true
                    }

                    R.id.menu_solicitudes_invitados -> {
                        val intent = Intent(this, SolicitudesInvitadosAdministradorActivity::class.java)
                        startActivity(intent)
                        true
                    }

                    // Submenú de REPORTES
                    R.id.menu_reportes_ver -> {
                        val intent = Intent(this, ListaReportesAdministradorActivity::class.java)
                        startActivity(intent)
                        true
                    }

                    R.id.menu_reportes_generar -> {
                        val intent = Intent(this, generarReporteAdminActivity::class.java)
                        startActivity(intent)
                        true
                        //aun falta componer que se separen los elementos de mes y año, pero me dio hueva porque estoy apurado, ayuda
                    }
                    else -> false
                }
            }
            popup.show()
        }

        //bton salir que regresa al activityMain
        var botonSalir = findViewById<Button>(R.id.botonSalir).setOnClickListener{
            finish()
        }
    }
}