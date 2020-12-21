package ifam.com.paramaisempreender

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class TelaPActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var BottomNavigationView : BottomNavigationView
    private lateinit var homeFragment: HomeFragment
    private lateinit var adicionarFragment: AdicionarFragment
    private lateinit var perfilFragment: PerfilFragment
    private lateinit var prodAdactivity: Prod_adActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_p)

        homeFragment = HomeFragment()
        adicionarFragment = AdicionarFragment()
        perfilFragment = PerfilFragment()
        prodAdactivity = Prod_adActivity()

        BottomNavigationView = findViewById(R.id.btt_naveg)
        BottomNavigationView.setOnNavigationItemSelectedListener(this)

        setFragment(homeFragment)
    }

    private fun setFragment(fragment:Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frl, fragment)
        fragmentTransaction.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btt_home ->{
                setFragment(homeFragment)
            }

            R.id.btt_adici ->{
              setFragment(adicionarFragment)
            }
            R.id.btt_perfil ->{
                setFragment(perfilFragment)
            }
        }
        return true
    }
}

private fun Any.onActivityCreated(savedInstanceState: Bundle?) {
}
