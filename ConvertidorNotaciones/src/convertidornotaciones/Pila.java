/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convertidornotaciones;

import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class Pila {
    
     private Elementopila stck[] = new Elementopila[100];
     private int tos;
    
     Pila(){//Stack Constraction method
        tos=-1;
    }
    
    boolean isEmpty(){//Check whether or not Stack is Empty
        return(tos==-1);
    }
    
    boolean isFull(){//Check whether or not Stack is Full
        return(tos==99);
    }
    
    void push(Elementopila SE){
        try{
            if(tos==99)
                throw new ArithmeticException("La pila esta llena!");//Stack is full
            else{
                stck[++tos]=SE;
            }
        }
        catch(ArithmeticException exp){
            JOptionPane.showMessageDialog(null, exp);
            throw new ArithmeticException("La pila esta llena!");//Stack is full
        }
    }//Push into the Stack
    
   Elementopila pop(){//Pop from Stack
        try{
            if(tos<0)
                throw new ArithmeticException("Pila vacia");
            else
                return stck[tos--];
        }
        catch(ArithmeticException exp){
            JOptionPane.showMessageDialog(null, exp);
            throw new ArithmeticException("Pila Vacia");
        }
    }
    
      Elementopila pop2(){//Pop from Stack
        try{
            if(tos<0)
                throw new ArithmeticException("Pila vacia");
            else
                return stck[tos++];
        }
        catch(ArithmeticException exp){
            JOptionPane.showMessageDialog(null, exp);
            throw new ArithmeticException("Pila Vacia");
        }
    }
      
    Elementopila top(){//Return the top element of Stack
        if(isEmpty())
            throw new ArithmeticException("Pila Vacia");
        else
            return stck[tos];
    }

    void push(char opTop) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
