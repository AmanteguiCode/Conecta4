package conecta4;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borja
 */
public class Pila {

    private List pila = new ArrayList<Integer>();
    private int maxSize = 6;

    public Pila() {
    }
    
    public Integer talla(){
        return pila.size();
    }
    
    public Integer pop(){
        int retorno = (int) pila.get(pila.size() - 1);
        pila.remove(pila.size() - 1);
        return retorno;
    }
    
    public boolean push(int insertar) throws ColumnaLlenaExcepcion{
        if(pila.size() > maxSize) throw new ColumnaLlenaExcepcion("Columna llena");
        pila.add(insertar);
        return true;
    }    
    
    public boolean vacia(){
        return pila.isEmpty();
    }
    
    public Integer top(){
        if(!vacia()) return (Integer) pila.get(pila.size()-1);
        return -1;
    }
    
    public Integer getElementoEnPosicion(int posicion){
        if(posicion >= pila.size()) return 0;
        return (Integer) pila.get(posicion);
    }

    
    
    
}
