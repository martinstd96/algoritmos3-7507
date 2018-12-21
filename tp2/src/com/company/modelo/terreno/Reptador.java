package com.company.modelo.terreno;

import com.company.excepciones.CasilleroLlenoException;
import com.company.excepciones.CasilleroNoExistenteException;
import com.company.excepciones.MapaLlenoException;
import com.company.modelo.unidades.Unidad;

import java.util.ArrayList;

/** El Reptador es una entidad que se encarga de buscar en el mapa el casillero vacío
 * más cercano a un punto de origen que se indique. Se le pasan las coordenadas
 * iniciales por parámetro y se lo actualiza para que se mueva hasta que encuentre
 * un casillero vacío. Se mueve en espiral por el mapa para mantenerse lo más cerca
 * posible de su punto de origen.
 */
public class Reptador {

    private Integer posicionHorizontal;
    private Integer posicionVertical;
    private Mapa mapa;
    private Integer casillerosMirados = 0;

    private Integer direccionX = 1;


    protected Reptador(Integer posicionHorizontal, Integer posicionVertical){
        this.posicionHorizontal = posicionHorizontal;
        this.posicionVertical = posicionVertical;
        this.mapa = Mapa.getMapa();
    }

    /* Devuelve un si encuentra uno vacío. Avanza el Reptador.
     */
    protected Boolean buscar() throws MapaLlenoException{

        Boolean encontrado = false;

        if(casillerosMirados > mapa.obtenerTamanio()){
            throw new MapaLlenoException("No se encontró un casillero vacío en el mapa");
        }
        avanzarPosicion();

        try{
            Casillero lugar = mapa.obtenerCasillero(posicionHorizontal, posicionVertical );

            casillerosMirados++;
            encontrado = (! lugar.estaOcupado());

        } catch (Exception e){
            //Se da vuelta si no encuentra en esa direccion
            direccionX *= -1;
            return false;
        }


        return encontrado;
    }

    protected void ubicarUnidad(Unidad unidad) throws CasilleroNoExistenteException, CasilleroLlenoException {

        unidad.establecerCoordenadasDeNacimiento(posicionHorizontal,posicionVertical);
        mapa.ubicar(unidad,posicionHorizontal, posicionVertical);
    }

    private void avanzarPosicion(){
        posicionHorizontal += direccionX;
    }

}
