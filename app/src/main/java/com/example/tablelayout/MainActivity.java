package com.example.tablelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button b1,b2,b3,b4,b5,b6,b7,b8,b9;
    private String signo="X";
    private int[][] magicSquareX;
    private int[][] magicSquareO;
    private int[][] auxX;
    private int[][] auxO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.b2);
        b3=(Button)findViewById(R.id.b3);
        b4=(Button)findViewById(R.id.b4);
        b5=(Button)findViewById(R.id.b5);
        b6=(Button)findViewById(R.id.b6);
        b7=(Button)findViewById(R.id.b7);
        b8=(Button)findViewById(R.id.b8);
        b9=(Button)findViewById(R.id.b9);

        //utlizo cuadros magicos para verificar si alguien gano. Los cuadores magicos son una matriz de 3x3
        // que suman 15 en todas sus filas, columnas y diagonales. Siendo cada numero del 1 al 9. Esto no es necesario pero
        // es una forma de verificar si alguien gano divertida.
        magicSquareX = new int[][]{
                {8, 1, 6},
                {3, 5, 7},
                {4, 9, 2}
        };

        magicSquareO = new int[][]{
                {2, 9, 4},
                {7, 5, 3},
                {6, 1, 8}
        };

        auxX = new int[][]{
                {0,0,0},
                {0,0,0},
                {0,0,0}
        };

        auxO = new int[][]{
                {0,0,0},
                {0,0,0},
                {0,0,0}
        };
    }

    public void cambiarsigno(View v){
        Button b=(Button)v;
        //si el boton no tiene texto, entonces le pongo el signo
        if(b.getText().toString().equals("")) {
            b.setText(signo);
            verificarGanador(signo);
        }
        cambiarJugador();
    }

    public void cambiarJugador(){
        if(signo.equals("X")){
            signo="O";
        }else{
            signo="X";
        }
    }

    public void verificarGanador(String signo){
        String[][] valorActual = new String[3][3];

        valorActual[0][0] = b1.getText().toString();
        valorActual[0][1] = b2.getText().toString();
        valorActual[0][2] = b3.getText().toString();
        valorActual[1][0] = b4.getText().toString();
        valorActual[1][1] = b5.getText().toString();
        valorActual[1][2] = b6.getText().toString();
        valorActual[2][0] = b7.getText().toString();
        valorActual[2][1] = b8.getText().toString();
        valorActual[2][2] = b9.getText().toString();

        if(signo.equals("X")){
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(valorActual[i][j].equals("X")){
                        //si el valor es X, entonces en la matriz auxiliar pongo el valor del cuadro magico
                        auxX[i][j] = magicSquareX[i][j];
                        if(loopAux(auxX)){
                            Toast.makeText(this, "Gano X", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }else if(signo.equals("O")){
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(valorActual[i][j].equals("O")){
                        auxO[i][j] = magicSquareO[i][j];
                        if(loopAux(auxO)){
                            Toast.makeText(this, "Gano O", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }

    public boolean loopAux(int[][] aux){
        int size = aux.length;

        int sumDiag1 = 0;
        int sumDiag2 = 0;
        //verifico diagonales
        for(int i=0;i<size;i++){
            sumDiag1 += aux[i][i];
            sumDiag2 += aux[i][size-i-1];
        }
        if(sumDiag1 == 15 || sumDiag2 == 15){
            return true;
        }

        //verifico filas y columnas
        for(int i=0;i<size;i++){
            int sumRow = 0;
            int sumCol = 0;
            for(int j=0;j<size;j++){
                sumRow += aux[i][j];
                sumCol += aux[j][i];
            }
            if(sumRow == 15 || sumCol == 15){
                return true;
            }
        }

        //si llego hasta aqui es porque es uno suma 15
        return false;
    }
}