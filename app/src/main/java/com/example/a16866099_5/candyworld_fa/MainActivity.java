package com.example.a16866099_5.candyworld_fa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ArrayList<String> nombres = new ArrayList<String>();
    private GridView gridView;
    private MyAdapter myAdapter;
    private ArrayList<Dulce> dulce = new ArrayList<Dulce>();

    final String[] nombre = new String[] { "Dulce de arandano",
            "Algodon de azucar", "Snickers", "Ositos de goma" };

    final String[] descripcion = new String[] {
            "Vendidos al por mayor",
            "Para pegarse los dedos", "El preferido de Diabetin",
            "PETA te estara buscando" };

    final Integer[] icono = { R.drawable.dulce_arandano,
            R.drawable.dulce_algodon, R.drawable.snickers, R.drawable.osos_goma };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        dulce = new ArrayList<Dulce>();


        for (int i = 0; i < nombre.length; i++) {
            Dulce item = new Dulce(nombre[i], descripcion[i], icono[i]);
            dulce.add(item);
        }

        gridView = (GridView) findViewById(R.id.gridView);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, ""+dulce.get(i), Toast.LENGTH_LONG).show();
            }
        });
        //Enlazamos con nuestro adaptador personalizado
        myAdapter = new MyAdapter(this, R.layout.grid_item, dulce);
        gridView.setAdapter(myAdapter);

        //Registramos el adaptador
        registerForContextMenu(gridView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.add_item:
                //agregamos nuevo nombre

                    Dulce items = new Dulce("Hola", "Que tal ", R.drawable.dulce_sandia);
                    dulce.add(items);

                //Notificamos al adaptador del cambio producido
                this.myAdapter.notifyDataSetChanged();
                return true;

            case R.id.cambiar_vista:
                dulce = (ArrayList<Dulce>) getIntent().getSerializableExtra("nombres");
                Intent intent = new Intent(MainActivity.this,ListActivity.class);
                intent.putExtra("nombres",dulce);
                startActivity(intent);
            default:
                return  super.onOptionsItemSelected(item);
        }
    }
    //creamos el menu contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.dulce.get(info.position).getNombre());
        inflater.inflate(R.menu.context_menu, menu);
    }

    //manejamos eventos del menu contextual
    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.delete_item:
                //eliminamos el item clickeado
                this.dulce.remove(info.position);
                //notificamos el cambio al adapter
                this.myAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
